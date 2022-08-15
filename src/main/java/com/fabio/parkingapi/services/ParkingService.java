package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public List<ParkingDto> findAll(){
        return parkingRepository.findAll().stream().map(this::toParkingDto).collect(Collectors.toList());
    }

    public ParkingDto saveParking(NewParkingDto newParkingDto){
        Parking parking = toParking(newParkingDto);
        parkingRepository.save(parking);
        return toParkingDto(parking);
    }

    public Parking toParking(ParkingDto dto){
        return new Parking(dto.getId(), dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public Parking toParking(NewParkingDto dto){
        return new Parking(null, dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public ParkingDto toParkingDto(Parking parking){
        return new ParkingDto(parking.getId(),parking.getLicense(), parking.getModel(), parking.getColor(),
                parking.getEntrance(),parking.getExit(),parking.getPeriodType(),parking.getBill());
    }

}
