package com.sda.conference_room.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "conference_rooms")
@Builder(setterPrefix = "with")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceRoom {

    @Id
    @Column(name = "conference_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long.")
    private String name;

    @Min(0)
    @Max(10)
    private int level;

    private boolean available;

    private Long sittingPlaces;

    private Long standingPlaces;

    @OneToMany(mappedBy = "conferenceRoom", cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Organization organization;


}
