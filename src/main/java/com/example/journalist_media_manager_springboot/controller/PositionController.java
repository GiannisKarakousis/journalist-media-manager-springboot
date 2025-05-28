package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.PositionDto;
import com.example.journalist_media_manager_springboot.service.journalist.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public Collection<PositionDto> getAll(){
        return positionService.findAll();
    }
}
