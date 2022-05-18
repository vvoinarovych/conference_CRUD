package com.sda.conference_room.rest.controller;
import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations () {
        return ResponseEntity.ok(organizationService.getALlOrganizations());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById (@PathVariable("id") final Long id) {
        return ResponseEntity.ok(organizationService.getOrganizationDtoById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<OrganizationDto> addOrganization(@RequestBody @Valid final OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.saveOrganization(organizationDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") final Long id, @RequestBody @Valid final OrganizationDto organizationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.updateOrganization(id, organizationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") final Long id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}