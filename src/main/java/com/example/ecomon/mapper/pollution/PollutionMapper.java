package com.example.ecomon.mapper.pollution;

import com.example.ecomon.dto.pollution.PollutionRequest;
import com.example.ecomon.dto.pollution.PollutionResponse;
import com.example.ecomon.persistence.entity.pollution.Pollution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import static com.example.ecomon.service.calculation.CalculationService.*;



@Mapper(componentModel = "spring")
public interface PollutionMapper {

    @Mappings({
            @Mapping(target = "objectName", source = "object.name"),
            @Mapping(target = "objectDescription", source = "object.description"),
            @Mapping(target = "pollutantName", source = "pollutant.name")
    })
    PollutionResponse toPollutionResponse(Pollution pollution);
}
