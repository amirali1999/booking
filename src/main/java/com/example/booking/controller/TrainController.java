package com.example.booking.controller;

import com.example.booking.dto.TrainDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.service.TrainService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/trains")
@SecurityRequirement(name = "token")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> getTrain(){
        return trainService.getTrain().createResponseEntity();
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> postTrain(@RequestBody TrainDTO trainDTO) throws BookingException.NotFoundException {
        return trainService.postTrain(trainDTO).createResponseEntity();
    }
    @DeleteMapping("{trainId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> deleteTrain(@PathVariable("trainId") Long id) throws BookingException.NotFoundException {
        return trainService.deleteTrain(id).createResponseEntity();
    }
    @PatchMapping("{trainId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> patchTrain(@PathVariable("trainId") Long id,
                                        @RequestBody TrainDTO trainDTO) throws BookingException.NotFoundException {
        return trainService.patchTrain(id,trainDTO).createResponseEntity();
    }
}
