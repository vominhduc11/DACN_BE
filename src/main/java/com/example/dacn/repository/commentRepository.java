package com.example.dacn.repository;

import com.example.dacn.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentRepository extends JpaRepository<comment, Integer> {
    List<comment> findAllByProduct_Id(int id);
}
