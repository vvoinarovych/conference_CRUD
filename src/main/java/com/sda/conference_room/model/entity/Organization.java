package com.sda.conference_room.model.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long.")
    private String name;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    private List<ConferenceRoom> conferenceRoomList;
}
