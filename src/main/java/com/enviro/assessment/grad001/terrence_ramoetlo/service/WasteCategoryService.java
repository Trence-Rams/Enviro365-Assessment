package com.enviro.assessment.grad001.terrence_ramoetlo.service;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.CategoryAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.CategoryDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.WasteCategory;
import com.enviro.assessment.grad001.terrence_ramoetlo.repository.WasteCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {
    private WasteCategoryRepository wasteCategoryRepository;

    public WasteCategoryService(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }


    public List<WasteCategory> getWasteCategories(){
        return wasteCategoryRepository.findAll();
    }
    public void createWasteCategory(WasteCategory wasteCategory) throws CategoryAlreadyExistsException {
            Optional<WasteCategory> category = wasteCategoryRepository.findByNameIgnoreCase(wasteCategory.getName());
            if (category.isPresent())
                throw new  CategoryAlreadyExistsException();
            wasteCategoryRepository.save(wasteCategory);
    }
     public void deleteWasteCategory(Long id) throws CategoryDoesNotExistException {
         Optional<WasteCategory> category = wasteCategoryRepository.findById(id);
         if (!category.isPresent())
             throw new CategoryDoesNotExistException();
         wasteCategoryRepository.deleteById(id);
     }

    public WasteCategory updateWasteCategory(Long id,WasteCategory wasteCategory) throws CategoryDoesNotExistException {
        Optional<WasteCategory> category = wasteCategoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryDoesNotExistException();
        }
        WasteCategory updatedCategory = category.get();
        updatedCategory.setName(wasteCategory.getName());
        updatedCategory.setDescription(wasteCategory.getDescription());
        wasteCategoryRepository.save(updatedCategory);
        return updatedCategory;
    }
}
