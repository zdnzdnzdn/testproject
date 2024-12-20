package com.finalproject.client_app_etickpark.helper;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestInterceptorHelper implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!request.getURI().getPath().equals("/auth/login")) {
            String token = BasicHeaderHelper.createBasicToken(
              authentication.getPrincipal().toString(),
              authentication.getCredentials().toString()
            );
            request.getHeaders().add("Authorization", "Basic " + token);
          }
          ClientHttpResponse response = execution.execute(request, body);
          return response;
    }
}
