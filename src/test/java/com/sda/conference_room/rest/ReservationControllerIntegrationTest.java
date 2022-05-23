package com.sda.conference_room.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.dto.ReservationDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() throws Exception {
        long organizationId = 1L;

        OrganizationDto organizationDto = OrganizationDto.builder()
                .withName("Apple")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationDto)))
                .andDo(print());

        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withName("Blue")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/" + organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print());
    }


    @Test
    @Order(1)
    void addReservationShouldAddReservationToDatabase() throws Exception {

        ReservationDto toAdd = ReservationDto.builder()
                .withId(3L)
                .withConferenceRoomDto(ConferenceRoomDto.builder().withId(2L).build())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/reservation/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/reservation/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(3L));
    }

    @Test
    @Order(2)
    void getAllReservationShouldReturnEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/reservation/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

}
