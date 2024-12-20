package com.finalproject.client_app_etickpark.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.finalproject.client_app_etickpark.entity.Wahana;
import com.finalproject.client_app_etickpark.model.request.WahanaRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WahanaService {
    @Value("${server.base.url}/wahana")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    
    public List<Wahana> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Wahana>>() {}
            )
            .getBody();
    }

    public Wahana getById(Integer id) {
        log.info("endpoint serverapp = {}", url.concat("/" + id));
        
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Wahana>() {}
            )
            .getBody();
    }

    public Wahana create(WahanaRequest wahanaRequest) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("nama", wahanaRequest.getNama());
        body.add("keterangan", wahanaRequest.getKeterangan());
        body.add("harga", wahanaRequest.getHarga());
        
        Resource gambarResource = new ByteArrayResource(wahanaRequest.getGambar().getBytes()) {
        @Override
        public String getFilename() {
            return wahanaRequest.getGambar().getOriginalFilename();
        }
    };
    body.add("gambar", gambarResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate
            .exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Wahana>() {}
            )
            .getBody();
    }

    public Wahana update(Integer id, Wahana wahana) {
        HttpEntity<Wahana> request = new HttpEntity<Wahana>(wahana);
        return restTemplate
            .exchange(url.concat("/" + id), HttpMethod.PUT, request, Wahana.class)
            .getBody();
    }
    
    public Wahana delete(Integer id) {
        return restTemplate
            .exchange(url.concat("/" + id), HttpMethod.DELETE, null, Wahana.class)
            .getBody();
    }
}
