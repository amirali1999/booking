package com.example.booking.controller;

import com.example.booking.dto.PassengerDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.Response;
import com.example.booking.model.Passenger;
import com.example.booking.service.PassengerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/passengers")
@SecurityRequirement(name = "token")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> getPassenger(){
        return passengerService.getPassenger().createResponseEntity();
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> postPassenger(@RequestBody PassengerDTO passengerDTO) throws BookingException.NotFoundException {
        return passengerService.postPassenger(passengerDTO).createResponseEntity();
    }
    @DeleteMapping("{passengerId}")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> deletePassenger(@PathVariable("passengerId") Long id)
            throws BookingException.NotFoundException {
        return passengerService.deletePassenger(id).createResponseEntity();
    }
}
