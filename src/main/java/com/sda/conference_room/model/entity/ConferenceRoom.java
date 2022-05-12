package com.sda.conference_room.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "conference_rooms")
public class ConferenceRoom {

    @Id
    @Column(name = "conference_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int level;

    private boolean availability;

    private Long sittingPlaces;

    private Long standingPlaces;

    @OneToMany(mappedBy = "conferenceRoom", cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    @ManyToOne
    private Organization organization;
}
