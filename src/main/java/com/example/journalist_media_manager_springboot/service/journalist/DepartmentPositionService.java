package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.journalist.DepartmentPositionDto;
import com.example.journalist_media_manager_springboot.persistence.entity.DepartmentPosition;
import com.example.journalist_media_manager_springboot.persistence.repository.DepartmentPositionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class DepartmentPositionService {

    @Autowired
    private DepartmentPositionRepository departmentPositionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<DepartmentPositionDto> findAll() {
        Collection<DepartmentPosition> departmentPositionCollection = this.departmentPositionRepository.findAll();

        Type type = new TypeToken<Collection<DepartmentPositionDto>>() {
        }.getType();

        return modelMapper.map(departmentPositionCollection, type);
    }
}
