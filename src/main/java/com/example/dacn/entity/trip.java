package com.example.dacn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "chuyen_di")
public class trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chuyen_di_nguoi_dung",
            joinColumns = @JoinColumn(name = "id_chuyen_di"),
            inverseJoinColumns = @JoinColumn(name = "id_nguoi_dung")
    )
    private List<user> users;
}
