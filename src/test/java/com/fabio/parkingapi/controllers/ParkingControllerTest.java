package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.services.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ParkingControllerTest {

    private static final String VERSION_APP = "v1";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    @Autowired
    private ObjectMapper objectMapper;

    private ParkingDto parkingDto;
    private NewParkingDto newParkingDto;
    private UpdateParkingDto updateParkingDto;
    private Long fakeId;

    @BeforeEach
    void setup(){
      parkingDto = new ParkingDto(1L,"WWW-9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
      newParkingDto = new NewParkingDto("WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
      updateParkingDto = new UpdateParkingDto(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        fakeId = 1L;
    }

    @Test
    @DisplayName("It must return status OK and a List of ParkingDto")
    void returnResponseListOfParkingDto() throws Exception {
        List<ParkingDto> dtoList = List.of(this.parkingDto);
        Mockito.when(parkingService.findAll()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/" + VERSION_APP + "/parkings")
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dtoList.get(0))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(dtoList.get(0).getId().intValue())));
    }

    @Test
    @DisplayName("It must return status CREATED and a body with ParkingDto.")
    void returnResponseStatusCreatedAndBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.saveParking(newParkingDto)).thenReturn(parkingDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/" + VERSION_APP + "/parkings")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newParkingDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        var response = parkingController.saveParking(newParkingDto);
        Assertions.assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        Assertions.assertEquals(parkingDto,response.getBody());
    }

    @Test
    @DisplayName("It must return status OK and a body with ParkingDto")
    void returnResponseStatusOkAndBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.findById(fakeId)).thenReturn(parkingDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/" + VERSION_APP + "/parkings/"+fakeId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        var response = parkingController.findById(fakeId);
        Assertions.assertEquals(ResponseEntity.ok().body(parkingDto),response);
    }

    @Test
    @DisplayName("When called It must update a Parking and return status ok with body of ParkingDto")
    void shouldReturnResponseStatusOkAndResponseBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.updateById(fakeId,updateParkingDto)).thenReturn(parkingDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/" + VERSION_APP + "/parkings/"+fakeId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(parkingDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        var response = parkingController.updateById(fakeId,updateParkingDto);
        Assertions.assertEquals(ResponseEntity.ok(parkingDto),response);

    }

    @Test
    @DisplayName("When called It must delete Parking and return status NoContent.")
    void shouldDeleteParking() throws Exception {
        Mockito.doNothing().when(parkingService).deleteById(fakeId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/" + VERSION_APP + "/parkings/"+fakeId)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        var response = parkingController.deleteById(fakeId);
        Assertions.assertEquals(ResponseEntity.noContent().build(),response);

    }


}
