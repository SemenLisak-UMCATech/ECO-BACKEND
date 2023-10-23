package com.example.ecomon.controller.pollution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ecomon.dto.pollution.AggregatedPollution;
import com.example.ecomon.dto.pollution.PollutionRequest;
import com.example.ecomon.dto.pollution.PollutionResponse;
import com.example.ecomon.service.pollution.PollutionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pollutions")
@RequiredArgsConstructor
public class PollutionController {

    private final PollutionService pollutionService;

    @GetMapping
    public List<AggregatedPollution> findAll(){
        return pollutionService.findAll();
    }

    @GetMapping("/{id}")
    public PollutionResponse findById(@PathVariable Long id){
        return pollutionService.findById(id);
    }

    @PutMapping("/{id}")
    public PollutionResponse update(@PathVariable Long id, @RequestBody PollutionRequest pollutionRequest){
        return pollutionService.update(id, pollutionRequest);
    }

    @PostMapping
    public PollutionResponse create(@RequestBody PollutionRequest pollutionRequest){
        return pollutionService.create(pollutionRequest);
    }

    @DeleteMapping ("/{id}")
    public void deletePollution(@PathVariable Long id){
        pollutionService.delete(id);
    }
}
