package com.example.ecomon.service.pollutant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ecomon.dto.pollutant.PollutantRequest;
import com.example.ecomon.dto.pollutant.PollutantResponse;
import com.example.ecomon.exception.pollutant.PollutantNotFoundException;
import com.example.ecomon.mapper.pollutant.PollutantMapper;
import com.example.ecomon.persistence.entity.pollutant.Pollutant;
import com.example.ecomon.persistence.repository.PollutantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutantService {
    private final PollutantRepository pollutantRepository;
    private final PollutantMapper pollutantMapper;
    @Transactional
    public PollutantResponse create(PollutantRequest pollutantRequest) {
        var pollutantToCrete = pollutantMapper.fromPollutantRequest(pollutantRequest);
        var createdPollutant = pollutantRepository.save(pollutantToCrete);
        return pollutantMapper.toPollutantResponse(createdPollutant);
    }
    @Transactional
    public PollutantResponse update(Long id, PollutantRequest pollutantRequest) {
        Pollutant pollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        pollutant.setName(pollutantRequest.name());
        pollutant.setMfr(pollutantRequest.mfr());
        pollutant.setElv(pollutantRequest.elv());
        pollutant.setTlv(pollutantRequest.tlv());
        pollutant.setSf(pollutantRequest.sf());
        pollutant.setRfc(pollutantRequest.rfc());
        return pollutantMapper.toPollutantResponse(pollutant);
    }
    @Transactional
    public void delete(Long id) {
        pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        pollutantRepository.deleteById(id);
    }

    public PollutantResponse findById(Long id) {
        var retrievedPollutant = pollutantRepository.findById(id).orElseThrow(() -> new PollutantNotFoundException("id = " + id));
        return pollutantMapper.toPollutantResponse(retrievedPollutant);
    }

    public List<PollutantResponse> findAll() {
        return pollutantRepository.findAllBy(PollutantResponse.class);
    }
}
