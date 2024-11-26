package com.example.dacn.repository;


import com.example.dacn.entity.package_service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface package_serviceRepository extends JpaRepository<package_service, Integer> {
    List<package_service> findAllByProduct_Id(int id) ;
}
