package com.sda.conference_room.repository;

import com.sda.conference_room.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrganizationRepository extends JpaRepository<Organization, Long> {

}
