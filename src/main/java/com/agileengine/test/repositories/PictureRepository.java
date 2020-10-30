package com.agileengine.test.repositories;

import com.agileengine.test.models.PictureResponse;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PictureRepository extends CrudRepository<PictureResponse, String> {

  @Query("select p from PictureResponse p where "
      + "upper(p.author) like concat('%', upper(?1), '%') "
      + "or upper(p.camera) like concat('%', upper(?1), '%') "
      + "or upper(p.id) like concat('%', upper(?1), '%') "
      + "or upper(p.cropped_picture) like concat('%', upper(?1), '%') "
      + "or upper(p.full_picture) like concat('%', upper(?1), '%') "
      + "or upper(p.tags) like concat('%', upper(?1), '%')")
  List<PictureResponse> getBySearchTerm(String firstName);
}

