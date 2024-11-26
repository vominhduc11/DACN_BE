package com.example.dacn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "don_hang")
public class order {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ten_goi", nullable = false)
    private String name_package;

    @Column(name = "so_luong", nullable = false)
    private String quantity;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private product product;

    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private user user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // Tự động lưu thời gian hiện tại khi thêm
    }
}
