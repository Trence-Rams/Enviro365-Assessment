package com.enviro.assessment.grad001.terrence_ramoetlo.repository;

import com.enviro.assessment.grad001.terrence_ramoetlo.model.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline,Long> {
    Optional<DisposalGuideline> findByTitleIgnoreCase(String title);
}
