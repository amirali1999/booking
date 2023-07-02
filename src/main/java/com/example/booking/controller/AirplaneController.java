package com.example.booking.controller;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.service.AirplaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/airplanes")
public class AirplaneController {
    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }
    @GetMapping
    public ResponseEntity<?> getAirplane(){
        return airplaneService.getAirplane().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postAirplane(@RequestBody AirplaneDTO airplaneDTO){

        return airplaneService.postAirplane(airplaneDTO).createResponseEntity();
    }
    @DeleteMapping({"{airplaneId}"})
    public ResponseEntity<?> deleteAirplane(@PathVariable("airplaneId") Long id) throws BookingException.NotFoundException {
        return airplaneService.deleteAirplane(id).createResponseEntity();
    }
    @PatchMapping({"{airplaneId}"})
    public ResponseEntity<?> patchAirplane(@PathVariable("airplaneId") Long id,
                                           @RequestBody AirplaneDTO airplaneDTO){
        return airplaneService.patchAirplane(id,airplaneDTO).createResponseEntity();
    }
}
