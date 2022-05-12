package com.sda.conference_room.model.dto;
import lombok.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrganizationDto {

    private Long id;

    @NotNull
    @Min(2)
    @Max(20)
    private String name;

    private String password;
}
