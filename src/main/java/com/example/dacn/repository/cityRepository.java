package com.example.dacn.repository;

import com.example.dacn.entity.city;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface cityRepository extends JpaRepository<city, Integer> {
    Set<city> findTop6ByOrderByIdAsc();

    Set<city> findTop10ByOrderByIdAsc();

    city findByName(String name);

    List<city> findTop5ByNameContainingIgnoreCase(@Param("keyword") String keyword);

    List<city> findAllByCountry_Id(int id_product);
}
