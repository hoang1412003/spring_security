package com.example.spring_security.configs;

import java.io.IOException;

public class ResourceNotFoundException extends IOException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
