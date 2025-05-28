package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.MediaTypeDto;
import com.example.journalist_media_manager_springboot.service.media.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/media-types")
public class MediaTypeController {

    @Autowired
    private MediaTypeService mediaTypeService;

    @GetMapping
    public Collection<MediaTypeDto> getAll() {
        return mediaTypeService.findAll();
    }
}
