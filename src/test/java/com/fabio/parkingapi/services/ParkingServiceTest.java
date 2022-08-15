package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    @InjectMocks
    private ParkingService parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    @Test
    @DisplayName("When called It must return a Parking")
    void returnTheConversionParkingDtoToParking(){
        ParkingDto parkingDto = new ParkingDto(1L,"WWW-9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
        Parking parking = parkingService.toParking(parkingDto);

        Assertions.assertEquals(parkingDto.getId(), parking.getId());
        Assertions.assertEquals(parkingDto.getLicense(),parking.getLicense());

    }

    @Test
    @DisplayName("When called It must return a ParkingDto")
    void returnTheConversionParkingToParkingDto(){
        Parking parking = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        ParkingDto parkingDto = parkingService.toParkingDto(parking);
        Assertions.assertEquals(parking.getId(),parkingDto.getId());
        Assertions.assertEquals(parking.getLicense(),parkingDto.getLicense());
        Assertions.assertEquals(parking.getColor(), parkingDto.getColor());
    }

    @Test
    @DisplayName("When called It must return a Parking.")
    void returnTheConversionNewParkingDtoToParking(){
        NewParkingDto newParkingDto = new NewParkingDto("WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        Parking parking = parkingService.toParking(newParkingDto);
        Assertions.assertEquals(newParkingDto.getLicense(),parking.getLicense());
        Assertions.assertEquals(newParkingDto.getModel(), parking.getModel());
        Assertions.assertEquals(newParkingDto.getPeriodType(),parking.getPeriodType());
    }

    @Test
    @DisplayName("When called It must return a list of PrkingDto.")
    void returnListParkingDto(){
        Parking carro1 = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        List<Parking> parkingList = List.of(carro1);
        Mockito.when(parkingRepository.findAll()).thenReturn(parkingList);
        List<ParkingDto> dtoList = parkingService.findAll();
        Assertions.assertEquals(parkingList.get(0).getId(),dtoList.get(0).getId());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a NewParkingDto. Save a Parking and return a ParkingDto")
    void returnPrkingDto(){

        NewParkingDto newParkingDto = new NewParkingDto("WQZ-3434","FIAT ARGO","RED",PeriodType.DIARIA);
        Parking parking = new Parking(1L,"WQZ-3434","FIAT ARGO","RED",PeriodType.DIARIA);

        Mockito.when(parkingRepository.save(parking)).thenReturn(parking);

        ParkingDto parkingDtoSaved = parkingService.saveParking(newParkingDto);

        Assertions.assertEquals(parking.getModel(),parkingDtoSaved.getModel());

    }


}
