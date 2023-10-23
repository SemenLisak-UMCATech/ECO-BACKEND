package com.example.ecomon.dto.pollution;

import jakarta.validation.constraints.NotNull;

public record PollutionRequest(
        Long id,
        @NotNull
        String objectName,
        @NotNull
        String objectDescription,
        @NotNull
        String pollutantName,
        @NotNull
        int year,
        @NotNull
        double valuePollution,
        @NotNull
        double pollutionConcentration,
        @NotNull
        double hq,
        @NotNull
        double cr,
        @NotNull
        double fee
) {
    public PollutionRequest(String objectName, String objectDescription, String pollutantName,
                            int year, double valuePollution, double pollutionConcentration, double hq, double cr, double fee) {
        this(null, objectName, objectDescription, pollutantName, year, valuePollution, pollutionConcentration,hq,cr,fee);
    }
}
