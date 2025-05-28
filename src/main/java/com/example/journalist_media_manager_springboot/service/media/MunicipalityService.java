package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.MunicipalityDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Municipality;
import com.example.journalist_media_manager_springboot.persistence.repository.MunicipalityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class MunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<MunicipalityDto> findAll() {
        Collection<Municipality> municipalityCollection = this.municipalityRepository.findAll();

        Type type = new TypeToken<Collection<MunicipalityDto>>() {
        }.getType();

        return modelMapper.map(municipalityCollection, type);
    }
}
