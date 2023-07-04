package com.example.booking.controller;

import com.example.booking.dto.CityDTO;
import com.example.booking.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping
    public ResponseEntity<?> getCity(){
        return cityService.getCity().createResponseEntity();
    }
    @PostMapping
    public ResponseEntity<?> postCity(@RequestBody CityDTO cityDTO){
        return cityService.postCity(cityDTO).createResponseEntity();
    }
    @DeleteMapping("{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable("cityId") Long id){
        return cityService.deleteCity(id).createResponseEntity();
    }
    @PatchMapping("{cityId}")
    public ResponseEntity<?> patchCity(@PathVariable("cityId") Long id,
                                       @RequestBody CityDTO cityDTO){
        return cityService.patchCity(id,cityDTO).createResponseEntity();
    }
}