package com.fabio.parkingapi.controllers;


import com.fabio.parkingapi.ParkingService.ParkingService;
import com.fabio.parkingapi.entities.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<Parking>> findALl(){
        return ResponseEntity.ok(parkingService.findAll());
    }
}
