package com.example.booking.controller;

import com.example.booking.dto.AirplaneDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.service.AirplaneService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/airplanes")
@SecurityRequirement(name = "token")
public class AirplaneController {
    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> getAirplane(){
        return airplaneService.getAirplane().createResponseEntity();
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> postAirplane(@RequestBody AirplaneDTO airplaneDTO) throws BookingException.NotFoundException {

        return airplaneService.postAirplane(airplaneDTO).createResponseEntity();
    }
    @DeleteMapping({"{airplaneId}"})
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> deleteAirplane(@PathVariable("airplaneId") Long id) throws BookingException.NotFoundException {
        return airplaneService.deleteAirplane(id).createResponseEntity();
    }
    @PatchMapping({"{airplaneId}"})
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> patchAirplane(@PathVariable("airplaneId") Long id,
                                           @RequestBody AirplaneDTO airplaneDTO) throws BookingException.NotFoundException {
        return airplaneService.patchAirplane(id,airplaneDTO).createResponseEntity();
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> findById(@PathVariable("id")Long id) throws BookingException.NotFoundException {
        return airplaneService.getAirplaneById(id).createResponseEntity();
    }

}
