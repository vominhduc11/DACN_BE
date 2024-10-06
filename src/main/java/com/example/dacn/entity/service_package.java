package com.example.dacn.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "goi_dich_vu")
public class service_package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "noi_dung", nullable = false)
    private String content;

    @Column(name = "gia", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;
}
