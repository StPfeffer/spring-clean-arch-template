package com.pfeffer.springcleanarchtemplate.presentation.controller;

import com.pfeffer.springcleanarchtemplate.domain.entity.dto.request.UserRequestDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.PaginatedResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.infra.adapter.SpringPageableAdapter;
import com.pfeffer.springcleanarchtemplate.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO user = service.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email) {
        UserResponseDTO user = service.findByEmail(email);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = service.findAll();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponseDTO<UserResponseDTO>> findAll(Pageable pageable) {
        PaginatedResponseDTO<UserResponseDTO> users = service.findAll(new SpringPageableAdapter(pageable));

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("username/{username}")
    public ResponseEntity<UserResponseDTO> findByUsername(@PathVariable String username) {
        UserResponseDTO user = service.findByUsername(username);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO dto) {
        UserResponseDTO createdUser = service.save(dto);

        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> updateById(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        UserResponseDTO updatedUser = service.updateById(id, dto);

        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserResponseDTO> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
