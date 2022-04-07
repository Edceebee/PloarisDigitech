package com.example.PolarisAssessment.repositories;

import com.example.PolarisAssessment.entities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepo extends JpaRepository<Box, Long> {

}
