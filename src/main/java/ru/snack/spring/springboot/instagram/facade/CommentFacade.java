package ru.snack.spring.springboot.instagram.facade;

import org.springframework.stereotype.Component;
import ru.snack.spring.springboot.instagram.entity.Comment;
import ru.snack.spring.springboot.instagram.dto.CommentDTO;

@Component
public class CommentFacade {

    public CommentDTO commentToCommentDTO (Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setMessage(comment.getMessage());
        return commentDTO;
    }
}
