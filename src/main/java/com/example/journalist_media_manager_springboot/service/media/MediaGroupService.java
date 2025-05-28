package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.media.MediaGroupDto;
import com.example.journalist_media_manager_springboot.persistence.entity.MediaGroup;
import com.example.journalist_media_manager_springboot.persistence.repository.MediaGroupRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class MediaGroupService {

    @Autowired
    private MediaGroupRepository mediaGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<MediaGroupDto> findAll() {
        Collection<MediaGroup> mediaGroupCollection = this.mediaGroupRepository.findAll();

        Type type = new TypeToken<Collection<MediaGroupDto>>() {
        }.getType();

        return modelMapper.map(mediaGroupCollection, type);
    }
}
