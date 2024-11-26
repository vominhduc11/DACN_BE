package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "thanh_pho")
public class city {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "hinh_anh", nullable = false)
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<product> products;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_quoc_gia")
    private country country;
}
