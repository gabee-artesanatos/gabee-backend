package com.gabee.backend.photo;

import com.gabee.backend.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository repository;

    public List<Photo> findAll() {

        return repository.findAll()
                .stream()
                .toList();
    }

    public Photo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Photo insert(Photo photo) {
        return repository.save(photo);
    }

    public Photo update(Long id, Photo photo) {
        findById(id);

        return repository.save(photo);
    }

    public void delete(Long id) {
        Photo deletedPhoto = findById(id);

        repository.delete(deletedPhoto);
    }
}
