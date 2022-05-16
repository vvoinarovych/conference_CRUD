package com.sda.conference_room.service.implementation;

import com.sda.conference_room.exception.NotFoundException;
import com.sda.conference_room.model.entity.Organization;
import com.sda.conference_room.repository.OrganizationRepository;
import com.sda.conference_room.model.request.UserRequest;
import com.sda.conference_room.model.entity.User;
import com.sda.conference_room.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public User createUser(final UserRequest userRequest) {
        final User user = new User();
        user.setUsername(userRequest.getName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User setOrganization(String userName, String orgName){
        Organization organization = organizationRepository.findOrganizationByName(orgName);
        User user = findUserByName(userName);
        user.setOrganization(organization);
        userRepository.save(user);
        System.out.println(userRepository.findUserByUsername(user.getUsername()));
        return user;

    }

    public User findUserByName(String name){
        return userRepository.findUserByUsername(name).orElseThrow(() -> new NotFoundException("User with such name not found"));
    }
}
