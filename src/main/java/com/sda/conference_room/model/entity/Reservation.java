package com.sda.conference_room.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    private Long id;

    private LocalDateTime starting;

    private LocalDateTime ending;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "conference_room_id")
    private ConferenceRoom conferenceRoom;



}
