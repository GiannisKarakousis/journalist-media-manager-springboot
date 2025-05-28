package com.example.journalist_media_manager_springboot.service.journalist;

import com.example.journalist_media_manager_springboot.dto.common.GenericPageDto;
import com.example.journalist_media_manager_springboot.dto.journalist.*;
import com.example.journalist_media_manager_springboot.dto.journalist.register.JournalistCreateDto;
import com.example.journalist_media_manager_springboot.dto.journalist.register.JournalistCreateRequiredDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaDto;
import com.example.journalist_media_manager_springboot.exception.EntityNotFoundException;
import com.example.journalist_media_manager_springboot.exception.InvalidFieldValueException;
import com.example.journalist_media_manager_springboot.persistence.entity.*;
import com.example.journalist_media_manager_springboot.persistence.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JournalistService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JournalistRepository journalistRepository;

    @Autowired
    private AccreditationRepository accreditationRepository;

    @Autowired
    private DepartmentPositionRepository departmentPositionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ReportageRepository reportageRepository;

    @Autowired
    private PositionRepository positionRepository;

    private static final Logger logger = LoggerFactory.getLogger(JournalistService.class);

    public JournalistDto findById(Integer id) throws EntityNotFoundException {
        Optional<Journalist> optionalJournalistEntity = this.journalistRepository.findById(id);

        if (optionalJournalistEntity.isPresent()) {
            return modelMapper.map(optionalJournalistEntity.get(), JournalistDto.class);
        } else {
            throw new EntityNotFoundException("Journalist with id " + id + " not found");
        }
    }

    public Collection<JournalistDto> findAll() {
        Collection<Journalist> journalists = journalistRepository.findAll();

        Type type = new TypeToken<Collection<JournalistDto>>() {
        }.getType();

        return modelMapper.map(journalists, type);
    }

    public GenericPageDto findPaginated(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.asc("lastname"), Sort.Order.asc("firstname"));

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Journalist> paginatedData = journalistRepository.findAll(pageable);


        Collection<Journalist> journalists = paginatedData.getContent();
        Type type = new TypeToken<Collection<JournalistDto>>() {
        }.getType();
        Collection<JournalistDto> journalistDtos = modelMapper.map(journalists, type);

        GenericPageDto genericPageDto = new GenericPageDto();

        genericPageDto.setElements(journalistDtos);
        genericPageDto.setCurrentPage(paginatedData.getNumber());
        genericPageDto.setTotalElements(paginatedData.getTotalElements());
        genericPageDto.setTotalPages(paginatedData.getTotalPages());

        return genericPageDto;
    }

    public JournalistDto create(JournalistCreateDto journalistCreateDto) {
        // Check required fields
        JournalistCreateRequiredDto requiredFields = modelMapper.map(journalistCreateDto, JournalistCreateRequiredDto.class);
        Boolean missingRequiredFields = requiredFields.equals(new JournalistCreateRequiredDto());

        if (missingRequiredFields) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        // Create new Journalist entity
        Journalist journalist = modelMapper.map(journalistCreateDto, Journalist.class);

        // Convert department position name to entity
        if (journalistCreateDto.getDepartmentPositionName() != null) {
            DepartmentPosition departmentPosition = departmentPositionRepository
                    .findByDepartmentPositionName(journalistCreateDto.getDepartmentPositionName())
                    .orElseThrow(() -> new InvalidFieldValueException("Department Position not found"));
            journalist.setDepartmentPosition(departmentPosition);
        }

        // Convert accreditation name to entity
        if (journalistCreateDto.getAccreditationName() != null) {
            Accreditation accreditation = accreditationRepository
                    .findByAccreditationName(journalistCreateDto.getAccreditationName())
                    .orElseThrow(() -> new InvalidFieldValueException("Accreditation not found"));
            journalist.setAccreditation(accreditation);
        }

        if (journalistCreateDto.getCustomerCollection() != null) {
            // Fetch the selected customers
            Set<String> customerNames = journalistCreateDto.getCustomerCollection()
                    .stream().map(CustomerDto::getCustomerName)
                    .collect(Collectors.toSet());

            // Fetch all customers that exist in the database
            Set<String> existingCustomerNames = new HashSet<>(customerRepository.findAllByCustomerNameIn(customerNames))
                    .stream().map(Customer::getCustomerName)
                    .collect(Collectors.toSet());

            // Check if correct
            if (!existingCustomerNames.containsAll(customerNames)) {
                customerNames.removeAll(existingCustomerNames); // Get only the missing ones
                throw new InvalidFieldValueException("CustomerCollection: The following customers do not exist: " + customerNames);
            }

            // Assign the customers
            Set<Customer> customerSet = customerRepository.findAllByCustomerNameIn(existingCustomerNames);
            journalist.setCustomerCollection(customerSet);
        }

        // Convert media names to entities
        if (journalistCreateDto.getMediaCollection() != null) {
            // Fetch the selected media
            Set<String> mediaNames = journalistCreateDto.getMediaCollection()
                    .stream().map(MediaDto::getMediaName)
                    .collect(Collectors.toSet());

            // Fetch all media that exist in the database
            Set<String> existingMediaNames = new HashSet<>(mediaRepository.findAllByMediaNameIn(mediaNames))
                    .stream().map(Media::getMediaName)
                    .collect(Collectors.toSet());

            // Check if correct
            if (!existingMediaNames.containsAll(mediaNames)) {
                mediaNames.removeAll(existingMediaNames); // Get only the missing ones
                throw new InvalidFieldValueException("MediaCollection: The following media do not exist: " + mediaNames);
            }

            // Assign the media
            Set<Media> mediaSet = mediaRepository.findAllByMediaNameIn(existingMediaNames);
            journalist.setMediaCollection(mediaSet);
        }

        // Convert position names to entities
        if (journalistCreateDto.getPositionCollection() != null) {
            // Fetch the selected positions
            Set<String> positionNames = journalistCreateDto.getPositionCollection()
                    .stream().map(PositionDto::getPositionName)
                    .collect(Collectors.toSet());

            // Fetch all the positions that exist in the database
            Set<String> existingPositionNames = new HashSet<>(positionRepository.findAllByPositionNameIn(positionNames))
                    .stream().map(Position::getPositionName)
                    .collect(Collectors.toSet());

            // Check if correct
            if (!existingPositionNames.containsAll(positionNames)) {
                positionNames.removeAll(existingPositionNames);
                throw new InvalidFieldValueException("PositionCollection: The following positions do not exist: " + positionNames);
            }

            // Assign the positions
            Set<Position> positionSet = positionRepository.findAllByPositionNameIn(existingPositionNames);
            journalist.setPositionCollection(positionSet);
        }

        // Convert reportage names to entities
        if (journalistCreateDto.getReportageCollection() != null) {
            // Fetch the selected reportage
            Set<String> reportageNames = journalistCreateDto.getReportageCollection()
                    .stream().map(ReportageDto::getReportageName)
                    .collect(Collectors.toSet());

            // Fetch all the reportage that exist in the database
            Set<String> existingReportageNames = reportageRepository.findAllByReportageNameIn(reportageNames)
                    .stream().map(Reportage::getReportageName)
                    .collect(Collectors.toSet());

            // Check if correct
            if (!existingReportageNames.containsAll(reportageNames)) {
                reportageNames.removeAll(existingReportageNames);
                throw new InvalidFieldValueException("ReportageCollection: The following reportage do not exist: " + reportageNames);
            }

            // Assign the reportage
            Set<Reportage> reportageSet = reportageRepository.findAllByReportageNameIn(existingReportageNames);
            journalist.setReportageCollection(reportageSet);
        }

        // Save journalist entity
        Journalist createdEntity = journalistRepository.save(journalist);

        // Convert saved entity back to DTO
        return modelMapper.map(createdEntity, JournalistDto.class);
    }

    public JournalistDto update(Integer id, JournalistUpdateDto journalistUpdateDto) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            throw new SecurityException("Unauthorized: No authenticated user found");
