package com.example.dacn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "binh_luan")
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "thoi_gian", nullable = false)
    private LocalDateTime time;

    @Column(name = "danh_gia", nullable = false)
    private float evaluate;

    @Column(name = "noi_dung", nullable = false)
    private String content;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private user user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;
}
