package com.gabee.backend.photo;

import java.util.List;

public interface PhotoService {
    List<Photo> findAll();

    Photo findById(Long id);

    Photo insert(Photo obj);

    Photo update(Long id, Photo obj);

    void delete(Long id);
}
