package com.example.ecomon.dto.pollution;

import jakarta.validation.constraints.NotEmpty;

public record PollutionResponse(
        Long id,
        @NotEmpty
        String objectName,
        @NotEmpty
        String objectDescription,
        @NotEmpty
        String pollutantName,
        @NotEmpty
        int year,
        @NotEmpty
        double valuePollution,
        @NotEmpty
        double pollutionConcentration,
        @NotEmpty
        double hq,
        @NotEmpty
        double cr,
        @NotEmpty
        double fee,
        double addLadd) {
}
