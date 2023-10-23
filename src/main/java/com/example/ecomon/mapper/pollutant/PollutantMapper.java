package com.example.ecomon.mapper.pollutant;

import org.mapstruct.Mapper;
import com.example.ecomon.dto.pollutant.PollutantRequest;
import com.example.ecomon.dto.pollutant.PollutantResponse;
import com.example.ecomon.persistence.entity.pollutant.Pollutant;

@Mapper(componentModel = "spring")
public interface PollutantMapper {

    PollutantRequest toPollutantRequest(Pollutant pollutant);

    PollutantResponse toPollutantResponse(Pollutant pollutant);

    Pollutant fromPollutantRequest(PollutantRequest pollutantRequest);
}
