package com.fabio.parkingapi.ParkingService;

import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

}
