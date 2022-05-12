package com.sda.conference_room.model.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(setterPrefix = "with")
@RequiredArgsConstructor
public class ReservationDto {

    @NotNull
    @Length(min = 2, max = 20)
    private Long id;
    private LocalDateTime starting;
    private LocalDateTime ending;

}
