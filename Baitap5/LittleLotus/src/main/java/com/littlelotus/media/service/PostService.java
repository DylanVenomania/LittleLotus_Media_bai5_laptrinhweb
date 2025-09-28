package com.littlelotus.media.service;

import com.littlelotus.media.dto.SessionUser;
import com.littlelotus.media.dto.forms.PostForm;
import com.littlelotus.media.entity.Post;
import com.littlelotus.media.entity.User;
import com.littlelotus.media.repository.PostRepository;
import com.littlelotus.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(PostForm form, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow();
        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
