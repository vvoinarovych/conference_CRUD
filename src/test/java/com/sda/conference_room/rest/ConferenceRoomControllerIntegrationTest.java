package com.sda.conference_room.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.OrganizationDto;
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
class ConferenceRoomControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        OrganizationDto organizationDto = OrganizationDto.builder()
                .withName("Kirin Tor")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationDto)))
                .andDo(print());
    }

    @Test
    @Order(1)
    void addConferenceRoomShouldAddConferenceRoomToDatabase() throws Exception {
        long organizationId = 1L;

        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withName("The Violet Hold")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/" + organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conferenceroom/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("The Violet Hold"));
    }

    @Test
    @Order(2)
    void addConferenceRoomShouldThrowExceptionIfNameNotUnique() throws Exception {
        long organizationId = 3L;

        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withName("The Violet Hold")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/"+ organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/"+ organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print())
                .andDo(print())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Name must be unique"))
                .andExpect(jsonPath("$.details").value("Conference room with that name already exists"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(3)
    void deleteConferenceRoomShouldDeleteRecordFromDatabase() throws Exception {
        long organizationId = 5L;

        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withName("The Violet Hold")
                .build();
        long conferenceRoomId = 6L;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/"+organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conferenceroom/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("The Violet Hold"));

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/api/conferenceroom/" + conferenceRoomId))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conferenceroom/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(4)
    void updateOrganizationShouldUpdateOrganization() throws Exception {
        long organizationId = 7L;

        ConferenceRoomDto conferenceRoomDto = ConferenceRoomDto.builder()
                .withName("The Violet Hold")
                .build();
        ConferenceRoomDto conferenceRoomDtoUpdate = ConferenceRoomDto.builder()
                .withName("Stratholme")
                .build();
        long conferenceRoomId = 8L;


        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/conferenceroom/add/"+organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/api/conferenceroom/"+conferenceRoomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(conferenceRoomDtoUpdate)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/conferenceroom/" + conferenceRoomId))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Stratholme"));

    }

}