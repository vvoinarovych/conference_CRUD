package com.sda.conference_room.rest.controller;

import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.dto.ReservationDto;
import com.sda.conference_room.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/all/{organizationId}")
    public ResponseEntity<List<ReservationDto>> getAllReservationsForOrganization(@PathVariable("organizationId") Long id){
        return ResponseEntity.ok(reservationService.getAllReservationsByOrganizationId(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody @Valid ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationDto));
    }

    @PutMapping("/update")//not usable
    public ResponseEntity<ReservationDto> updateOrganization(@RequestBody @Valid ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/by/{conferenceRoomName}")
    public ResponseEntity<List<ReservationDto>> getAllReservationsByConferenceRoomName(@PathVariable("conferenceRoomName") Long conferenceRoomId) {
        return ResponseEntity.ok().body(reservationService.getAllReservationsByConferenceRoomId(conferenceRoomId));
    }

    @PostMapping("/period/{organizationName}")
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(@PathVariable("organizationName") String organizationName,
                                                                                                                @RequestBody ReservationDto reservationDto) {
        LocalDateTime start = reservationDto.getStarting();
        LocalDateTime end = reservationDto.getEnding();

        return ResponseEntity.ok().body(reservationService.getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(organizationName, start,  end));
    }
}
