package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.NewPriceTableDto;
import com.fabio.parkingapi.dtos.PriceTableDto;
import com.fabio.parkingapi.dtos.UpdatePriceTableDto;
import com.fabio.parkingapi.services.PriceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/prices")
public class PriceTableController {

    @Autowired
    private PriceTableService priceTableService;

    @GetMapping
    public ResponseEntity<List<PriceTableDto>> findAll(){
        return ResponseEntity.ok(priceTableService.findAll());
    }

    @PostMapping
    public ResponseEntity<PriceTableDto> save(@Valid @RequestBody NewPriceTableDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(priceTableService.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceTableDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(priceTableService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceTableDto> updateById(@PathVariable Long id, @RequestBody UpdatePriceTableDto dto){
        return ResponseEntity.ok(priceTableService.updateById(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        priceTableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
