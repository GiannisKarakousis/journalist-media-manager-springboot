package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.MunicipalityDto;
import com.example.journalist_media_manager_springboot.service.media.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/municipalities")
public class MunicipalityController {

    @Autowired
    private MunicipalityService municipalityService;

    @GetMapping
    public Collection<MunicipalityDto> getAll() {
        return municipalityService.findAll();
    }
}
