package com.example.dacn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "nguoi_dung")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "mat_khau",nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<order> orders;

    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<cart> carts;

    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<favorite> favorites;

    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private List<trip> trips;
}
