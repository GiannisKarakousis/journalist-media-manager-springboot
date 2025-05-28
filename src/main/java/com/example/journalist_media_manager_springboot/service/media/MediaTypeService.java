package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.MediaTypeDto;
import com.example.journalist_media_manager_springboot.persistence.entity.MediaType;
import com.example.journalist_media_manager_springboot.persistence.repository.MediaTypeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class MediaTypeService {

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<MediaTypeDto> findAll() {
        Collection<MediaType> mediaTypeCollection = this.mediaTypeRepository.findAll();

        Type type = new TypeToken<Collection<MediaTypeDto>>() {
        }.getType();

        return modelMapper.map(mediaTypeCollection, type);
    }
}
