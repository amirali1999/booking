package com.example.booking.controller;

import com.example.booking.dto.ResidenceDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.model.Residence;
import com.example.booking.service.ResidenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/residences")
public class ResidenceController {
    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }
    @GetMapping
    public ResponseEntity<?> getResidence(){
        return residenceService.getResidence().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postResidence(@RequestBody ResidenceDTO residenceDTO) throws BookingException.NotFoundException {
        return residenceService.postResidence(residenceDTO).createResponseEntity();
    }
    @DeleteMapping("{residenceId}")
    public ResponseEntity<?> deleteResidence(@PathVariable("residenceId") Long id)
            throws BookingException.NotFoundException {
        return residenceService.deleteResidence(id).createResponseEntity();
    }
    @PatchMapping("{residenceId}")
    public ResponseEntity<?> patchResidence(@PathVariable("residenceId") Long id,
                                            @RequestBody ResidenceDTO residenceDTO) throws BookingException.NotFoundException {
        return residenceService.patchResidence(id,residenceDTO).createResponseEntity();
    }
}
