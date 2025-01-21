package com.enviro.assessment.grad001.terrence_ramoetlo.controller;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.DisposalGuidelineAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.DisposalGuidelineDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.DisposalGuideline;
import com.enviro.assessment.grad001.terrence_ramoetlo.service.DisposalGuidelineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/disposal-guidelines")
public class DisposalGuidelineController {
    private final DisposalGuidelineService disposalGuidelineService;

    public DisposalGuidelineController(DisposalGuidelineService disposalGuidelineService) {
        this.disposalGuidelineService = disposalGuidelineService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getDisposalGuidelines() {
        try {
            List<DisposalGuideline> guidelines = disposalGuidelineService.getDisposalGuidelines();
            return ResponseEntity.ok(guidelines);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while fetching disposal guidelines.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGuideline(@RequestBody DisposalGuideline disposalGuideline) {
        try {
            disposalGuidelineService.createDisposalGuideline(disposalGuideline);
            return ResponseEntity.ok("Disposal guideline created successfully.");
        } catch (DisposalGuidelineAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Disposal guideline with the same name already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the disposal guideline.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGuideline(@PathVariable Long id) {
        try {
            disposalGuidelineService.deleteDisposalGuideline(id);
            return ResponseEntity.ok("Disposal guideline deleted successfully.");
        } catch (DisposalGuidelineDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Disposal guideline with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the disposal guideline.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGuideline(@PathVariable Long id, @RequestBody DisposalGuideline disposalGuideline) {
        try {
            DisposalGuideline updatedGuideline = disposalGuidelineService.updateDisposalGuideline(id, disposalGuideline);
            return ResponseEntity.ok(updatedGuideline);
        } catch (DisposalGuidelineDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Disposal guideline with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating the disposal guideline.");
        }
    }
}