//        }
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Journalist journalist = journalistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Journalist with id " + id + " not found"));

        if (journalistUpdateDto.getFirstname() != null) journalist.setFirstname(journalistUpdateDto.getFirstname());
        if (journalistUpdateDto.getLastname() != null) journalist.setLastname(journalistUpdateDto.getLastname());
        if (journalistUpdateDto.getGdpr() != null) journalist.setGdpr(journalistUpdateDto.getGdpr());
        if (journalistUpdateDto.getPhone1() != null) journalist.setPhone1(journalistUpdateDto.getPhone1());
        if (journalistUpdateDto.getPhone2() != null) journalist.setPhone2(journalistUpdateDto.getPhone2());
        if (journalistUpdateDto.getMobile1() != null) journalist.setMobile1(journalistUpdateDto.getMobile1());
        if (journalistUpdateDto.getMobile2() != null) journalist.setMobile2(journalistUpdateDto.getMobile2());
        if (journalistUpdateDto.getEmail1() != null) journalist.setEmail1(journalistUpdateDto.getEmail1());
        if (journalistUpdateDto.getEmail2() != null) journalist.setEmail2(journalistUpdateDto.getEmail2());
        if (journalistUpdateDto.getKeywords() != null) journalist.setKeywords(journalistUpdateDto.getKeywords());
        if (journalistUpdateDto.getNotes() != null) journalist.setNotes(journalistUpdateDto.getNotes());
