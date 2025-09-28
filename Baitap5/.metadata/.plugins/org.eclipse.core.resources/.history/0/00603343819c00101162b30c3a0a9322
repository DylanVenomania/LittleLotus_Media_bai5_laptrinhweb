package com.littlelotus.controller;

import com.littlelotus.entity.User;
import com.littlelotus.service.VideoService;
import com.littlelotus.service.UserService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AppController 
{

    @Autowired
    private VideoService videoService;
    
    @Autowired
    private UserService userService; 

    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) 
    {
        HttpSession session = request.getSession(false);
        if (session != null) 
        {
            session.invalidate();
        }
        return "redirect:/login"; 
    }
    
    
    @GetMapping("/login")
    public String viewLoginPage() 
    {
        return "login"; 
    }
    
   
    @PostMapping("/login") 
    public String processLogin(
            @RequestParam("username") String username, 
            @RequestParam("password") String password, 
            HttpServletRequest request,
            RedirectAttributes ra) {
        
     
        User user = userService.findByUsername(username); 

        
        if (user != null && user.getPassword().equals(password)) 
        {
            
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInUser", user); 
            
            return "redirect:/"; 
        } 
        else 
        {
            ra.addFlashAttribute("errorMessage", "Tài khoản hoặc mật khẩu không đúng.");
            return "redirect:/login";
        }
    }
    
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("videos", videoService.findAll()); 
        return "home/index"; 
    }
}