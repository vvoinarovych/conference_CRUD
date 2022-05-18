package com.sda.conference_room.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private LocalDateTime starting;
    private LocalDateTime ending;
    private ConferenceRoomDto conferenceRoomDto;

}
