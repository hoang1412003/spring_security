package com.example.spring_security.controllers;

import com.example.spring_security.models.User;
import com.example.spring_security.responses.ApiResponse;
import com.example.spring_security.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index() {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(userService.getAllUsers())
                .message("Get all users successfully")
                .status(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User user) throws Exception {
        ApiResponse apiResponse = ApiResponse.builder()
                .data(userService.login(user.getUsername(), user.getPassword()))
                .message("Login successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody User user, BindingResult result) throws Exception {
        if(result.hasErrors()) {
            List<String> erros = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(erros)
                    .message("Validation Failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }

        User newUser = userService.createUser(user);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(newUser)
                .message("User created successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
