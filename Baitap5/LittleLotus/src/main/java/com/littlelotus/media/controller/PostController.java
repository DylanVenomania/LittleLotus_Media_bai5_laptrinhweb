package com.littlelotus.media.controller;

import com.littlelotus.media.dto.SessionUser;
import com.littlelotus.media.dto.forms.PostForm;
import com.littlelotus.media.entity.Post;
import com.littlelotus.media.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "newpost";
    }

    @PostMapping("/new")
    public String createPost(@Valid @ModelAttribute("postForm") PostForm form,
                             BindingResult bindingResult,
                             HttpSession session,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "newpost";
        }

        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        postService.createPost(form, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index"; // hiển thị feed
    }
}
