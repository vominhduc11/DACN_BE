package com.example.dacn.repository;

import com.example.dacn.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface orderRepository extends JpaRepository<order, String> {
    List<order> findAllByUserIdOrderByIdDesc(int idUser);
    @Transactional
    @Modifying
    @Query("DELETE FROM don_hang e WHERE e.createdAt < :expirationTime")
    void deleteOldData(LocalDateTime expirationTime);
}
