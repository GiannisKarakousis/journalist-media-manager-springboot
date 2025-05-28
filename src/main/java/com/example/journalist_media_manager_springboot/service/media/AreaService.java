package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.AreaDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Area;
import com.example.journalist_media_manager_springboot.persistence.repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<AreaDto> findAll() {
        Collection<Area> areaCollection = this.areaRepository.findAll();

        Type type = new TypeToken<Collection<AreaDto>>() {
        }.getType();

        return modelMapper.map(areaCollection, type);
    }
}
