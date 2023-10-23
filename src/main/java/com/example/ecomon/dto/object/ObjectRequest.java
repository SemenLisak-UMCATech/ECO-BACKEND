package com.example.ecomon.dto.object;

import jakarta.validation.constraints.NotEmpty;

public record ObjectRequest(
        @NotEmpty
        String name,
        @NotEmpty
        String description
) { }
