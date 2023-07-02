package com.example.booking.controller;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.exception.Response;
import com.example.booking.model.Passenger;
import com.example.booking.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @GetMapping
    public ResponseEntity<?> getPassenger(){
        return passengerService.getPassenger().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postPassenger(@RequestBody PassengerDTO passengerDTO){
        return passengerService.postPassenger(passengerDTO).createResponseEntity();
    }
    @DeleteMapping("{passengerId}")
    public ResponseEntity<?> deletePassenger(@PathVariable("passengerId") Long id){
        return passengerService.deletePassenger(id).createResponseEntity();
    }
    @PatchMapping("{passengerId}")
    public ResponseEntity<?> patchPassenger(@PathVariable("passengerId") Long id,
                                            @RequestBody PassengerDTO passengerDTO){
        return passengerService.patchPassenger(id,passengerDTO).createResponseEntity();
    }
}
