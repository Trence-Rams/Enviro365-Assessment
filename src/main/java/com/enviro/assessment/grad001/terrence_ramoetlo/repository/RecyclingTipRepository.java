package com.enviro.assessment.grad001.terrence_ramoetlo.repository;

import com.enviro.assessment.grad001.terrence_ramoetlo.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip,Long> {
    Optional<RecyclingTip> findByTitleIgnoreCase(String title);
}
