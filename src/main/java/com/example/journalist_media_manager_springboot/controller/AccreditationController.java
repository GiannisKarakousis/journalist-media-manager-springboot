package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.AccreditationDto;
import com.example.journalist_media_manager_springboot.service.journalist.AccreditationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/accreditations")
public class AccreditationController {

    @Autowired
    private AccreditationService accreditationService;

    @GetMapping
    public Collection<AccreditationDto> getAll() {
        return accreditationService.findAll();
    }
}
