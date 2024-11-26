package com.example.dacn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "quoc_gia")
public class country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "hinh_anh", nullable = false)
    private String image;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<city> cities;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<product> products;
}
