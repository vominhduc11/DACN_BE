package com.example.dacn.service;

import com.example.dacn.entity.city;
import com.example.dacn.entity.country;
import com.example.dacn.entity.package_service;
import com.example.dacn.entity.product;
import com.example.dacn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;
    @Autowired
    private cityRepository cityRepository;
    @Autowired
    private countryRepository countryRepository;
    @Autowired
    private package_serviceRepository package_serviceRepository;
    @Autowired
    private favoriteRepository favoriteRepository;

    public Map<String, Object> getProduct(int id, int idUser) {
        // Lấy tất cả gói của sản phẩm đó và duyệt qua
        List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(id);
        List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

        for (package_service package_service : package_services) {
            Map<String, Object> package_serviceDTO = new HashMap<>() {{
                put("id", package_service.getId());
                put("name", package_service.getName());
                put("quantitys", package_service.getQuantitys());
            }};

            package_serviceDTOs.add(package_serviceDTO);
        }

        // Lấy ra sản phẩm đó từ id
        Optional<product> productOptional = productRepository.findById(id);
        product product = productOptional.get();
        // Kiểm tra xem có phải là sản phẩm yêu thích hay không
        boolean b = favoriteRepository.existsByUser_IdAndProduct_Id(idUser, id);
        Map<String, Object> productDTO = new HashMap<>() {
            {
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("evaluate", product.getEvaluate());
                put("booked", product.getBooked());
                put("isLike", b);
                put("notify", product.getNotify());
                put("place", product.getPlace());
                put("address", product.getAddress());
                put("highlight", product.getHighlight());
                put("information", product.getInformation());
                put("category", product.getCategory());
                put("package_services", package_serviceDTOs);
            }
        };
        return productDTO;
    }

    public List<Map<String, Object>> getProductOfCity(int id, int amount, int idUser) {
        // Sử dụng PageRequest với số lượng giới hạn là amount
        long totalRecords = productRepository.countByCityId(id);
        int randomPage = (int) (Math.random() * (totalRecords / amount));
        Pageable pageable = PageRequest.of(randomPage, amount);

        List<product> products = productRepository.findByCity_Id(id, pageable);

        Collections.shuffle(products); // Sắp xếp ngẫu nhiên lại kết quả trang

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            // Xác nhận sản phẩm này có phải là sản phẩm yêu thích hay không
            boolean b = favoriteRepository.existsByUser_IdAndProduct_Id(idUser, product.getId());
            // Lấy danh sách gói thông qua id sản phẩm và duyệt qua
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("cityId", product.getCity().getId());
                put("city", product.getCity().getName());
                put("evaluate", product.getEvaluate());
                put("booked", product.getBooked());
                put("category", product.getCategory());
                put("star", product.getStar());
                put("isLike", b);
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public Set<String> getAllCategoryOfCity(int id) {
        Set<String> strings = productRepository.getAllCategoryOfCity(id);

        return strings;
    }

    public List<Map<String, Object>> getInTurnProduct(int amountProduct) {
        Pageable pageable = PageRequest.of(0, amountProduct);
        List<product> products = productRepository.findLatestProduct(pageable);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<Map<String, Object>> getInTurnProductOfCity(int id, int amount) {
        Pageable pageable = PageRequest.of(0, amount);
        List<product> products = productRepository.findAllByCityId(id, pageable);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<Map<String, Object>> getListProduct(String category, int cityId) {
        List<product> products = productRepository.findTop10ByCategoryAndCityId(category, cityId);
        // Randon vị trí phần tử
        Collections.shuffle(products);
        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<Map<String, Object>> getAllProductOfCategory(String Category,int idUser) {
        List<product> products = productRepository.findAllByCategory(Category);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            // Xác nhận sản phẩm này có phải là sản phẩm yêu thích hay không
            boolean b = favoriteRepository.existsByUser_IdAndProduct_Id(idUser, product.getId());
            // Lấy danh sách gói thông qua id sản phẩm và duyệt qua
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {

                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("evaluate", product.getEvaluate());
                put("isLike", b);
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public List<Map<String, Object>> getAllProductOfCity(int cityId, int idUser) {
        List<product> products = productRepository.findAllByCityId(cityId);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            boolean b = favoriteRepository.existsByUser_IdAndProduct_Id(idUser, product.getId());
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("evaluate", product.getEvaluate());
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("isLike", b);
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public List<Map<String, Object>> getListProductAccordingString(String debounce) {
        List<product> products = productRepository.findTop10ByNameContainingIgnoreCase(debounce);

        List<Map<String, Object>> productDTOs = new ArrayList<>();


        for (product product : products) {
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("evaluate", product.getEvaluate());
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public List<Map<String, Object>> getAllProductOfCategoryAndCity(String category, String nameCity, int idUser) {
        List<product> products = productRepository.findAllByCategoryAndCity_Name(category, nameCity);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        for (product product : products) {
            // Xác nhận sản phẩm có phải là sản phẩm yêu thích hay không
            boolean b = favoriteRepository.existsByUser_IdAndProduct_Id(idUser, product.getId());
            // Tìm danh sách gói thông qua id sản phẩm và duyệt qua
            List<package_service> package_services = package_serviceRepository.findAllByProduct_Id(product.getId());
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();

            for (package_service package_service : package_services) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};

                package_serviceDTOs.add(package_serviceDTO);
            }

            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", product.getId());
                put("name", product.getName());
                put("image", product.getImage());
                put("star", product.getStar());
                put("evaluate", product.getEvaluate());
                put("isLike", b);
                put("city", product.getCity().getName());
                put("cityId", product.getCity().getId());
                put("category", product.getCategory());
                put("booked", product.getBooked());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public int addProduct(String name, String image, int star, int review, int booked, String notify, String position, String highlight, String information, String category, int city, int country) {
        product product = new product();

        product.setName(name);
        product.setImage(image);
        product.setStar(star);
        product.setEvaluate(review);
        product.setBooked(booked);
        product.setNotify(notify);
        product.setPlace(position);
        product.setHighlight(highlight);
        product.setInformation(information);
        product.setCategory(category);

        Optional<city> cityOption = cityRepository.findById(city);
        city cityEntity = cityOption.get();

        Optional<country> countryOption = countryRepository.findById(country);
        country countryEntity = countryOption.get();

        product.setCity(cityEntity);
        product.setCountry(countryEntity);

        product product1 = productRepository.save(product);

        return product1.getId();
    }
}
