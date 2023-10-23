package com.example.ecomon.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecomon.persistence.entity.pollutant.Pollutant;

import java.util.List;
import java.util.Optional;

public interface PollutantRepository extends JpaRepository<Pollutant, Long> {

    Optional<Pollutant> findByNameIgnoreCase(String name);

    <T> List<T> findAllBy(Class<T> type);
}