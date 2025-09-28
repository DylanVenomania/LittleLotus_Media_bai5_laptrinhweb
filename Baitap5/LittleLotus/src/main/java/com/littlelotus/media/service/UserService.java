package com.littlelotus.media.service;

import com.littlelotus.media.dto.forms.LoginForm;
import com.littlelotus.media.dto.forms.RegisterForm;
import com.littlelotus.media.entity.User;
import com.littlelotus.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
	public UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Đăng ký user mới
    public User register(RegisterForm form) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    // Login
    public Optional<User> login(LoginForm form) {
        Optional<User> userOpt = userRepository.findByUsername(form.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(form.getPassword(), user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
