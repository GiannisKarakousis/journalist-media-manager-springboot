package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.PeriodicityDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Periodicity;
import com.example.journalist_media_manager_springboot.persistence.repository.PeriodicityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class PeriodicityService {

    @Autowired
    private PeriodicityRepository periodicityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<PeriodicityDto> findAll() {
        Collection<Periodicity> periodicityCollection = this.periodicityRepository.findAllByOrderById();

        Type type = new TypeToken<Collection<PeriodicityDto>>() {
        }.getType();

        return modelMapper.map(periodicityCollection, type);
    }
}
