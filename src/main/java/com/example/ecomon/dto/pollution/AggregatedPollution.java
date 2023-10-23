package com.example.ecomon.dto.pollution;

public record AggregatedPollution(
        Long id,
        String objectName,
        String objectDescription,
        String pollutantName,
        double valuePollution,
        Long pollutantMfr,
        Long pollutantElv,
        double pollutantTlv,
        double pollutionConcentration,
        double hq,
        double cr,
        double fee,
        double addLadd,
        int year
) {}
