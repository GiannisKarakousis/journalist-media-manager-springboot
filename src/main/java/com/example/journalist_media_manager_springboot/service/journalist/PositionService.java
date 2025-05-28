package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.journalist.PositionDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Position;
import com.example.journalist_media_manager_springboot.persistence.repository.PositionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<PositionDto> findAll() {
        Collection<Position> positionCollection = this.positionRepository.findAll();

        Type type = new TypeToken<Collection<PositionDto>>() {
        }.getType();

        return modelMapper.map(positionCollection, type);
    }
}
