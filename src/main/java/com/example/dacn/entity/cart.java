package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity(name = "gio_hang")
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "so_luong", nullable = false, columnDefinition = "TEXT")
    private String quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_san_pham")
    private product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_goi")
    private package_service packageService;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "gio_hang_nguoi_dung",
            joinColumns = @JoinColumn(name = "id_gio_hang"),
            inverseJoinColumns = @JoinColumn(name = "id_nguoi_dung")
    )

    private List<user> users;
}
