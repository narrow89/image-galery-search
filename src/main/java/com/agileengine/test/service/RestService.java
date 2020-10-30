package com.agileengine.test.service;

import com.agileengine.test.models.AuthResponse;
import com.agileengine.test.models.ImagesResponse;
import com.agileengine.test.models.PictureResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestService {

  private final RestTemplate restTemplate;

  private AuthResponse authResponse;

  public RestService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
    this.authResponse = new AuthResponse();
    this.authResponse.setAuth(false);
  }

  public ImagesResponse getPicutres() {
    if (authResponse.getAuth()) {
      String url = "http://interview.agileengine.com/images";

      ResponseEntity<ImagesResponse> response = this.restTemplate.exchange(
          url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
          ImagesResponse.class);

      if (response.getStatusCode() == HttpStatus.OK) {
//        this.authResponse = response.getBody();
        return response.getBody();
      } else {
        return null;
      }
    } else {
      log.info("No estas logueado");
    }

    return null;
  }

  public ImagesResponse getPicutresPage(Integer pageNumber) {
    if (authResponse.getAuth()) {
      String url = "http://interview.agileengine.com/images?page=" + pageNumber;

      ResponseEntity<ImagesResponse> response = this.restTemplate.exchange(
          url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
          ImagesResponse.class);

      if (response.getStatusCode() == HttpStatus.OK) {
//        this.authResponse = response.getBody();
        return response.getBody();
      } else {
        return null;
      }
    } else {
      log.info("No estas logueado");
    }

    return null;
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setBearerAuth(authResponse.getToken());
    return headers;
  }

  public PictureResponse getPicutreDetail(String id) {
    if (authResponse.getAuth()) {
      String url = "http://interview.agileengine.com/images/" + id;

      ResponseEntity<PictureResponse> response = this.restTemplate.exchange(
          url, HttpMethod.GET, new HttpEntity<>(getHeaders()),
          PictureResponse.class);

      if (response.getStatusCode() == HttpStatus.OK) {
        return response.getBody();
      } else {
        return null;
      }
    }

    return null;
  }

  public AuthResponse login() {
    String url = "http://interview.agileengine.com/auth";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    Map<String, Object> map = new HashMap<>();
    map.put("apiKey", "23567b218376f79d9415");

    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

    ResponseEntity<AuthResponse> response = this.restTemplate
        .postForEntity(url, entity, AuthResponse.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      this.authResponse = response.getBody();
      this.authResponse.setToken(response.getBody().getToken());
      return response.getBody();
    } else {
      return null;
    }
  }
}
