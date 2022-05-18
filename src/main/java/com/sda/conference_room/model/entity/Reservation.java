package com.sda.conference_room.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reservations")

public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime starting;

    private LocalDateTime ending;

    @ManyToOne
    private ConferenceRoom conferenceRoom;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                '}';
    }
}
