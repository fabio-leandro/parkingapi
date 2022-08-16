package com.fabio.parkingapi.controllers;


import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<ParkingDto>> findALl(){
        return ResponseEntity.ok(parkingService.findAll());
    }

    @PostMapping
    public ResponseEntity<ParkingDto> saveParking(@Valid @RequestBody NewParkingDto newParkingDto){
        ParkingDto parkingDto = parkingService.saveParking(newParkingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findById(@PathVariable Long id){
        ParkingDto parkingDto = parkingService.findById(id);
        return ResponseEntity.ok(parkingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDto> updateById(@PathVariable Long id, @Valid @RequestBody UpdateParkingDto updateParkingDto){
        ParkingDto dto = parkingService.updateById(id,updateParkingDto);
        return ResponseEntity.ok(dto);
    }




}
