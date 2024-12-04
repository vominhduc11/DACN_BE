package com.example.dacn.service;

import com.example.dacn.entity.cart;
import com.example.dacn.entity.package_service;
import com.example.dacn.entity.product;
import com.example.dacn.entity.user;
import com.example.dacn.repository.cartRepository;
import com.example.dacn.repository.package_serviceRepository;
import com.example.dacn.repository.productRepository;
import com.example.dacn.repository.userRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class cartService {
    @Autowired
    private cartRepository cartRepository;

    @Autowired
    private productRepository productRepository;

    @Autowired
    private package_serviceRepository package_serviceRepository;

    @Autowired
    private userRepository userRepository;

    public List<Map<String, Object>> getAllProductCart(int idUser) {
        List<cart> carts = cartRepository.findAllByUserId(idUser);

        List<Map<String, Object>> productDTOs = new ArrayList<>();

        // Tạo Gson instance
        Gson gson = new Gson();

        for (cart cart : carts) {
            List<Map<String, Object>> package_serviceDTOs = new ArrayList<>();
            for (package_service package_service : cart.getProduct().getPackage_services()) {
                Map<String, Object> package_serviceDTO = new HashMap<>() {{
                    put("id", package_service.getId());
                    put("name", package_service.getName());
                    put("quantitys", package_service.getQuantitys());
                }};
                package_serviceDTOs.add(package_serviceDTO);
            }

            Type listType = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
//             Parse JSON thành Map
            List<Map<String, Object>> map = gson.fromJson(cart.getQuantity(), listType);
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", cart.getProduct().getId());
                put("name", cart.getProduct().getName());
                put("id_package", cart.getPackageService().getId());
                put("name_package", cart.getPackageService().getName());
                put("quantitys", map);
                put("image", cart.getProduct().getImage());
                put("cityId", cart.getProduct().getCity().getId());
                put("city", cart.getProduct().getCity().getName());
                put("category", cart.getProduct().getCategory());
                put("star", cart.getProduct().getStar());
                put("package", package_serviceDTOs);
            }};

            productDTOs.add(productDTO);
        }

        return productDTOs;


    }


    public boolean checkIfExists(int idPackage, int idUser) {
        boolean result = cartRepository.existsByPackageService_IdAndUser_Id(idPackage, idUser);

        return result;
    }

    public void partialUpdateProductCart(int idPackage, int idProduct, int idUser, String quantitys) {
        cart cart = cartRepository.findByPackageService_IdAndUser_Id(idPackage, idUser);  // Sản phẩm mà người dùng thêm vào chắc chắn có trong bảng giỏ hàng
        package_service package_service = package_serviceRepository.findById(idPackage).get(); // Gói mà người dùng đó thêm vào
        user user = userRepository.findById(idUser).get();  // Người dùng mà thêm sản phẩm
        product product = productRepository.findById(idProduct).get(); // Sản phẩm mà người dùng đó thêm vào
        // Lấy quantity của cart trong csdl
        String quantity1 = cart.getQuantity();

        // Sử lí 2 quantity
        JsonArray array1 = JsonParser.parseString(quantity1).getAsJsonArray();
        JsonArray array2 = JsonParser.parseString(quantitys).getAsJsonArray();

        JsonArray mergedArray = mergeJsonArrays(array1, array2);
        String mergedArrayString = mergedArray.toString();

        if (cart.getUsers().size() == 1 && cart.getUsers().contains(user)) {
//            Xóa Liên kết
            List<user> users = cart.getUsers();
            users.remove(user);
            cartRepository.save(cart);
//            Xóa cart
            cartRepository.delete(cart);
            // Tạo cart mới và lưu
            cart cart1 = new cart();
            cart1.setProduct(product);
            cart1.setPackageService(package_service);
            cart1.setQuantity(mergedArrayString);
            // Khởi tạo danh sách users nếu null
            if (cart1.getUsers() == null) {
                cart1.setUsers(new ArrayList<>());
            }
            cart1.getUsers().add(user);
            cartRepository.save(cart1);
        }


        if (cart.getUsers().size() > 1 && cart.getUsers().contains(user)) {
            List<user> users = cart.getUsers();
            users.remove(user);
            cartRepository.save(cart);

            // Tạo cart mới và lưu
            cart cart1 = new cart();
            cart1.setProduct(product);
            cart1.setPackageService(package_service);
            cart1.setQuantity(mergedArrayString);
            // Khởi tạo danh sách users nếu null
            if (cart1.getUsers() == null) {
                cart1.setUsers(new ArrayList<>());
            }
            cart1.getUsers().add(user);
            cartRepository.save(cart1);
        }


        if (cart.getUsers().size() >= 1 && !cart.getUsers().contains(user)) {
            if (quantity1.equals(quantitys)) {
                List<user> users = cart.getUsers();
                users.add(user);
                cartRepository.save(cart);
            } else {
                // Tạo cart mới và lưu
                cart cart1 = new cart();
                cart1.setProduct(product);
                cart1.setPackageService(package_service);
                cart1.setQuantity(quantitys);
                // Khởi tạo danh sách users nếu null
                if (cart1.getUsers() == null) {
                    cart1.setUsers(new ArrayList<>());
                }
                cart1.getUsers().add(user);
                cartRepository.save(cart1);
            }
        }
    }

    private JsonArray mergeJsonArrays(JsonArray array1, JsonArray array2) {
        Map<Integer, JsonObject> map = new HashMap<>();

        // Xử lý mảng đầu tiên
        for (int i = 0; i < array1.size(); i++) {
            JsonObject obj = array1.get(i).getAsJsonObject();
            map.put(obj.get("id").getAsInt(), obj);
        }

        // Xử lý mảng thứ hai
        for (int i = 0; i < array2.size(); i++) {
            JsonObject obj = array2.get(i).getAsJsonObject();
            int id = obj.get("id").getAsInt();

            if (map.containsKey(id)) {
                // Cộng giá trị amount nếu đã tồn tại
                JsonObject existing = map.get(id);
                int newAmount = existing.get("amount").getAsInt() + obj.get("amount").getAsInt();
                existing.addProperty("amount", newAmount);
            } else {
                // Thêm mới nếu chưa tồn tại
                map.put(id, obj);
            }
        }

        // Chuyển map thành JsonArray
        JsonArray result = new JsonArray();
        for (JsonObject value : map.values()) {
            result.add(value);
        }

        return result;
    }

    public void addProductCart(int idProduct, int idPackage, int idUser, String quantitys) {
        Optional<product> productOptional = productRepository.findById(idProduct);
        product product = productOptional.get();

        Optional<package_service> packageOptional = package_serviceRepository.findById(idPackage);
        package_service package_service = packageOptional.get();

        Optional<user> userOptional = userRepository.findById(idUser);
        user user = userOptional.get();

        cart cart = new cart();
        cart.setProduct(product);
        cart.setQuantity(quantitys);
        cart.setPackageService(package_service);

        // Khởi tạo danh sách users nếu null
        if (cart.getUsers() == null) {
            cart.setUsers(new ArrayList<>());
        }
        cart.getUsers().add(user);
        cartRepository.save(cart);
    }

    public void deleteProductCart(int idPackage, int idUser) {
        cart cart = cartRepository.findByPackageService_IdAndUser_Id(idPackage, idUser);
        //Xóa dây
        List<user> users = cart.getUsers();
        user user = userRepository.findById(idUser).get();
        users.remove(user);
        // Lưu lại đối tượng cart sau khi thay đổi danh sách users
        cartRepository.save(cart);

        if (cart.getUsers().size() == 0) {
            cartRepository.delete(cart);
        }
    }
}
