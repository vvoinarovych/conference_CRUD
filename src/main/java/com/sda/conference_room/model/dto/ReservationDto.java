package com.sda.conference_room.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@RequiredArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;

    @NotNull
    private LocalDateTime starting;

    @NotNull
    private LocalDateTime ending;

    @NotBlank(message = "Reservation identifier must not be blank.")
    @Size(min = 2, max = 20, message = "Reservation identifier must be between 2 and 20 characters long.")
    private String reservationIdentifier;

}
