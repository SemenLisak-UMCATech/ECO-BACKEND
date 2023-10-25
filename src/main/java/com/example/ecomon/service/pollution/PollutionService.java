package com.example.ecomon.service.pollution;

import static com.example.ecomon.service.calculation.CalculationService.calculateAddLadd;
import static com.example.ecomon.service.calculation.CalculationService.calculateAirFee;
import static com.example.ecomon.service.calculation.CalculationService.calculateCR;
import static com.example.ecomon.service.calculation.CalculationService.calculateHQ;

import com.example.ecomon.dto.pollution.AggregatedPollution;
import com.example.ecomon.dto.pollution.PollutionRequest;
import com.example.ecomon.dto.pollution.PollutionResponse;
import com.example.ecomon.exception.object.ObjectNotFoundException;
import com.example.ecomon.exception.pollutant.PollutantNotFoundException;
import com.example.ecomon.exception.pollution.PollutionNotFoundException;
import com.example.ecomon.mapper.pollution.PollutionMapper;
import com.example.ecomon.persistence.entity.object.Object;
import com.example.ecomon.persistence.entity.pollutant.Pollutant;
import com.example.ecomon.persistence.entity.pollution.Pollution;
import com.example.ecomon.persistence.repository.ObjectRepository;
import com.example.ecomon.persistence.repository.PollutantRepository;
import com.example.ecomon.persistence.repository.PollutionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PollutionService {

    private final PollutionRepository pollutionRepository;
    private final PollutantRepository pollutantRepository;
    private final ObjectRepository objectRepository;
    private final PollutionMapper pollutionMapper;

    public List<AggregatedPollution> findAll() {
        return pollutionRepository.findAllByOrderById(AggregatedPollution.class);
    }

    @Transactional
    public PollutionResponse create(PollutionRequest pollutionRequest) {
        Pollutant pollutant = mapToPollutant(pollutionRequest);
        Object object = mapToObject(pollutionRequest);

        Pollution pollution = mapToPollution(pollutionRequest, pollutant, object);
        pollutionRepository.save(pollution);

        return pollutionMapper.toPollutionResponse(pollution);
    }

    @Transactional
    public void delete(Long id) {
        findPollutionByIdOrThrow(id);
        pollutionRepository.deleteById(id);
    }

    @Transactional
    public PollutionResponse update(Long id, PollutionRequest pollutionRequest) {
        Pollution pollution = findPollutionByIdOrThrow(id);
        Object object = findObjectByIdOrThrow(pollutionRequest);
        Pollutant pollutant = mapToPollutant(pollutionRequest);

        updateEntity(pollutionRequest, object, pollution, pollutant);

        return pollutionMapper.toPollutionResponse(pollution);
    }

    public PollutionResponse findById(Long id) {
        return pollutionRepository.findPollutionById(id, PollutionResponse.class)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
    }

    private Pollution mapToPollution(PollutionRequest pollutionRequest, Pollutant pollutant, Object object) {
        return Pollution.builder()
                .pollutant(pollutant)
                .object(object)
                .valuePollution(pollutionRequest.valuePollution())
                .year(pollutionRequest.year())
                .pollutionConcentration(pollutionRequest.pollutionConcentration())
                .hq(calculateHQ(pollutionRequest.pollutionConcentration(), pollutant.getRfc()))
                .cr(calculateCR(pollutionRequest.pollutionConcentration(), pollutant.getSf()))
                .fee(calculateAirFee(pollutant.getMfr(), pollutionRequest.valuePollution(), pollutant.getTlv()))
                .addLadd(calculateAddLadd(pollutionRequest.pollutionConcentration()))
                .build();
    }

    private Pollutant mapToPollutant(PollutionRequest pollutionRequest) {
        return pollutantRepository.findByNameIgnoreCase(pollutionRequest.pollutantName())
                .orElseThrow(() -> new PollutantNotFoundException("name = " + pollutionRequest.pollutantName()));
    }

    private Object mapToObject(PollutionRequest pollutionRequest) {
        return objectRepository.findByNameIgnoreCase(pollutionRequest.objectName())
                .orElse(new Object(pollutionRequest.objectName(), "None"));
    }

    private Pollution findPollutionByIdOrThrow(Long id) {
        return pollutionRepository.findById(id)
                .orElseThrow(() -> new PollutionNotFoundException("id = " + id));
    }

    private Object findObjectByIdOrThrow(PollutionRequest pollutionRequest) {
        return objectRepository.findByNameIgnoreCase(pollutionRequest.objectName())
                .orElseThrow(() -> new ObjectNotFoundException("name = " + pollutionRequest.objectName()));
    }

    private void updateEntity(PollutionRequest pollutionRequest, Object object, Pollution pollution, Pollutant pollutant) {
        object.setDescription(pollutionRequest.objectDescription());
        pollution.setObject(object);
        pollution.setPollutant(pollutant);
        pollution.setPollutionConcentration(pollutionRequest.pollutionConcentration());
        pollution.setValuePollution(pollutionRequest.valuePollution());
        pollution.setYear(pollutionRequest.year());
        pollution.setHq(calculateHQ(pollutionRequest.pollutionConcentration(), pollutant.getRfc()));
        pollution.setCr(calculateCR(pollutionRequest.pollutionConcentration(), pollutant.getSf()));
        pollution.setAddLadd(calculateAddLadd(pollutionRequest.pollutionConcentration()));
        pollution.setFee(calculateAirFee(pollutant.getMfr(), pollutionRequest.valuePollution(), pollutant.getTlv()));
    }
}
