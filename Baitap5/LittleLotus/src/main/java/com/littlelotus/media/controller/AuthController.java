package com.littlelotus.media.controller;

import com.littlelotus.media.dto.SessionUser;
import com.littlelotus.media.dto.forms.LoginForm;
import com.littlelotus.media.dto.forms.RegisterForm;
import com.littlelotus.media.entity.User;
import com.littlelotus.media.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("registerForm") RegisterForm form,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "register"; // trả về register.html
        }

        if (userService.userRepository.findByUsername(form.getUsername()).isPresent()) {
            model.addAttribute("error", "Username đã tồn tại");
            return "register";
        }

        if (userService.userRepository.findByEmail(form.getEmail()).isPresent()) {
            model.addAttribute("error", "Email đã tồn tại");
            return "register";
        }

        userService.register(form);
        return "redirect:/login"; // POST xong redirect tránh circular
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("loginForm") LoginForm form,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        return userService.login(form).map(user -> {
            session.setAttribute("user", new SessionUser(user));
            return "redirect:/"; // login thành công redirect về home
        }).orElseGet(() -> {
            model.addAttribute("error", "Invalid username hoặc password");
            return "login"; // login thất bại -> return view login
        });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
