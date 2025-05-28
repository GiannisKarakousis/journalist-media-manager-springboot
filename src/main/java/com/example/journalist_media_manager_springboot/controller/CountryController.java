package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.CountryDto;
import com.example.journalist_media_manager_springboot.service.media.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public Collection<CountryDto> getAll() {
        return countryService.findAll();
    }
}
