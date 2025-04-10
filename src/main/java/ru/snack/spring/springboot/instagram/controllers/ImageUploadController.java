package ru.snack.spring.springboot.instagram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.snack.spring.springboot.instagram.entity.ImageModel;
import ru.snack.spring.springboot.instagram.payload.response.MessageResponse;
import ru.snack.spring.springboot.instagram.services.ImageUploadService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageUploadController {
    @Autowired
    ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                       Principal principal) throws IOException {
        imageUploadService.uploadImageToUser(file, principal);
        return ResponseEntity.ok(new MessageResponse("Image uploaded successfully"));
    }

    @PostMapping("/{postId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(@PathVariable("postId") String postId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        imageUploadService.uploadImageToPost(file, principal, Long.parseLong(postId));
        return ResponseEntity.ok(new MessageResponse("Image uploaded successfully"));
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal) {
        ImageModel userImage = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{postId}/image")
    public ResponseEntity<ImageModel> getImageToPost(@PathVariable("postId") String postId) {
        ImageModel postImage = imageUploadService.getImageToPost(Long.parseLong(postId));
        return new ResponseEntity<>(postImage, HttpStatus.OK);
    }

}
