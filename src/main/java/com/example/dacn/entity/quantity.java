package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "so_luong")
public class quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "do_tuoi", nullable = false, columnDefinition = "TEXT")
    private String age;

    @Column(name = "gia", nullable = false)
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_goi")
    private package_service package_service;
}
