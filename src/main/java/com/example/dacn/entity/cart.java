package com.example.dacn.entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private product product;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "gio_hang_nguoi_dung",
            joinColumns = @JoinColumn(name = "id_gio_hang"),
            inverseJoinColumns = @JoinColumn(name = "id_nguoi_dung")
    )
    private List<user> users;
}
