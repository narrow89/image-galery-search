package com.agileengine.test.controller;

import com.agileengine.test.models.PictureResponse;
import com.agileengine.test.repositories.PictureRepository;
import com.agileengine.test.service.LoadService;
import com.agileengine.test.service.RestService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class Controller {

  PictureRepository pictureRepository;
  RestService restService;
  LoadService loadService;

  public Controller(final PictureRepository pictureRepository, final RestService restService,
      final LoadService loadService) {
    this.pictureRepository = pictureRepository;
    this.restService = restService;
    this.loadService = loadService;
  }

  @GetMapping("/search3/{id}")
  public List<PictureResponse> search(@PathVariable(value = "id") final String id) {
    return pictureRepository.getBySearchTerm(id);
  }
}
