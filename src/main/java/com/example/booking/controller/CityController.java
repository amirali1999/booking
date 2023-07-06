package com.example.booking.controller;

import com.example.booking.dto.CityDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.service.CityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
@SecurityRequirement(name = "token")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public ResponseEntity<?> getCity(){
        return cityService.getCity().createResponseEntity();
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> postCity(@RequestBody CityDTO cityDTO) throws BookingException.DuplicateFieldException {
        return cityService.postCity(cityDTO).createResponseEntity();
    }
    @DeleteMapping("{cityId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> deleteCity(@PathVariable("cityId") Long id) throws BookingException.NotFoundException {
        return cityService.deleteCity(id).createResponseEntity();
    }
    @PatchMapping("{cityId}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<?> patchCity(@PathVariable("cityId") Long id,
                                       @RequestBody CityDTO cityDTO)
            throws BookingException.NotFoundException, BookingException.DuplicateFieldException {
        return cityService.patchCity(id,cityDTO).createResponseEntity();
    }
}
