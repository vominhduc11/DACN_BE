package com.example.dacn.service;

import com.example.dacn.entity.comment;
import com.example.dacn.repository.commentRepository;
import com.example.dacn.repository.productRepository;
import com.example.dacn.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class commentService {

    @Autowired
    private commentRepository commentRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private productRepository productRepository;


    public void addComment(int idUser, int idProduct, Number star, String time, String content) {
        comment comment = new comment();
        comment.setContent(content);
        comment.setEvaluate(star.floatValue());
        comment.setUser(userRepository.findById(idUser).get());
        comment.setProduct(productRepository.findById(idProduct).get());
        comment.setTime(ZonedDateTime.parse(time).toLocalDateTime());
        commentRepository.save(comment);
    }

    public List<Map<String, Object>> getAllComment(int idUser, int idProduct) {
        List<comment> comments = commentRepository.findAllByProduct_IdAndUser_IdOrderByIdDesc(idProduct, idUser);

        List<Map<String, Object>> commentDTOs = new ArrayList<>();

        comments.forEach(comment -> {
            // Chuyển LocalDateTime sang ZonedDateTime với múi giờ UTC
            ZonedDateTime zonedDateTime = comment.getTime().atZone(ZoneOffset.UTC);
            Map<String, Object> commentDTO = new HashMap<>() {{
                put("id", comment.getId());
                put("evaluate", comment.getEvaluate());
                put("time", zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
                put("content", comment.getContent());
                put("name", comment.getUser().getName());
                put("image", comment.getUser().getImage());
            }};
            commentDTOs.add(commentDTO);
        });

        return commentDTOs;
    }
}
