package ru.snack.spring.springboot.instagram.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDTO {
    private Long id;
    @NotEmpty
    private String message;
    private String username;
}
