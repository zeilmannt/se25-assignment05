package de.unibayreuth.se.campuscoffee.api.controller;

import de.unibayreuth.se.campuscoffee.api.dtos.UserDto;
import de.unibayreuth.se.campuscoffee.api.mapper.UserDtoMapper;
import de.unibayreuth.se.campuscoffee.domain.exceptions.UserNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.model.User;
import de.unibayreuth.se.campuscoffee.domain.ports.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@OpenAPIDefinition(
        info = @Info(
                title = "CampusCoffee",
                version = "0.0.1"
        )
)
@Tag(name = "Users")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    // implement the required endpoints here.
        private final UserService userService;
        private final UserDtoMapper userMapper;

        @GetMapping
        public List<UserDto> getAllUsers(){
                return userService.findAll().stream()
                        .map(userMapper::fromDomain)
                        .collect(Collectors.toList());
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserDto> getById(@PathVariable Long id) {
                try {
                        User user = userService.findById(id);
                        return ResponseEntity.ok(userMapper.fromDomain(user));
                } catch (UserNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/login/{login}")
        public ResponseEntity<UserDto> getByLogin(@PathVariable String loginName){
                try {
                        User user = userService.findByLogin(loginName);
                        return ResponseEntity.ok(userMapper.fromDomain(user));
                } catch (UserNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @PostMapping
        public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dtoUser){
                User createdUser = userService.create(userMapper.toDomain(dtoUser));
                return ResponseEntity.ok(userMapper.fromDomain(createdUser));
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto dtoUser){
                try {
                        User updatedUser = userService.update(id, userMapper.toDomain(dtoUser));
                        return ResponseEntity.ok(userMapper.fromDomain(updatedUser));
                } catch (UserNotFoundException e) {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/filter")
        public ResponseEntity<UserDto> filterByLogin(@RequestParam String loginName){
                try{
                        User user = userService.findByLogin(loginName);
                        return ResponseEntity.ok(userMapper.fromDomain(user));
                }
                catch (UserNotFoundException e){
                        return ResponseEntity.notFound().build();
                }
        }
}
