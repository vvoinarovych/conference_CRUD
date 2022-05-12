package com.sda.conference_room.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id")
    private Long id;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "organizations", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Reservation> reservationList;


}