//        journalist.setUpdatedByUser(userDetails.getId());

        if (journalistUpdateDto.getDepartmentPositionName() != null) {
            DepartmentPosition departmentPosition = departmentPositionRepository
                    .findByDepartmentPositionName(journalistUpdateDto.getDepartmentPositionName())
                    .orElseThrow(() -> new InvalidFieldValueException("Department Position not found"));
            journalist.setDepartmentPosition(departmentPosition);
        }

        if (journalistUpdateDto.getAccreditationName() != null) {
            Accreditation accreditation = accreditationRepository
                    .findByAccreditationName(journalistUpdateDto.getAccreditationName())
                    .orElseThrow(() -> new InvalidFieldValueException("Accreditation not found"));
            journalist.setAccreditation(accreditation);
        }

        if (journalistUpdateDto.getCustomerCollection() != null) {
            Set<String> customerNames = journalistUpdateDto.getCustomerCollection()
                    .stream().map(CustomerDto::getCustomerName)
                    .collect(Collectors.toSet());

            Set<String> existingCustomerNames = customerRepository.findAllByCustomerNameIn(customerNames)
                    .stream().map(Customer::getCustomerName)
                    .collect(Collectors.toSet());

            if (!existingCustomerNames.containsAll(customerNames)) {
                customerNames.removeAll(existingCustomerNames);
                throw new InvalidFieldValueException("CustomerCollection: The following customers do not exist: " + customerNames);
            }

            // Assign the customers
            Set<Customer> customerSet = customerRepository.findAllByCustomerNameIn(existingCustomerNames);
            journalist.setCustomerCollection(customerSet);
        }

        if (journalistUpdateDto.getMediaCollection() != null) {
            Set<String> mediaNames = journalistUpdateDto.getMediaCollection()
                    .stream().map(MediaNameDto::getMediaName)
                    .collect(Collectors.toSet());

            Set<String> existingMediaNames = mediaRepository.findAllByMediaNameIn(mediaNames)
                    .stream().map(Media::getMediaName)
                    .collect(Collectors.toSet());

            if (!existingMediaNames.containsAll(mediaNames)) {
                mediaNames.removeAll(existingMediaNames);
                throw new InvalidFieldValueException("MediaCollection: The following media do not exist: " + mediaNames);
            }

            Set<Media> mediaSet = mediaRepository.findAllByMediaNameIn(mediaNames);
            journalist.setMediaCollection(mediaSet);
        }

        if (journalistUpdateDto.getPositionCollection() != null) {
            Set<String> positionNames = journalistUpdateDto.getPositionCollection()
                    .stream().map(PositionDto::getPositionName)
                    .collect(Collectors.toSet());

            Set<String> existingPositionNames = positionRepository.findAllByPositionNameIn(positionNames)
                    .stream().map(Position::getPositionName)
                    .collect(Collectors.toSet());

            if (!existingPositionNames.containsAll(positionNames)) {
                positionNames.removeAll(existingPositionNames);
                throw new InvalidFieldValueException("PositionCollection: The following position(s) do not exist: " + positionNames);
            }

            Set<Position> positionSet = positionRepository.findAllByPositionNameIn(positionNames);
            journalist.setPositionCollection(positionSet);
        }

        if (journalistUpdateDto.getReportageCollection() != null) {
            Set<String> reportageNames = journalistUpdateDto.getReportageCollection()
                    .stream().map(ReportageDto::getReportageName)
                    .collect(Collectors.toSet());

            Set<String> existingReportageNames = reportageRepository.findAllByReportageNameIn(reportageNames)
                    .stream().map(Reportage::getReportageName)
                    .collect(Collectors.toSet());

            if (!existingReportageNames.containsAll(reportageNames)) {
                reportageNames.removeAll(existingReportageNames);
                throw new InvalidFieldValueException("ReportageCollection: The following reportage do not exist: " + reportageNames);
            }

            Set<Reportage> reportageSet = reportageRepository.findAllByReportageNameIn(reportageNames);
            journalist.setReportageCollection(reportageSet);
        }

        // Logging before saving
        //logger.info("Before saving, updatedByUser = {}", journalist.getUpdatedByUser());

        Journalist savedJournalist = journalistRepository.save(journalist);

        //logger.info("After saving, updatedByUser = {}", savedJournalist.getUpdatedByUser());

        return modelMapper.map(savedJournalist, JournalistDto.class);
    }

    public void delete(Integer id) {
        journalistRepository.deleteById(id);
    }
}
