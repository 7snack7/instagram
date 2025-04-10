package ru.snack.spring.springboot.instagram.facade;

import org.springframework.stereotype.Component;
import ru.snack.spring.springboot.instagram.dto.PostDTO;
import ru.snack.spring.springboot.instagram.entity.Post;

@Component
public class PostFacade {

    public PostDTO postToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setLikes(post.getLikes());
        postDTO.setUsersLikes(post.getLikesUsers());
        postDTO.setLocation(post.getLocation());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
