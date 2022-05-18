package com.sda.conference_room.service;

import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import com.sda.conference_room.repository.OrganizationRepository;
import com.sda.conference_room.service.ConferenceRoomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConferenceRoomServiceIntegrationTest {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @BeforeAll
    static void setUp(@Autowired ConferenceRoomRepository conferenceRoomRepository, @Autowired OrganizationRepository organizationRepository){
        Organization organization1 = Organization.builder().withName("organization1").build();
        Organization organization2 = Organization.builder().withName("organization2").build();
        Organization organization3 = Organization.builder().withName("organization3").build();
        System.out.println(organization1);


        organizationRepository.save(organization1);
        organizationRepository.save(organization2);
        organizationRepository.save(organization3);

        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room1").withAvailable(true).withOrganization(organization1).build());
        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room2").withAvailable(true).withOrganization(organization2).build());
        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room3").withAvailable(false).withOrganization(organization2).build());
    }

    @Test
    void shouldReturn3WhenGetAllConferenceRooms(){
        assertThat(conferenceRoomService.getAllConferenceRooms()).hasSize(3);
    }

    @Test
    void shouldReturn2WhenGetAllAvailableConferenceRooms(){
        assertThat(conferenceRoomService.getAllAvailableConferenceRooms()).hasSize(2);
    }

    @Test
    void shouldReturn2WhenGetAllConferenceRoomsForSpecificOrganization(){
        assertThat(conferenceRoomService.getAllConferenceRoomsForSpecificOrganization(2L)).hasSize(2);
    }


}
