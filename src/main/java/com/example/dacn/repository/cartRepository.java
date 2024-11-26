package com.example.dacn.repository;

import com.example.dacn.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cartRepository  extends JpaRepository<cart, Integer> {
    @Query("SELECT COUNT(c) > 0 FROM gio_hang c JOIN c.users u WHERE c.packageService.id = :idPackage AND u.id = :idUser")
    boolean existsByPackageService_IdAndUser_Id(@Param("idPackage") int idPackage, @Param("idUser") int idUser);

    @Query("SELECT c FROM gio_hang c JOIN c.users u WHERE u.id = :userId ORDER BY c.id DESC")
    List<cart> findAllByUserId(@Param("userId") int userId);

    @Query("SELECT c FROM gio_hang c JOIN c.users u WHERE c.packageService.id = :idPackage AND u.id = :idUser")
    cart findByPackageService_IdAndUser_Id(@Param("idPackage") int idPackage, @Param("idUser") int idUser);
}