package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "goi")
public class package_service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false, columnDefinition = "TEXT")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "package_service", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<quantity> quantitys;

    @JsonIgnore
    @OneToMany(mappedBy = "packageService", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<cart> carts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;
}
