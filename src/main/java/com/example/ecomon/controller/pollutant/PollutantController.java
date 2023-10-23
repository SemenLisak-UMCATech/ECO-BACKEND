package com.example.ecomon.controller.pollutant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecomon.dto.pollutant.PollutantRequest;
import com.example.ecomon.dto.pollutant.PollutantResponse;
import com.example.ecomon.service.pollutant.PollutantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pollutants")
public class PollutantController {

    private final PollutantService pollutantService;

    @GetMapping
    public ResponseEntity<List<PollutantResponse>> findAll() {
        List<PollutantResponse> pollutants = pollutantService.findAll();
        return ResponseEntity.ok(pollutants);
    }

    @GetMapping("/{id}")
    public PollutantResponse findById(@PathVariable Long id) {
        return  pollutantService.findById(id);
    }

    @PostMapping
    public PollutantResponse create(@RequestBody PollutantRequest pollutantRequest) {
        return pollutantService.create(pollutantRequest);
    }

    @PutMapping("/{id}")
    public PollutantResponse update(@PathVariable Long id, @RequestBody PollutantRequest pollutantRequest) {
        return pollutantService.update(id, pollutantRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pollutantService.delete(id);
    }
}
