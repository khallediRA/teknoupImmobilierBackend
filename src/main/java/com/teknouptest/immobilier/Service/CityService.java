package com.teknouptest.immobilier.Service;

import com.teknouptest.immobilier.Repository.CityRepository;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.model.City;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City findOrCreateCity(ImmobilierPostRequest postRequest) {
        return cityRepository.findByNameAndCountryName(postRequest.getCityname(), postRequest.getCountryName())
                .orElseGet(() -> {
                    return cityRepository.save(City.builder().countryName(postRequest.getCountryName())
                            .name(postRequest.getCityname()).build());
                });
    }

}
