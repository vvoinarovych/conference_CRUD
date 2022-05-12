package com.sda.conference_room.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime starting;

    private LocalDateTime ending;

    @ManyToOne
    private ConferenceRoom conferenceRoom;



}
