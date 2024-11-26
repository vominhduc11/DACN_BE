package com.example.dacn.repository;

import com.example.dacn.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository  extends JpaRepository<user, Integer> {
    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByPassword(String password);

    user findByEmailAndPassword(String email, String password);
}
