package com.enviro.assessment.grad001.terrence_ramoetlo.controller;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.CategoryAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.CategoryDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.WasteCategory;
import com.enviro.assessment.grad001.terrence_ramoetlo.service.WasteCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/waste-categories")
public class WasteCategoryController {
    private final WasteCategoryService wasteCategoryService;

    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getWasteCategories() {
        try {
            List<WasteCategory> wasteCategoryList = wasteCategoryService.getWasteCategories();
            return ResponseEntity.ok(wasteCategoryList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while fetching waste categories.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody WasteCategory wasteCategory) {
        try {
            wasteCategoryService.createWasteCategory(wasteCategory);
            return ResponseEntity.ok("Category created successfully.");
        } catch (CategoryAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Category with the same name already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the category.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            wasteCategoryService.deleteWasteCategory(id);
            return ResponseEntity.ok("Category deleted successfully.");
        } catch (CategoryDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the category.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody WasteCategory wasteCategory) {
        try {
            WasteCategory updatedCategory = wasteCategoryService.updateWasteCategory(id, wasteCategory);
            return ResponseEntity.ok(updatedCategory);
        } catch (CategoryDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating the category.");
        }
    }
}
