package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.journalist.AccreditationDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Accreditation;
import com.example.journalist_media_manager_springboot.persistence.repository.AccreditationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class AccreditationService {

    @Autowired
    private AccreditationRepository accreditationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<AccreditationDto> findAll() {
        Collection<Accreditation> accreditationCollection = this.accreditationRepository.findAll();

        Type type = new TypeToken<Collection<AccreditationDto>>() {
        }.getType();

        return modelMapper.map(accreditationCollection, type);
    }
}
