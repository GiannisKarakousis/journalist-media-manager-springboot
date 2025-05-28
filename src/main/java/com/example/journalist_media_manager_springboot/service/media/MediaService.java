package com.example.journalist_media_manager_springboot.service.media;

import com.example.journalist_media_manager_springboot.dto.common.GenericPageDto;
import com.example.journalist_media_manager_springboot.dto.journalist.MediaNameDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaDto;
import com.example.journalist_media_manager_springboot.dto.media.MediaUpdateDto;
import com.example.journalist_media_manager_springboot.dto.media.register.MediaCreateDto;
import com.example.journalist_media_manager_springboot.dto.media.register.MediaCreateRequiredDto;
import com.example.journalist_media_manager_springboot.exception.EntityNotFoundException;
import com.example.journalist_media_manager_springboot.exception.InvalidFieldValueException;
import com.example.journalist_media_manager_springboot.persistence.entity.*;
import com.example.journalist_media_manager_springboot.persistence.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    @Autowired
    private PeriodicityRepository periodicityRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MediaGroupRepository mediaGroupRepository;

    public MediaDto findById(Integer id) throws EntityNotFoundException {
        Optional<Media> optionalMediaEntity = this.mediaRepository.findById(id);

        if (optionalMediaEntity.isPresent()) {
            return modelMapper.map(optionalMediaEntity.get(), MediaDto.class);
        } else {
            throw new EntityNotFoundException("Media with id " + id + " not found");
        }
    }

    public Collection<MediaDto> findAll() {
        Collection<Media> mediaCollection = mediaRepository.findAll();

        Type type = new TypeToken<Collection<MediaDto>>() {
        }.getType();

        return modelMapper.map(mediaCollection, type);
    }

    public GenericPageDto findPaginated(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.asc("mediaName"));

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Media> paginatedData = mediaRepository.findAll(pageable);

        Collection<Media> media = paginatedData.getContent();
        Type type = new TypeToken<Collection<MediaDto>>() {
        }.getType();
        Collection<MediaDto> mediaDtos = modelMapper.map(media, type);

        GenericPageDto genericPageDto = new GenericPageDto();

        genericPageDto.setElements(mediaDtos);
        genericPageDto.setCurrentPage(paginatedData.getNumber());
        genericPageDto.setTotalElements(paginatedData.getTotalElements());
        genericPageDto.setTotalPages(paginatedData.getTotalPages());

        return genericPageDto;
    }

    public Collection<MediaNameDto> findAllNames() {
        Collection<Media> mediaCollection = this.mediaRepository.findAll();

        Type type = new TypeToken<Collection<MediaNameDto>>() {
        }.getType();

        return modelMapper.map(mediaCollection, type);
    }

    public MediaDto create(MediaCreateDto mediaCreateDto) {
        // Check required fields
        MediaCreateRequiredDto requiredFields = modelMapper.map(mediaCreateDto, MediaCreateRequiredDto.class);
        Boolean missingRequiredFields = requiredFields.equals(new MediaCreateRequiredDto());

        if (missingRequiredFields) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        // Create new Media Entity
        Media media = modelMapper.map(mediaCreateDto, Media.class);

        // Convert TransmissionType name to Entity
        if (mediaCreateDto.getTransmissionTypeName() != null) {
            TransmissionType transmissionType = transmissionTypeRepository
                    .findByTransmissionTypeName(mediaCreateDto.getTransmissionTypeName())
                    .orElseThrow(() -> new InvalidFieldValueException("Transmission Type not found"));
            media.setTransmissionType(transmissionType);
        }

        if (mediaCreateDto.getMediaTypeName() != null) {
            MediaType mediaType = mediaTypeRepository
                    .findByMediaTypeName(mediaCreateDto.getMediaTypeName())
                    .orElseThrow(() -> new InvalidFieldValueException("Media Type not found"));
            media.setMediaType(mediaType);
        }

        if (mediaCreateDto.getPeriodicityName() != null) {
            Periodicity periodicity = periodicityRepository
                    .findByPeriodicityName(mediaCreateDto.getPeriodicityName())
                    .orElseThrow(() -> new InvalidFieldValueException("Periodicity not found"));
            media.setPeriodicity(periodicity);
        }

        if (mediaCreateDto.getAreaName() != null) {
            Area area = areaRepository
                    .findByAreaName(mediaCreateDto.getAreaName())
                    .orElseThrow(() -> new InvalidFieldValueException("Area not found"));
            media.setArea(area);
        }

        if (mediaCreateDto.getCountyName() != null) {
            County county = countyRepository
                    .findByCountyName(mediaCreateDto.getCountyName())
                    .orElseThrow(() -> new InvalidFieldValueException("County not found"));
            media.setCounty(county);
        }

        if (mediaCreateDto.getMunicipalityName() != null) {
            Municipality municipality = municipalityRepository
                    .findByMunicipalityName(mediaCreateDto.getMunicipalityName())
                    .orElseThrow(() -> new InvalidFieldValueException("Municipality not found"));
            media.setMunicipality(municipality);
        }

        if (mediaCreateDto.getCountryName() != null) {
            Country country = countryRepository
                    .findByCountryName(mediaCreateDto.getCountryName())
                    .orElseThrow(() -> new InvalidFieldValueException("Country not found"));
            media.setCountry(country);
        }

        if (mediaCreateDto.getMediaGroupName() != null) {
            MediaGroup mediaGroup = mediaGroupRepository
                    .findByMediaGroupName(mediaCreateDto.getMediaGroupName())
                    .orElseThrow(() -> new InvalidFieldValueException("Media Group not found"));
            media.setMediaGroup(mediaGroup);
        }

        Media createdEntity = mediaRepository.save(media);

        return modelMapper.map(createdEntity, MediaDto.class);
    }

    public MediaDto update(Integer id, MediaUpdateDto mediaUpdateDto) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Media with id " + id + " not found"));

        if (mediaUpdateDto.getMediaName() != null) media.setMediaName(mediaUpdateDto.getMediaName());
        if (mediaUpdateDto.getEmail() != null) media.setEmail(mediaUpdateDto.getEmail());
        if (mediaUpdateDto.getAddress() != null) media.setAddress(mediaUpdateDto.getAddress());
        if (mediaUpdateDto.getTown() != null) media.setTown(mediaUpdateDto.getTown());
        if (mediaUpdateDto.getPostalCode() != null) media.setPostalCode(mediaUpdateDto.getPostalCode());

        if (mediaUpdateDto.getTransmissionTypeName() != null) {
            TransmissionType transmissionType = transmissionTypeRepository
                    .findByTransmissionTypeName(mediaUpdateDto.getTransmissionTypeName())
                    .orElseThrow(() -> new InvalidFieldValueException("Transmission Type not found"));
            media.setTransmissionType(transmissionType);
        }

        if (mediaUpdateDto.getMediaTypeName() != null) {
            MediaType mediaType = mediaTypeRepository
                    .findByMediaTypeName(mediaUpdateDto.getMediaTypeName())
                    .orElseThrow(() -> new InvalidFieldValueException("Media Type not found"));
            media.setMediaType(mediaType);
        }

        if (mediaUpdateDto.getPeriodicityName() != null) {
            Periodicity periodicity = periodicityRepository
                    .findByPeriodicityName(mediaUpdateDto.getPeriodicityName())
                    .orElseThrow(() -> new InvalidFieldValueException("Periodicity not found"));
            media.setPeriodicity(periodicity);
        }

        if (mediaUpdateDto.getAreaName() != null) {
            Area area = areaRepository
                    .findByAreaName(mediaUpdateDto.getAreaName())
                    .orElseThrow(() -> new InvalidFieldValueException("Area not found"));
            media.setArea(area);
        }

        if (mediaUpdateDto.getCountyName() != null) {
            County county = countyRepository
                    .findByCountyName(mediaUpdateDto.getCountyName())
                    .orElseThrow(() -> new InvalidFieldValueException("County not found"));
            media.setCounty(county);
        }

        if (mediaUpdateDto.getMunicipalityName() != null) {
            Municipality municipality = municipalityRepository
                    .findByMunicipalityName(mediaUpdateDto.getMunicipalityName())
                    .orElseThrow(() -> new InvalidFieldValueException("Municipality not found"));
            media.setMunicipality(municipality);
        }

        if (mediaUpdateDto.getCountryName() != null) {
            Country country = countryRepository
                    .findByCountryName(mediaUpdateDto.getCountryName())
                    .orElseThrow(() -> new InvalidFieldValueException("Country not found"));
            media.setCountry(country);
        }

        if (mediaUpdateDto.getMediaGroupName() != null) {
            MediaGroup mediaGroup = mediaGroupRepository
                    .findByMediaGroupName(mediaUpdateDto.getMediaGroupName())
                    .orElseThrow(() -> new InvalidFieldValueException("Media Group not found"));
            media.setMediaGroup(mediaGroup);
        }

        Media savedMedia = mediaRepository.save(media);

        return modelMapper.map(savedMedia, MediaDto.class);
    }

    public void deleteById(Integer id) {
        mediaRepository.deleteById(id);
    }
}
