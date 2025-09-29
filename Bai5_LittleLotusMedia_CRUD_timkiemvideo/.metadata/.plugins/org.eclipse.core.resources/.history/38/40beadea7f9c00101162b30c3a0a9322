package com.littlelotus.controller;

import com.littlelotus.entity.Category;
import com.littlelotus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categories") 
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1. Hiển thị Danh sách Categories
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        // Đã sửa: Trả về đường dẫn đầy đủ "admin/categories/list"
        return "admin/categories/list"; 
    }

    // 2. Hiển thị Form Thêm mới Category
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Thêm Category Mới");
        return "admin/categories/form";
    }

    // 3. Xử lý Thêm/Sửa Category
    @PostMapping("/save")
    public String saveCategory(Category category, RedirectAttributes ra) {
        categoryService.save(category);
        ra.addFlashAttribute("message", "Category đã được lưu thành công!");
        return "redirect:/admin/categories"; 
    }

    // 4. Hiển thị Form Sửa Category
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Category category = categoryService.findById(id)
                                 .orElseThrow(() -> new RuntimeException("Category not found"));
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Sửa Category (ID: " + id + ")");
            return "admin/categories/form";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/categories";
        }
    }

    // 5. Xử lý Xóa Category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            categoryService.deleteById(id);
            ra.addFlashAttribute("message", "Category ID " + id + " đã được xóa thành công.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Không thể xóa Category ID " + id + ".");
        }
        return "redirect:/admin/categories";
    }
}