package com.sda.conference_room.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.repository.OrganizationRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class OrganizationControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository orgRepo;


    @Test
    @Order(1)
    void getAllOrganizationShouldReturnEmptyList () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    void addOrganizationShouldAddOrganizationToDatabase() throws Exception {
        OrganizationDto toAdd = OrganizationDto.builder()
                .withName("Gooooooooooogle")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Gooooooooooogle"));
    }


    @Test
    @Order(3)
    void updateOrganizationShouldUpdateOrganization() throws Exception {
        OrganizationDto toAdd = OrganizationDto.builder()
                .withName("Java")
                .build();
        OrganizationDto toUpdate = OrganizationDto.builder()
                .withName("Jakarta")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Java"));

        long idForUpdate = orgRepo.findOrganizationByName("Java").getId();

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:" + port + "/api/organization/" + idForUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toUpdate)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Jakarta"));

    }

    @Test
    @Order(4)
    void deleteOrganizationShouldDeleteRecordsFromDatabase() throws Exception {
        OrganizationDto toAdd = OrganizationDto.builder()
                .withName("Umbrella")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").value("Umbrella"));

        long idToRemove = orgRepo.findOrganizationByName("Umbrella").getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/api/organization/" + idToRemove))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/all"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(5)
    void getOrganizationByIdShouldReturnNotFoundException () throws Exception {
        OrganizationDto toAdd = OrganizationDto.builder()
                .withName("Ancient Rome")
                .build();
        long idToFind = 17;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toAdd)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/organization/" + idToFind))
                .andDo(print())
                .andExpect(jsonPath("$.httpStatus").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Entity not found"))
                .andExpect(jsonPath("$.details").value("Organization with given id not found"))
                .andExpect(status().is4xxClientError());
    }

}