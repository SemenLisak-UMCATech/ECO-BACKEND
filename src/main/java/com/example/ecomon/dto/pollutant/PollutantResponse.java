package com.example.ecomon.dto.pollutant;

public record PollutantResponse(
        Long id,
        String name,
        Long elv,
        Long mfr,
        double tlv,
        double sf,
        double rfc
) {}
