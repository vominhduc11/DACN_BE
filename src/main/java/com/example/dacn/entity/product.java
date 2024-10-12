package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "san_pham")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "hinh_anh", nullable = false)
    private String image;

    @Column(name = "sao", nullable = false)
    private float star;

    @Column(name = "danh_gia", nullable = false)
    private int evaluate;

    @Column(name = "da_dat", nullable = false)
    private int booked;

    @Column(name = "thong_bao", nullable = false)
    private String notify;

    @Column(name = "vi_tri", nullable = false)
    private String place;

    @Lob
    @Column(name = "diem_noi_bat", nullable = false, columnDefinition = "TEXT")
    private String highlight;

    @Column(name = "gia", nullable = false)
    private BigDecimal price;

    @Column(name = "giam_gia", nullable = false)
    private byte reduce_price;

    @Lob
    @Column(name = "thong_tin", nullable = false, columnDefinition = "TEXT")
    private String information;

    @Column(name = "loai", length = 50, nullable = false)
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<comment> comments;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_thanh_pho")
    private city city;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_quoc_gia")
    private country country;

    @OneToOne(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private cart cart;

    @OneToOne(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private favorite favorite;

    @OneToOne(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private trip trip;

    @OneToOne(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private order order;
}
