package com.sda.conference_room.model.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    private List<ConferenceRoom> conferenceRoomList;


}
