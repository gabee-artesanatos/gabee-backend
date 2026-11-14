package com.gabee.backend.photo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController {

    @Autowired
    private PhotoService service;

    @GetMapping
    public ResponseEntity<List<Photo>> findAll() {
        List<Photo> photos = service.findAll();
        return ResponseEntity.ok().body(photos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Photo> findById(@PathVariable Long id) {
        Photo photo = service.findById(id);
        return ResponseEntity.ok().body(photo);
    }

    @PostMapping
    public ResponseEntity<Photo> insert(@Valid @RequestBody Photo obj) {
        Photo response = service.insert(obj);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Photo> update(@PathVariable Long id, @Valid @RequestBody Photo obj) {
        Photo response = service.update(id, obj);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
