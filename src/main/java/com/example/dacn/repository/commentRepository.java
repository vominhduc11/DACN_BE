package com.example.dacn.repository;

import com.example.dacn.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface commentRepository extends JpaRepository<comment, Integer> {

    List<comment> findAllByProduct_IdAndUser_IdOrderByIdDesc(int idProduct, int idUser);
}
