package com.sda.conference_room.rest.controller;

import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;


    @GetMapping("/all")
    public ResponseEntity<List<Organization>> getAllOrganizations () {
        List<Organization> organizations = organizationService.getALlOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Organization> getOrganizationById (@PathVariable("id") Long id) {
        Organization organization = organizationService.getOrganizationById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
        Organization newOrganization = organizationService.saveOrganization(organization);
        return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable("id") Long id, @RequestBody Organization organization) {
        Organization updateOrganization = organizationService.updateOrganization(id, organization);
        return new ResponseEntity<>(updateOrganization, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        organizationService.deleteOrganizationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}