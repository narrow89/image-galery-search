package com.agileengine.test.models;

import java.util.List;
import lombok.Data;

@Data
public class ImagesResponse {

  List<PictureResponse> pictures;
  Integer page;
  Integer pageCount;
  Boolean hasMore;
}
