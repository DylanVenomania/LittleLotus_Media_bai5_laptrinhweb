package com.littlelotus.repository;

import com.littlelotus.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> 
{
	@Query("SELECT v FROM Video v JOIN FETCH v.uploader JOIN FETCH v.category")
	List<Video> findAllEagerly();
	@Query("SELECT v FROM Video v JOIN FETCH v.uploader JOIN FETCH v.category WHERE v.id = :id")
	Optional<Video> findByIdEagerly(Long id);
	
	@Query("SELECT v FROM Video v JOIN FETCH v.uploader u JOIN FETCH v.category c WHERE LOWER(v.title) LIKE %:keyword%")
    List<Video> findByTitleContainingIgnoreCaseEagerly(@Param("keyword") String keyword);
	
    List<Video> findByCategoryNameContainingIgnoreCase(String categoryName);
}