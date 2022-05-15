package com.sda.conference_room.service.implementation;

import com.sda.conference_room.exception.NameIsNotUniqueException;
import com.sda.conference_room.exception.NotFoundException;
import com.sda.conference_room.mapper.ConferenceRoomMapper;
import com.sda.conference_room.model.dto.ConferenceRoomDto;
import com.sda.conference_room.model.entity.ConferenceRoom;
import com.sda.conference_room.repository.ConferenceRoomRepository;
import com.sda.conference_room.service.ConferenceRoomService;
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

    @Override
    public List<ConferenceRoomDto> getAllConferenceRooms() {
        log.info("Loading all conference rooms.");
        List<ConferenceRoom> foundConferenceRooms = conferenceRoomRepository.findAll();

        return foundConferenceRooms.stream()
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
    public ConferenceRoomDto addConferenceRoom(final ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoomFromDb = conferenceRoomRepository.findConferenceRoomByName(conferenceRoomDto.getName());
        if(conferenceRoomFromDb == null) {
            log.info("Saving conference room with id: {}", conferenceRoomDto.getId());
            final ConferenceRoom conferenceRoom = ConferenceRoomMapper.map(conferenceRoomDto);
            final ConferenceRoom addedConferenceRoom = conferenceRoomRepository.save(conferenceRoom);
            return ConferenceRoomMapper.map(addedConferenceRoom);
        }
        throw new NameIsNotUniqueException("Conference room with that name already exists");
    }

    @Override
    public ConferenceRoomDto updateConferenceRoom(final ConferenceRoomDto conferenceRoomDto) {
        log.info("Updating conference room with id: {}", conferenceRoomDto.getId());
        getConferenceRoomFromDataBase(conferenceRoomDto.getId());
        final ConferenceRoom conferenceRoom = ConferenceRoomMapper.map(conferenceRoomDto);
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
