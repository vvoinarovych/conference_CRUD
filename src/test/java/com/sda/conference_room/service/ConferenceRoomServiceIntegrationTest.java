package com.sda.conference_room.service;

import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.repository.ConferenceRoomRepository;
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
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @BeforeAll
    static void setUp(@Autowired ConferenceRoomRepository conferenceRoomRepository){
        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room1").withAvailable(true).build());
        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room2").withAvailable(true).build());
        conferenceRoomRepository.save(ConferenceRoom.builder().withName("room3").withAvailable(false).build());
    }

    @Test
    void shouldReturn3WhenGetAllConferenceRooms(){
        assertThat(conferenceRoomService.getAllConferenceRooms()).hasSize(3);
    }

    @Test
    void shouldReturn2WhenGetAllAvailableConferenceRooms(){
        assertThat(conferenceRoomService.getAllAvailableConferenceRooms()).hasSize(2);
    }


}
