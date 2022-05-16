package com.sda.conference_room.rest.controller;
import com.sda.conference_room.model.entity.User;
import com.sda.conference_room.service.implementation.UserService;
import com.sda.conference_room.model.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid final UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/org")
    public ResponseEntity<User> setOrg(@RequestBody String orgName, Principal principal) {
        String user = principal.getName();
        User result = userService.setOrganization(user, orgName);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser(Principal principal){
        String userName = principal.getName();
        User user = userService.findUserByName(userName);
        return ResponseEntity.ok().body(user);
    }

}
