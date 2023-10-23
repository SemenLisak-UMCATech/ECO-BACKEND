package com.example.ecomon.mapper.object;

import org.mapstruct.Mapper;
import com.example.ecomon.dto.object.ObjectRequest;
import com.example.ecomon.dto.object.ObjectResponse;
import com.example.ecomon.persistence.entity.object.Object;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    ObjectRequest toObjectRequest(Object object);

    Object fromObjectRequest(ObjectRequest objectRequest);

    ObjectResponse toObjectResponse(Object object);
}
