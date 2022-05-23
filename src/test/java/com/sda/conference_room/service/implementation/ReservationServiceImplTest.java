package com.sda.conference_room.service.implementation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @Test
    void testIsRoomAvailableInSpecificPeriod() {
        Organization organization = new Organization();
        organization.setConferenceRoomList(new ArrayList<>());
        organization.setId(123L);
        organization.setName("Name");

        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setAvailable(true);
        conferenceRoom.setId(123L);
        conferenceRoom.setLevel(1);
        conferenceRoom.setName("Name");
        conferenceRoom.setOrganization(organization);
        conferenceRoom.setReservationList(new ArrayList<>());
        conferenceRoom.setSittingPlaces(1L);
        conferenceRoom.setStandingPlaces(1L);
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        LocalDateTime end = LocalDateTime.of(1, 1, 1, 1, 1);
        assertTrue(
                this.reservationServiceImpl.isRoomAvailableInSpecificPeriod(conferenceRoom, start, end, new ArrayList<>()));
    }
}

