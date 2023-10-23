package com.example.ecomon.controller.object;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecomon.dto.object.ObjectRequest;
import com.example.ecomon.dto.object.ObjectResponse;
import com.example.ecomon.service.object.ObjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/objects")
public class ObjectController {

    private final ObjectService objectService;

    @GetMapping
    public List<ObjectResponse> findAll() {
        return objectService.getAll();
    }

    @GetMapping("/{id}")
    public ObjectResponse findById(@PathVariable Long id) {
        return objectService.get(id);
    }

    @PostMapping
    public ObjectResponse create(@RequestBody ObjectRequest objectRequest) {
        return objectService.create(objectRequest);
    }

    @PutMapping("/{id}")
    public ObjectResponse update(@PathVariable Long id, @RequestBody ObjectRequest objectRequest) {
        return objectService.update(id, objectRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        objectService.delete(id);
    }
}
