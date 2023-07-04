package com.example.booking.controller;

import com.example.booking.dto.TrainDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/trains")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }
    @GetMapping
    public ResponseEntity<?> getTrain(){
        return trainService.getTrain().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postTrain(@RequestBody TrainDTO trainDTO) throws BookingException.NotFoundException {
        return trainService.postTrain(trainDTO).createResponseEntity();
    }
    @DeleteMapping("{trainId}")
    public ResponseEntity<?> deleteTrain(@PathVariable("trainId") Long id) throws BookingException.NotFoundException {
        return trainService.deleteTrain(id).createResponseEntity();
    }
    @PatchMapping("{trainId}")
    public ResponseEntity<?> patchTrain(@PathVariable("trainId") Long id,
                                        @RequestBody TrainDTO trainDTO){
        return trainService.patchTrain(id,trainDTO).createResponseEntity();
    }
}
