package com.littlelotus.controller;

import com.littlelotus.entity.Video;
import com.littlelotus.entity.Category;
import com.littlelotus.entity.User;
import com.littlelotus.service.VideoService;
import com.littlelotus.service.UserService;
import com.littlelotus.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/videos")
public class AdminVideoController 
{

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
    

    private void addDropdownData(Model model) 
    {
        model.addAttribute("categories", categoryService.findAll());
    }

    // 1. Hien thi danh sach video
    @GetMapping
    public String listVideos(Model model) 
    {
        model.addAttribute("videos", videoService.findAll());
        return "admin/videos/list"; 
    }

    // 2. Hiển thi form them moi
    @GetMapping("/new")
    public String showNewForm(Model model) 
    {
        addDropdownData(model); 
        model.addAttribute("video", new Video());
        model.addAttribute("pageTitle", "Thêm Video Mới");
        return "admin/videos/form"; 
    }

    // 3. Xu ly them/sua video
    @PostMapping("/save")
    public String saveVideo(@ModelAttribute Video video,@RequestParam("categoryId") Long categoryId, RedirectAttributes ra) 
    {
        try 
        {
            User adminUser = userService.findById(1L).orElseThrow(() -> new RuntimeException("Admin User (ID 1) không tồn tại."));
            video.setUploader(adminUser);
  
           
            Category category = categoryService.findById(categoryId)
                                .orElseThrow(() -> new RuntimeException("Category không hợp lệ."));
            video.setCategory(category);
            
            videoService.save(video); 
            
            ra.addFlashAttribute("message", "Video đã được đăng thành công !");
        } 
        catch (RuntimeException e) 
        {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/videos";
    }

    // 4. Hiển thị form sua video
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra)
    {
        try {
            Video video = videoService.findById(id)
                                .orElseThrow(() -> new RuntimeException("Video not found"));
            
            addDropdownData(model); 
            model.addAttribute("video", video);
            model.addAttribute("pageTitle", "Sửa Video (ID: " + id + ")");
            return "admin/videos/form"; 
        } 
        catch (RuntimeException e) 
        {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/videos";
        }
    }

    // 5. Xử lý Xóa Video
    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable("id") Long id, RedirectAttributes ra) 
    {
        try 
        {
            videoService.deleteById(id);
            ra.addFlashAttribute("message", "Video ID " + id + " đã được xóa thành công.");
        } 
        catch (Exception e) 
        {
            ra.addFlashAttribute("message", "Không thể xóa Video ID " + id + ".");
        }
        return "redirect:/admin/videos";
    }

    // 6. Tìm kiem video
    @GetMapping("/search")
    public String searchVideos(@RequestParam("keyword") String keyword, Model model) 
    {
        List<Video> videos = videoService.search(keyword);
        model.addAttribute("videos", videos);
        model.addAttribute("pageTitle", "Kết quả tìm kiếm cho: " + keyword);
        return "admin/videos/list"; 
    }
}