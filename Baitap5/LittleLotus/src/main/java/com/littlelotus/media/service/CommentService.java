package com.littlelotus.media.service;

import com.littlelotus.media.entity.Comment;
import com.littlelotus.media.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getByPost(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
