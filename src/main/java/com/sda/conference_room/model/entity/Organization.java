package com.sda.conference_room.model.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;


@Entity
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "organizations")
public class Organization {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    @Min(2)
    @Max(20)
    private String name;

    private String password;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL)
    private List<ConferenceRoom> conferenceRoomList;
}
