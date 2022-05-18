package com.sda.conference_room.service.implementation;

import com.sda.conference_room.exception.NameIsNotUniqueException;
import com.sda.conference_room.exception.NotFoundException;
import com.sda.conference_room.mapper.ConferenceRoomMapper;
import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import com.sda.conference_room.service.ConferenceRoomService;
import com.sda.conference_room.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationService organizationService;

    @Override
    public List<ConferenceRoomDto> getAllAvailableConferenceRooms() {
        log.info("Loading all currently available conference rooms.");
        return conferenceRoomRepository.findAll()
                .stream()
                .filter(ConferenceRoom::isAvailable)
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConferenceRoomDto> getAllConferenceRoomsForSpecificOrganization(String organizationName) {
        log.info("Loading all  conference rooms for organization: {}.", organizationName);
        return conferenceRoomRepository.findAll()
                .stream()
                .filter(room -> room.getOrganization().getName().equals(organizationName))
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConferenceRoomDto> getAllConferenceRooms() {
        log.info("Loading all conference rooms.");
        return conferenceRoomRepository.findAll()
                .stream()
                .map(ConferenceRoomMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ConferenceRoomDto getConferenceRoomById(final Long id) {
        log.info("Loading conference room by id: {}", id);
        final ConferenceRoom conferenceRoom = getConferenceRoomFromDataBase(id);

        return ConferenceRoomMapper.map(conferenceRoom);
    }

    @Override
    public ConferenceRoomDto createConferenceRoom(final Long organizationId, final ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoomFromDb = conferenceRoomRepository.findConferenceRoomByName(conferenceRoomDto.getName());
        if (conferenceRoomFromDb == null) {
            log.info("Saving conference room with id: {}", conferenceRoomDto.getId());
            Organization organization = organizationService.getOrganizationById(organizationId);
            ConferenceRoom conferenceRoom = ConferenceRoomMapper.map(conferenceRoomDto);
            conferenceRoom.setOrganization(organization);
            ConferenceRoom addedConferenceRoom = conferenceRoomRepository.save(conferenceRoom);
            return ConferenceRoomMapper.map(addedConferenceRoom);
        }
        throw new NameIsNotUniqueException("Conference room with that name already exists");
    }

    @Override
    public ConferenceRoomDto updateConferenceRoom(Long conferenceRoomId, ConferenceRoomDto conferenceRoomDto) {
        log.info("Updating conference room with id: {}", conferenceRoomDto.getId());
        ConferenceRoom conferenceRoomFromDataBase = getConferenceRoomFromDataBase(conferenceRoomId);
        ConferenceRoom conferenceRoom = ConferenceRoomMapper.map(conferenceRoomDto);
        conferenceRoom.setId(conferenceRoomFromDataBase.getId());
        conferenceRoom.setOrganization(conferenceRoomFromDataBase.getOrganization());
        ConferenceRoom updatedConferenceRoom = conferenceRoomRepository.save(conferenceRoom);
        return ConferenceRoomMapper.map(updatedConferenceRoom);
    }

    @Override
    public void deleteConferenceRoomById(final Long id) {
        log.info("Deleting conference room by id: {}", id);
        conferenceRoomRepository.deleteById(id);
    }

    private ConferenceRoom getConferenceRoomFromDataBase(final Long id) {
        final Optional<ConferenceRoom> conferenceRoomFromDataBase = conferenceRoomRepository.findById(id);
        return conferenceRoomFromDataBase.orElseThrow(() -> new NotFoundException("Conference Room with given id not found"));
    }
}
