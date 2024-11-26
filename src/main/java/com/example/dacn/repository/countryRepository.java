package com.example.dacn.repository;

import com.example.dacn.entity.country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface countryRepository extends JpaRepository<country, Integer> {
}
