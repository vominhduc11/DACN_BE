package com.example.dacn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "yeu_thich")
public class favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "yeu_thich_nguoi_dung",
            joinColumns = @JoinColumn(name = "id_yeu_thich"),
            inverseJoinColumns = @JoinColumn(name = "id_nguoi_dung")
    )
    private List<user> users;
}
