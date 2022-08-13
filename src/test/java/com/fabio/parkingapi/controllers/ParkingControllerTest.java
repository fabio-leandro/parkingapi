package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.services.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
                        .content(objectMapper.writeValueAsString(dtoList.get(0))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(dtoList.get(0).getId().intValue())));
    }


}
