package com.sda.conference_room.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "conference_rooms")
@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ConferenceRoom {

    @Id
    @Column(name = "conference_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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

    @ManyToOne
    private Organization organization;
}
