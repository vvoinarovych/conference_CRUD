package com.sda.conference_room.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "conference_rooms", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Reservation> reservationList;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
