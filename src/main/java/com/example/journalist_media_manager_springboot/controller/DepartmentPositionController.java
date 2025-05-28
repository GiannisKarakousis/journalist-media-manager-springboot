package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.DepartmentPositionDto;
import com.example.journalist_media_manager_springboot.service.journalist.DepartmentPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/department-positions")
public class DepartmentPositionController {

    @Autowired
    private DepartmentPositionService departmentPositionService;

    @GetMapping
    public Collection<DepartmentPositionDto> getAll() {
        return departmentPositionService.findAll();
    }
}
