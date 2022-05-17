package com.sda.conference_room.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ConferenceRoomDto {

    private Long id;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long.")
    private String name;

    @Min(0)
    @Max(10)
    private int level;

    private boolean available;

    private Long sittingPlaces;

    private Long standingPlaces;

    private OrganizationDto organizationDto;
}
