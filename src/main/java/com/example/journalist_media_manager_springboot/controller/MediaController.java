package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.MediaNameDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaUpdateDto;
import com.example.journalist_media_manager_springboot.dto.media.register.MediaCreateDto;
import com.example.journalist_media_manager_springboot.service.media.MediaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/media")
@Validated
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/{id}")
    public MediaDto getById(@PathVariable Integer id) {
        return mediaService.findById(id);
    }

    @GetMapping
    public Object getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) Integer size
    ) {
        if (page != null) {
            return mediaService.findPaginated(page, size); // Return paginated Media
        } else {
            return mediaService.findAll(); // Return all Media
        }
    }

    @GetMapping("/names")
    public Collection<MediaNameDto> getAll() {
        return mediaService.findAllNames();
    }

    @PostMapping
    public MediaDto create(@Valid @RequestBody MediaCreateDto mediaCreateDto) {
        return mediaService.create(mediaCreateDto);
    }

    @PatchMapping("/{id}")
    public MediaDto update(@PathVariable Integer id, @Valid @RequestBody MediaUpdateDto mediaUpdateDto) {
        return mediaService.update(id, mediaUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        mediaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
