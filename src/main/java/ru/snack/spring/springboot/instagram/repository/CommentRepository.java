package ru.snack.spring.springboot.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.snack.spring.springboot.instagram.entity.Comment;
import ru.snack.spring.springboot.instagram.entity.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    Comment findByIdAndUserId(Long commentId, Long userId);

    List<Comment> findALLByUserId(Long userId);

}
