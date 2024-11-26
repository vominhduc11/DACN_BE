package com.example.dacn.service;

import com.example.dacn.repository.commentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class commentService {

    @Autowired
    private commentRepository commentRepository;
}
