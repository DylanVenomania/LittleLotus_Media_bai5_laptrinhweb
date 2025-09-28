package com.littlelotus.service;

import com.littlelotus.entity.Category;
import com.littlelotus.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService 
{
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category save(Category category) 
    {

        return categoryRepository.save(category);
    }
   
    public List<Category> findAll() 
    {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) 
    {
        return categoryRepository.findById(id);
    }
    
    public void deleteById(Long id) 
    {
        categoryRepository.deleteById(id);
    }
    public List<Category> search(String keyword) 
    {
        return categoryRepository.findByNameContainingIgnoreCase(keyword);
    }
}