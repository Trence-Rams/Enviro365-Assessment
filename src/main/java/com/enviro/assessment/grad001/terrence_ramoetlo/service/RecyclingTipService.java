package com.enviro.assessment.grad001.terrence_ramoetlo.service;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.RecyclingTipAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.RecyclingTipDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.RecyclingTip;
import com.enviro.assessment.grad001.terrence_ramoetlo.repository.RecyclingTipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {
    private final RecyclingTipRepository recyclingTipRepository;

    public RecyclingTipService(RecyclingTipRepository recyclingTipRepository) {
        this.recyclingTipRepository = recyclingTipRepository;
    }

    public List<RecyclingTip> getRecyclingTips() {
        return recyclingTipRepository.findAll();
    }

    public void createRecyclingTip(RecyclingTip recyclingTip) throws RecyclingTipAlreadyExistsException {
        Optional<RecyclingTip> tip = recyclingTipRepository.findByTitleIgnoreCase(recyclingTip.getTitle());
        if (tip.isPresent()) {
            throw new RecyclingTipAlreadyExistsException();
        }
        recyclingTipRepository.save(recyclingTip);
    }

    public void deleteRecyclingTip(Long id) throws RecyclingTipDoesNotExistException {
        Optional<RecyclingTip> tip = recyclingTipRepository.findById(id);
        if (!tip.isPresent()) {
            throw new RecyclingTipDoesNotExistException();
        }
        recyclingTipRepository.deleteById(id);
    }

    public RecyclingTip updateRecyclingTip(Long id, RecyclingTip recyclingTip) throws RecyclingTipDoesNotExistException {
        Optional<RecyclingTip> tip = recyclingTipRepository.findById(id);
        if (!tip.isPresent()) {
            throw new RecyclingTipDoesNotExistException();
        }
        RecyclingTip updatedTip = tip.get();
        updatedTip.setTitle(recyclingTip.getTitle());
        updatedTip.setDescription(recyclingTip.getDescription());
        recyclingTipRepository.save(updatedTip);
        return updatedTip;
    }
}
