package com.sda.conference_room.rest.controller;
import com.sda.conference_room.model.dto.OrganizationDto;
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
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations () {
        return ResponseEntity.ok(organizationService.getALlOrganizations());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById (@PathVariable("id") Long id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.saveOrganization(organizationDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.updateOrganization(id, organizationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}