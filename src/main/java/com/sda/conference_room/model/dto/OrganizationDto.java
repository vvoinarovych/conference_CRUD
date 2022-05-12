package com.sda.conference_room.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrganizationDto {

    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    private String password;
}
