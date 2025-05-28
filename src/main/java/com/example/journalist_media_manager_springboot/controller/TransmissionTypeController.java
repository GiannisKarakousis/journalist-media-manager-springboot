package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.TransmissionTypeDto;
import com.example.journalist_media_manager_springboot.service.media.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/transmission-types")
public class TransmissionTypeController {

    @Autowired
    private TransmissionTypeService transmissionTypeService;

    @GetMapping
    public Collection<TransmissionTypeDto> getAll() {
        return transmissionTypeService.findAll();
    }
}
