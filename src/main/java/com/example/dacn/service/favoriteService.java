package com.example.dacn.service;

import com.example.dacn.entity.favorite;
import com.example.dacn.entity.package_service;
import com.example.dacn.entity.product;
import com.example.dacn.entity.user;
import com.example.dacn.repository.favoriteRepository;
import com.example.dacn.repository.package_serviceRepository;
import com.example.dacn.repository.productRepository;
import com.example.dacn.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class favoriteService {

    @Autowired
    private favoriteRepository favoriteRepository;

    @Autowired
    private productRepository productRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private package_serviceRepository package_serviceRepository;

    // Thêm 1 sản phẩm vào bảng favorite
    public void addProductFavorite(int idProduct, int idUser) {
        favorite favorite = favoriteRepository.findByProduct_IdAndUser_Id(idProduct, idUser);
        product product = productRepository.findById(idProduct).get();
        user user = userRepository.findById(idUser).get();

        if (favorite == null) {
            favorite favorite1 = new favorite();
            favorite1.setProduct(product);

            if (favorite1.getUsers() == null) {
                favorite1.setUsers(new ArrayList<>());
            }
            favorite1.getUsers().add(user);
            favoriteRepository.save(favorite1);
        } else {
            favorite.getUsers().add(user);
            favoriteRepository.save(favorite);
        }
    }

    //    Lấy tất cả sản phẩm trong bảng favorite
    public List<Map<String, Object>> getAllProductFavorite(int userId) {
        List<favorite> favorites = favoriteRepository.findAllByUserId(userId);

        List<Map<String, Object>> favoriteDTOs = new ArrayList<>();

        for (favorite favorite : favorites) {

            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(favorite.getProduct().getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }

            Map<String, Object> favoriteDTO = new HashMap<>() {{
                put("id", favorite.getId());
                put("idProduct", favorite.getProduct().getId());
                put("image", favorite.getProduct().getImage());
                put("name", favorite.getProduct().getName());
                put("star", favorite.getProduct().getStar());
                put("evaluate", favorite.getProduct().getEvaluate());
                put("booked", favorite.getProduct().getBooked());
                put("cityId", favorite.getProduct().getCity().getId());
                put("city", favorite.getProduct().getCity().getName());
                put("category", favorite.getProduct().getCategory());
                put("country", favorite.getProduct().getCity().getCountry().getName());
                put("package", package_serviceDTOs);
            }};
            favoriteDTOs.add(favoriteDTO);
        }

        return favoriteDTOs;
    }

    public void deleteProductFavorite(int idProduct, int idUser) {
        user user = userRepository.findById(idUser).get();
        favorite favorite = favoriteRepository.findByProduct_IdAndUser_Id(idProduct, idUser);

        //Xóa dây
        List<user> users = favorite.getUsers();
        users.remove(user);
        favoriteRepository.save(favorite);

        if (favorite.getUsers().size() == 0) {
            favoriteRepository.delete(favorite);
        }
    }
}
