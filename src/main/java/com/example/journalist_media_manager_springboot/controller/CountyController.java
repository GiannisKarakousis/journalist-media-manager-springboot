package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.CountyDto;
import com.example.journalist_media_manager_springboot.service.media.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("counties")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @GetMapping
    public Collection<CountyDto> getAll() {
        return countyService.findAll();
    }
}
