package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.JournalistDto;
import com.example.journalist_media_manager_springboot.dto.journalist.JournalistUpdateDto;
import com.example.journalist_media_manager_springboot.dto.journalist.register.JournalistCreateDto;
import com.example.journalist_media_manager_springboot.service.journalist.JournalistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journalists")
@Validated
public class JournalistController {

    @Autowired
    private JournalistService journalistService;

    @GetMapping("/{id}")
    public JournalistDto getById(@PathVariable Integer id) {
        return journalistService.findById(id);
    }

    @GetMapping
    public Object getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false, defaultValue = "25") @Max(100) Integer size
    ) {
        if (page != null) {
            return journalistService.findPaginated(page, size); // Return paginated journalists
        } else {
            return journalistService.findAll(); // Return all journalists
        }
    }

    @PostMapping
    public JournalistDto create(@Valid @RequestBody JournalistCreateDto journalistCreateDto) {
        return journalistService.create(journalistCreateDto);
    }

    @PatchMapping("/{id}")
    public JournalistDto update(@PathVariable Integer id, @Valid @RequestBody JournalistUpdateDto journalistUpdateDto) {
        return journalistService.update(id, journalistUpdateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        journalistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
