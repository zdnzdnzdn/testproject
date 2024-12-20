package com.finalproject.client_app_etickpark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finalproject.client_app_etickpark.entity.Customer;
import com.finalproject.client_app_etickpark.model.request.LoginRequest;
import com.finalproject.client_app_etickpark.model.request.RegisterRequest;
import com.finalproject.client_app_etickpark.model.response.LoginResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
    @Value("${server.base.url}/auth")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    public Boolean login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
	    try {
		    ResponseEntity<LoginResponse> res = restTemplate.exchange(
			    url.concat("/login"),
			    HttpMethod.POST,
			    new HttpEntity<LoginRequest>(loginRequest),
			    new ParameterizedTypeReference<LoginResponse>() {}
		    );

		    if (res.getStatusCode() == HttpStatus.OK) {
			    setPrinciple(res.getBody(), loginRequest.getPassword());
			    saveSecurityContext(request, response);
				
			    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
			    log.info("Name: {}", auth.getName());

			    return true;
		    } else {
			    log.info("Login failed: {}", res.getStatusCode());
		    }
	    } catch (Exception e) {
		    log.warn("Error message: {}", e.getMessage());

		    return false;
	    }

	    return false;
    }

    public void setPrinciple(LoginResponse response, String password) {
		List<SimpleGrantedAuthority> authorities = response
			.getAuthorities()
			.stream()
			.map(authority -> new SimpleGrantedAuthority(authority))
			.toList();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(response.getUsername(), password, authorities);

		SecurityContextHolder.getContext().setAuthentication(token);
		
		log.info("username: {}\nauthorities: {}", response.getUsername(), authorities);
    }

    private void saveSecurityContext(HttpServletRequest request, HttpServletResponse response) {
		SecurityContext ctx = SecurityContextHolder.getContext();
	
		this.securityContextRepository.saveContext(ctx, request, response);
	
		log.info("Security context saved successfully");
    }

    public Customer registration(RegisterRequest registerRequest) {
        return restTemplate.exchange(
            url.concat("/register"),
            HttpMethod.POST,
            new HttpEntity<>(registerRequest),
            new ParameterizedTypeReference<Customer>() {
        }).getBody();
    }
}
