package com.agileengine.test.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PictureResponse {

  @Id
  String id;
  String cropped_picture;
  String author;
  String camera;
  String tags;
  String full_picture;
}
