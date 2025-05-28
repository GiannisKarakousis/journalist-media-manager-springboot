package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.journalist.ReportageDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Reportage;
import com.example.journalist_media_manager_springboot.persistence.repository.ReportageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class ReportageService {

    @Autowired
    private ReportageRepository reportageRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<ReportageDto> findAll() {
        Collection<Reportage> reportageCollection = this.reportageRepository.findAll();

        Type type = new TypeToken<Collection<ReportageDto>>() {
        }.getType();

        return modelMapper.map(reportageCollection, type);
    }
}
