package com.example.booking.repository;

import com.example.booking.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<Residence,Long> {
}
