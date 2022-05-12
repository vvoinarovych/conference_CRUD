package com.sda.conference_room.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Length(min = 2, max = 20)
    private Long id;

    @NotNull
    private LocalDateTime starting;

    @NotNull
    private LocalDateTime ending;

    @ManyToOne
    private ConferenceRoom conferenceRoom;


}
