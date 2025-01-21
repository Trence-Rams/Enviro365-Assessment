package com.enviro.assessment.grad001.terrence_ramoetlo.controller;

import com.enviro.assessment.grad001.terrence_ramoetlo.exception.RecyclingTipAlreadyExistsException;
import com.enviro.assessment.grad001.terrence_ramoetlo.exception.RecyclingTipDoesNotExistException;
import com.enviro.assessment.grad001.terrence_ramoetlo.model.RecyclingTip;
import com.enviro.assessment.grad001.terrence_ramoetlo.service.RecyclingTipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/recycling-tips")
public class RecyclingTipController {
    private final RecyclingTipService recyclingTipService;

    public RecyclingTipController(RecyclingTipService recyclingTipService) {
        this.recyclingTipService = recyclingTipService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getRecyclingTips() {
        try {
            List<RecyclingTip> tips = recyclingTipService.getRecyclingTips();
            return ResponseEntity.ok(tips);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while fetching recycling tips.");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTip(@RequestBody RecyclingTip recyclingTip) {
        try {
            recyclingTipService.createRecyclingTip(recyclingTip);
            return ResponseEntity.ok("Recycling tip created successfully.");
        } catch (RecyclingTipAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Recycling tip with the same name already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the recycling tip.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTip(@PathVariable Long id) {
        try {
            recyclingTipService.deleteRecyclingTip(id);
            return ResponseEntity.ok("Recycling tip deleted successfully.");
        } catch (RecyclingTipDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recycling tip with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the recycling tip.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTip(@PathVariable Long id, @RequestBody RecyclingTip recyclingTip) {
        try {
            RecyclingTip updatedTip = recyclingTipService.updateRecyclingTip(id, recyclingTip);
            return ResponseEntity.ok(updatedTip);
        } catch (RecyclingTipDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recycling tip with ID " + id + " does not exist.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating the recycling tip.");
        }
    }
}
