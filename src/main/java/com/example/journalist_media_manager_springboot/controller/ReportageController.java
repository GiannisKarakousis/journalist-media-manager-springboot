package com.example.journalist_media_manager_springboot.controller;

import com.example.journalist_media_manager_springboot.dto.journalist.ReportageDto;
import com.example.journalist_media_manager_springboot.service.journalist.ReportageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("reportage")
public class ReportageController {

    @Autowired
    private ReportageService reportageService;

    @GetMapping
    public Collection<ReportageDto> getAll() {
        return reportageService.findAll();
    }
}
