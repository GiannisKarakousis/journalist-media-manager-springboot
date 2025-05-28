package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.media.MediaGroupDto;
import com.example.journalist_media_manager_springboot.service.media.MediaGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/media-groups")
public class MediaGroupController {

    @Autowired
    private MediaGroupService mediaGroupService;

    @GetMapping
    public Collection<MediaGroupDto> getAll() {
        return mediaGroupService.findAll();
    }
}
