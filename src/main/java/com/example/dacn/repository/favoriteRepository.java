package com.example.dacn.repository;

import com.example.dacn.entity.cart;
import com.example.dacn.entity.favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface favoriteRepository  extends JpaRepository<favorite, Integer> {
    @Query("SELECT y FROM yeu_thich y JOIN y.users u WHERE u.id = :userId ORDER BY y.id DESC")
    List<favorite> findAllByUserId(@Param("userId") int userId);
    @Query("SELECT c FROM yeu_thich c JOIN c.users u WHERE c.product.id = :idProduct AND u.id = :idUser")
    favorite findByProduct_IdAndUser_Id(@Param("idProduct") int idProduct,@Param("idUser") int idUser);
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM yeu_thich f JOIN f.users u WHERE u.id = :idUser AND f.product.id = :idProduct")
    boolean existsByUser_IdAndProduct_Id(@Param("idUser") int idUser,@Param("idProduct") int id);
}
