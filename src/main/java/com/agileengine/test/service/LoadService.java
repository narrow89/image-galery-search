package com.agileengine.test.service;

import com.agileengine.test.models.ImagesResponse;
import com.agileengine.test.models.PictureResponse;
import com.agileengine.test.repositories.PictureRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoadService {

  PictureRepository pictureRepository;
  RestService restService;

  public LoadService(final PictureRepository pictureRepository, final RestService restService) {
    this.pictureRepository = pictureRepository;
    this.restService = restService;
  }

  @Scheduled(fixedRateString = "${reload-time}")
  public void load() {
    log.info("STARTED LOAD");
    List<PictureResponse> images = new ArrayList<>();
    restService.login();

    ImagesResponse response = restService.getPicutres();

    images.addAll(response.getPictures().stream().map(pictureResponse ->
        restService.getPicutreDetail(pictureResponse.getId())
    ).collect(Collectors.toList()));

    while (response.getHasMore()) {
      response = restService.getPicutresPage(response.getPage() + 1);

      images.addAll(response.getPictures().stream().map(pictureResponse ->
          restService.getPicutreDetail(pictureResponse.getId())
      ).collect(Collectors.toList()));
    }

    pictureRepository.saveAll(images);
  }
}
