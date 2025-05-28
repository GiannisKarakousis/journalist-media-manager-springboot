package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.CountyDto;
import com.example.journalist_media_manager_springboot.persistence.entity.County;
import com.example.journalist_media_manager_springboot.persistence.repository.CountyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<CountyDto> findAll() {
        Collection<County> countyCollection = this.countyRepository.findAll();

        Type type = new TypeToken<Collection<CountyDto>>() {
        }.getType();

        return modelMapper.map(countyCollection, type);
    }
}
