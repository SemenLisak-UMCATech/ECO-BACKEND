package com.example.ecomon.dto.pollutant;

import jakarta.validation.constraints.NotNull;

public record PollutantRequest(
    @NotNull
    String name,
    @NotNull
    Long elv,
    @NotNull
    Long mfr,
    @NotNull
    double tlv,
    @NotNull
    double sf,
    @NotNull
    double rfc
) {}
