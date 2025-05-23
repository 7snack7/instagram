package ru.snack.spring.springboot.instagram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageBytes;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long postId;
}
