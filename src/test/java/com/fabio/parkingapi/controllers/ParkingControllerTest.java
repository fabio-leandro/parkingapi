package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.services.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

@WebMvcTest
public class ParkingControllerTest {

    private static final String VERSION_APP = "v1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("It must return status OK and a List of ParkingDto")
    void returnResponseListOfParkingDto() throws Exception {
        ParkingDto parkingDto = new ParkingDto(1L,"WWW-9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
        List<ParkingDto> dtoList = List.of(parkingDto);

        Mockito.when(parkingService.findAll()).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/" + VERSION_APP + "/parkings")
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dtoList)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }









}
