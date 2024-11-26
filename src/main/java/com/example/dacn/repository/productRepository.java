package com.example.dacn.repository;

import com.example.dacn.entity.product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface productRepository extends JpaRepository<product, Integer> {
        @Query("SELECT p FROM san_pham p WHERE p.city.id = :cityId")
        List<product> findByCity_Id(@Param("cityId") int id_city, Pageable pageable);

        @Query("SELECT p.category  FROM san_pham p WHERE p.city.id = :cityId")
        Set<String> getAllCategoryOfCity(@Param("cityId") int cityId);

        @Query("SELECT p FROM san_pham p ORDER BY p.id DESC")
        List<product> findLatestProduct(Pageable pageable);

        List<product> findAllByCityId(int id, Pageable pageable);

        List<product> findTop10ByCategoryAndCityId(String category, int cityId);

        long countByCityId(int id);

        List<product> findAllByCategory(String category);

        List<product> findAllByCityId(int cityId);

        List<product> findTop10ByNameContainingIgnoreCase(String debounce);

        List<product> findAllByCategoryAndCity_Name(String category, String nameCity);
}
