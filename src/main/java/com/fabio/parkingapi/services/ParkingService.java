package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public List<ParkingDto> findAll(){
        return parkingRepository.findAll().stream().map(this::toParkingDto).collect(Collectors.toList());
    }


    public Parking toParking(ParkingDto dto){
        return new Parking(dto.getId(), dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public ParkingDto toParkingDto(Parking parking){
        return new ParkingDto(parking.getId(),parking.getLicense(), parking.getModel(), parking.getColor(),
                parking.getEntrance(),parking.getExit(),parking.getPeriodType(),parking.getBill());
    }

}
