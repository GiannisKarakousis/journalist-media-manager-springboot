package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.PeriodicityDto;
import com.example.journalist_media_manager_springboot.service.media.PeriodicityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/periodicals")
public class PeriodicityController {

    @Autowired
    private PeriodicityService periodicityService;

    @GetMapping
    public Collection<PeriodicityDto> getAll() {
        return periodicityService.findAll();
    }
}
