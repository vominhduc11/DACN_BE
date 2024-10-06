package com.example.dacn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "don_hang")
public class order {
    @Id
    @Column(name = "id")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private user user;
}
