package com.sda.conference_room.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    //@NotNull
    @Column(name = "starting")
    private LocalDateTime starting;

    //@NotNull
    @Column(name = "ending")
    private LocalDateTime ending;

    //@NotBlank(message = "Reservation identifier must not be blank.")
    @Size(min = 2, max = 20, message = "Reservation identifier must be between 2 and 20 characters long.")
    @Column(name = "reservation_identifier")
    private String reservationIdentifier;

    @ManyToOne
    private ConferenceRoom conferenceRoom;


}
