package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.TransmissionTypeDto;
import com.example.journalist_media_manager_springboot.persistence.entity.TransmissionType;
import com.example.journalist_media_manager_springboot.persistence.repository.TransmissionTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<TransmissionTypeDto> findAll() {
        Collection<TransmissionType> transmissionTypeCollection = this.transmissionTypeRepository.findAll();

        Type type = new TypeToken<Collection<TransmissionTypeDto>>() {
        }.getType();

        return modelMapper.map(transmissionTypeCollection, type);
    }
}
