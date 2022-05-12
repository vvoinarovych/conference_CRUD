package com.sda.conference_room.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "organizations")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//---------------

    private String password;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    private List<ConferenceRoom> conferenceRoomList;


}
