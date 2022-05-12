package com.sda.conference_room.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @Size(min = 2, max = 20)
    private String name;

    private String password;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<ConferenceRoom> conferenceRoomList;
}
