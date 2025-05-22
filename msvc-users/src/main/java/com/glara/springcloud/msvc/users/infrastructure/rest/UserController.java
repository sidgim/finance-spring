package com.glara.springcloud.msvc.users.infrastructure.rest;

import com.glara.springcloud.commons.dto.UserDTO;
import com.glara.springcloud.msvc.users.application.dto.CreateUserRequest;
import com.glara.springcloud.msvc.users.application.mapper.UserMapper;
import com.glara.springcloud.msvc.users.application.services.UserService;
import com.glara.springcloud.msvc.users.domain.entities.User;
import com.glara.springcloud.msvc.users.domain.exceptions.ResourceNotFoundException;
import com.glara.springcloud.msvc.users.infrastructure.rest.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService svc;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserController(UserService svc, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.svc = svc;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> create(@RequestBody CreateUserRequest request) {
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = svc.create(user);
        UserDTO responseDto = userMapper.toDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Usuario creado exitosamente", responseDto));
    }



    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getById(@PathVariable UUID id) {
        User user = svc.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        ApiResponse<User> response = ApiResponse.success("Usuario encontrado", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAll() {
        List<User> users = svc.findAll();
        List<UserDTO> responseDto = userMapper.toDTOList(users);
        ApiResponse<List<UserDTO>> response = ApiResponse.success("Usuarios recuperados exitosamente", responseDto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> update(@PathVariable UUID id,
                                       @RequestBody User user) {
        User updated = svc.update(id, user);
        ApiResponse<User> response = ApiResponse.success("Usuario actualizado exitosamente", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        svc.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(204, true, "Usuario eliminado exitosamente", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
