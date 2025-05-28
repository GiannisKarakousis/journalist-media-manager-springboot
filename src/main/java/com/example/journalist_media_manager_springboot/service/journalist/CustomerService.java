package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.journalist.CustomerDto;
import com.example.journalist_media_manager_springboot.persistence.entity.Customer;
import com.example.journalist_media_manager_springboot.persistence.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Collection<CustomerDto> findAll() {
        Collection<Customer> customerCollection = this.customerRepository.findAll();

        Type type = new TypeToken<Collection<CustomerDto>>() {
        }.getType();

        return modelMapper.map(customerCollection, type);
    }
}
