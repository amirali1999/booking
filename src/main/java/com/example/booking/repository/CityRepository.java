package com.example.booking.repository;

import com.example.booking.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> findByLabel(String label);
    Optional<City> findByValue(String value);
}
