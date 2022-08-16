package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    @InjectMocks
    private ParkingService parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    private ParkingDto parkingDto;
    private Parking parking;
    private NewParkingDto newParkingDto;
    private Long fakeId;
    private UpdateParkingDto updateParkingDto;

    @BeforeEach
    void setup(){
        parkingDto = new ParkingDto(1L,"WWW-9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
        parking = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        newParkingDto = new NewParkingDto("WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        updateParkingDto = new UpdateParkingDto(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        fakeId = 1L;
    }

    @Test
    @DisplayName("When called It must return a Parking")
    void returnTheConversionParkingDtoToParking(){
        Parking parking = parkingService.toParking(parkingDto);
        Assertions.assertEquals(parkingDto.getId(), parking.getId());
        Assertions.assertEquals(parkingDto.getLicense(),parking.getLicense());

    }

    @Test
    @DisplayName("When called It must return a ParkingDto")
    void returnTheConversionParkingToParkingDto(){
        ParkingDto parkingDto = parkingService.toParkingDto(parking);
        Assertions.assertEquals(parking.getId(),parkingDto.getId());
        Assertions.assertEquals(parking.getLicense(),parkingDto.getLicense());
        Assertions.assertEquals(parking.getColor(), parkingDto.getColor());
    }

    @Test
    @DisplayName("When called It must return a Parking.")
    void returnTheConversionNewParkingDtoToParking(){
        Parking parking = parkingService.toParking(newParkingDto);
        Assertions.assertEquals(newParkingDto.getLicense(),parking.getLicense());
        Assertions.assertEquals(newParkingDto.getModel(), parking.getModel());
        Assertions.assertEquals(newParkingDto.getPeriodType(),parking.getPeriodType());
    }

    @Test
    @DisplayName("When called It must return a Parking.")
    void returnTheConversionUpdateParkingDtoToParking(){
        Parking parking = parkingService.toParking(updateParkingDto);
        Assertions.assertEquals(updateParkingDto.getLicense(),parking.getLicense());
        Assertions.assertEquals(updateParkingDto.getModel(), parking.getModel());
        Assertions.assertEquals(updateParkingDto.getPeriodType(),parking.getPeriodType());
    }

    @Test
    @DisplayName("When called It must return a list of PrkingDto.")
    void returnListParkingDto(){
        List<Parking> parkingList = List.of(parking);
        Mockito.when(parkingRepository.findAll()).thenReturn(parkingList);
        List<ParkingDto> dtoList = parkingService.findAll();
        Assertions.assertEquals(parkingList.get(0).getId(),dtoList.get(0).getId());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a NewParkingDto. Save a Parking and return a ParkingDto")
    void returnPrkingDto(){
        Mockito.when(parkingRepository.save(parking)).thenReturn(parking);
        ParkingDto parkingDtoSaved = parkingService.saveParking(newParkingDto);
        Assertions.assertEquals(parking.getModel(),parkingDtoSaved.getModel());
    }

    @Test
    @DisplayName("When called It must get a ID and return a ParkingDto.")
    void returnParkingDtoById(){
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.ofNullable(parking));
        ParkingDto dto = parkingService.findById(fakeId);
        Assertions.assertEquals(dto.getId(), parking.getId());
        Assertions.assertEquals(dto.getLicense(),parking.getLicense());
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.findById(11L));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a ID, UpdateParkingDto and Return ParkingDto.")
    void shouldUpdateParkingAndReturnParkingDto(){
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.ofNullable(parking));
        Mockito.when(parkingRepository.save(parking)).thenReturn(parking);
        ParkingDto dto = parkingService.updateById(fakeId,updateParkingDto);
        Assertions.assertEquals(parking.getId(),dto.getId());
        Assertions.assertEquals(parking.getLicense(),dto.getLicense());
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.updateById(11L,updateParkingDto));
    }

}










