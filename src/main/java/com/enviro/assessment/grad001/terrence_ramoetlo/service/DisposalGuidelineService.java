package com.enviro.assessment.grad001.terrence_ramoetlo.service;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.DisposalGuidelineAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.DisposalGuidelineDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.DisposalGuideline;
import com.enviro.assessment.grad001.terrence_ramoetlo.repository.DisposalGuidelineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {
    private final DisposalGuidelineRepository disposalGuidelineRepository;

    public DisposalGuidelineService(DisposalGuidelineRepository disposalGuidelineRepository) {
        this.disposalGuidelineRepository = disposalGuidelineRepository;
    }

    public List<DisposalGuideline> getDisposalGuidelines() {return disposalGuidelineRepository.findAll();
    }

    public void createDisposalGuideline(DisposalGuideline disposalGuideline) throws DisposalGuidelineAlreadyExistsException {
        Optional<DisposalGuideline> guideline = disposalGuidelineRepository.findByTitleIgnoreCase(disposalGuideline.getTitle());
        if (guideline.isPresent())
            throw new DisposalGuidelineAlreadyExistsException();
        disposalGuidelineRepository.save(disposalGuideline);
    }

    public void deleteDisposalGuideline(Long id) throws DisposalGuidelineDoesNotExistException {
        Optional<DisposalGuideline> guideline = disposalGuidelineRepository.findById(id);
        if (!guideline.isPresent())
            throw new DisposalGuidelineDoesNotExistException();
        disposalGuidelineRepository.deleteById(id);
    }

    public DisposalGuideline updateDisposalGuideline(Long id, DisposalGuideline disposalGuideline) throws DisposalGuidelineDoesNotExistException {
        Optional<DisposalGuideline> guideline = disposalGuidelineRepository.findById(id);
        if (!guideline.isPresent())
            throw new DisposalGuidelineDoesNotExistException();
        DisposalGuideline updatedGuideline = guideline.get();
        updatedGuideline.setTitle(disposalGuideline.getTitle());
        updatedGuideline.setContent(disposalGuideline.getContent());
        disposalGuidelineRepository.save(updatedGuideline);
        return updatedGuideline;
    }
}
