package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "san_pham")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false, unique = true)
    private String name;

    @Column(name = "hinh_anh", nullable = false)
    private String image;

    @Column(name = "sao", nullable = false)
    private float star;

    @Column(name = "danh_gia", nullable = false)
    private int evaluate;

    @Column(name = "da_dat", nullable = false)
    private int booked;

    @Column(name = "thong_bao")
    private String notify;

    @Column(name = "vi_tri")
    private String place;

    @Column(name = "dia_chi")
    private String address;

    @Lob
    @Column(name = "diem_noi_bat", columnDefinition = "TEXT")
    private String highlight;

    @Lob
    @Column(name = "thong_tin", nullable = false, columnDefinition = "TEXT")
    private String information;

    @Column(name = "loai", length = 50, nullable = false)
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<package_service> package_services;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_thanh_pho")
    private city city;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_quoc_gia")
    private country country;

    @JsonIgnore
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<favorite> favorites;

    @JsonIgnore
    @OneToOne(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    private trip trip;

    @JsonIgnore
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<order> orders;

}
