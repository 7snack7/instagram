package ru.snack.spring.springboot.instagram.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String token;
}
