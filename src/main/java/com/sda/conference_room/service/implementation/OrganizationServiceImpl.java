package com.sda.conference_room.service.implementation;

import com.sda.conference_room.exception.NameIsNotUniqueException;
import com.sda.conference_room.exception.NotFoundException;
import com.sda.conference_room.utils.mapper.OrganizationMapper;
import com.sda.conference_room.model.dto.OrganizationDto;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.OrganizationRepository;
import com.sda.conference_room.service.OrganizationService;
import com.sda.conference_room.utils.validation.OrganizationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationValidator organizationValidator;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        if (organizationValidator.isValid(organizationDto)) {
            Organization organizationToSave = organizationRepository.save(OrganizationMapper.map(organizationDto));
            log.info("Organization with name {} saved", organizationToSave.getName());
            return OrganizationMapper.map(organizationToSave);
        }
        throw new NameIsNotUniqueException("Organization with that name already exists");
    }

    @Override
    public List<OrganizationDto> getALlOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(OrganizationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDto getOrganizationDtoById(Long organizationId) {
        Organization organization = getOrganizationById(organizationId);
        log.info("Organization with id {} was found", organizationId);
        return OrganizationMapper.map(organization);
    }

    @Override
    public OrganizationDto updateOrganization(Long organizationId, OrganizationDto organizationDto) {
        if (organizationValidator.isValid(organizationDto)) {
            final Organization organizationFromRequest = OrganizationMapper.map(organizationDto);
            organizationFromRequest.setId(organizationId);
            organizationRepository.save(organizationFromRequest);
            log.info("Organization with id {} updated", organizationId);
            return OrganizationMapper.map(organizationFromRequest);
        }
        throw new NameIsNotUniqueException("Organization with that name already exists");
    }

    @Override
    public void deleteOrganizationById(Long organizationId) {
        organizationRepository.delete(getOrganizationById(organizationId));
        log.info("Organization with id {} deleted", organizationId);
    }

    public Organization getOrganizationById(Long organizationId) {
        final Optional<Organization> organizationFromDatabase = organizationRepository.findById(organizationId);
        return organizationFromDatabase.orElseThrow(() -> new NotFoundException("Organization with given id not found"));
    }
}
