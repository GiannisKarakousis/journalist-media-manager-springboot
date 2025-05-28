package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.CountryDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Country;
import com.example.journalist_media_manager_springboot.persistence.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<CountryDto> findAll() {
        Collection<Country> countryCollection = this.countryRepository.findAll();

        Type type = new TypeToken<Collection<CountryDto>>() {
        }.getType();

        return modelMapper.map(countryCollection, type);
    }
}
