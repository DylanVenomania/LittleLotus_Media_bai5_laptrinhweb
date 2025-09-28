package com.littlelotus.service;

import com.littlelotus.entity.Category;
import com.littlelotus.entity.User;
import com.littlelotus.entity.Video;
import com.littlelotus.repository.CategoryRepository;
import com.littlelotus.repository.UserRepository;
import com.littlelotus.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService 
{

    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    // tao/ capnhat
    @Transactional
    public Video save(Video video, Long uploaderId, Long categoryId) {
       
        Optional<User> uploader = userRepository.findById(uploaderId);
        Optional<Category> category = categoryRepository.findById(categoryId);
        
        if (uploader.isEmpty() || category.isEmpty()) 
        {
            throw new RuntimeException("Uploader hoặc Category không tìm thấy.");
        }
        
   
        video.setUploader(uploader.get());
        video.setCategory(category.get());
        
        
        return videoRepository.save(video);
    }

    // doc ( all)
    public List<Video> findAll() 
    {
        return videoRepository.findAllEagerly(); 
    }

    // doc ( bang id )
    public Optional<Video> findById(Long id) 
    {
        return videoRepository.findByIdEagerly(id);
    }
    
    // xoa
    public void deleteById(Long id) 
    {
        videoRepository.deleteById(id);
    }

    // tim kiem
    public List<Video> search(String keyword) 
    {
        // Tim theo tieu de
        return videoRepository.findByTitleContainingIgnoreCase(keyword);
    }
    
    // tim kiem theo ten category
    public List<Video> searchByCategory(String categoryName) 
    {
        return videoRepository.findByTitleContainingIgnoreCase(categoryName);
    }
}