package com.example.PROYECTOv1.Repositories;

import com.example.PROYECTOv1.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPadreIsNull();
}
