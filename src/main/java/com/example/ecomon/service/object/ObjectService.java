package com.example.ecomon.service.object;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ecomon.dto.object.ObjectRequest;
import com.example.ecomon.dto.object.ObjectResponse;
import com.example.ecomon.exception.object.ObjectNotFoundException;
import com.example.ecomon.mapper.object.ObjectMapper;
import com.example.ecomon.persistence.repository.ObjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public ObjectResponse create(ObjectRequest objectRequest) {

        var objectToCreate = objectMapper.fromObjectRequest(objectRequest);

        var createdObject = objectRepository.save(objectToCreate);
        return objectMapper.toObjectResponse(createdObject);
    }

    @Transactional
    public ObjectResponse update(Long id, ObjectRequest objectRequest) {
        var object = objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));

        object.setName(objectRequest.name());
        object.setDescription(objectRequest.description());
        return objectMapper.toObjectResponse(object);
    }

    @Transactional
    public void delete(Long id) {
        objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));
        objectRepository.deleteById(id);
    }

    public ObjectResponse get(Long id) {
        var retrievedObject = objectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("id = " + id));
        return objectMapper.toObjectResponse(retrievedObject);
    }

    public List<ObjectResponse> getAll() {
        return objectRepository.findAllBy(ObjectResponse.class);
    }
}
