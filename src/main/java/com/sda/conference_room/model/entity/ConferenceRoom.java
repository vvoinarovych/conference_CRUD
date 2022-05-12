package com.sda.conference_room.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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

    private String name;

    private int level;

    private boolean available;

    private Long sittingPlaces;

    private Long standingPlaces;

    @OneToMany(mappedBy = "conferenceRoom", cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    @ManyToOne
    private Organization organization;
}
