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

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody @Valid ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationDto));
    }

    @PutMapping("/{reservationId}")//not usable
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("reservationId") Long reservationId, @RequestBody @Valid ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservationId, reservationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/by/{conferenceRoomId}")
    public ResponseEntity<List<ReservationDto>> getAllReservationsByConferenceRoomId(@PathVariable("conferenceRoomId") Long conferenceRoomId) {
        return ResponseEntity.ok().body(reservationService.getAllReservationsByConferenceRoomId(conferenceRoomId));
    }

    @PostMapping("/period/{organizationId}")
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(@PathVariable("organizationId") Long organizationId,
                                                                                                                 @RequestBody ReservationDto reservationDto) {
        LocalDateTime start = reservationDto.getStarting();
        LocalDateTime end = reservationDto.getEnding();

        return ResponseEntity.ok().body(reservationService.getAllConferenceRoomsForSpecificOrganizationForSpecificPeriod(organizationId, start,  end));
    }
}
