package com.teknouptest.immobilier.Service;

import javax.transaction.Transactional;

import com.teknouptest.immobilier.Repository.ImmobilierRepository;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostUpdateDto;
import com.teknouptest.immobilier.exception.ImmobilierNotFoundException;
import com.teknouptest.immobilier.model.City;
import com.teknouptest.immobilier.model.Immobilier;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ImmobilierService {
        private final ImmobilierRepository immobilierRepository;

        Immobilier save(ImmobilierPostRequest postRequest, City city) {
                return immobilierRepository.save(Immobilier.builder().adresse(postRequest.getAdresse())
                                .description(postRequest.getDescription()).imageURL(postRequest.getImageUrl())
                                .price(postRequest.getPrice()).city(city).superficie(postRequest.getSuperficie())
                                .build());
        }

        Immobilier updateImmobilier(Long immobilierId, City city, ImmobilierPostUpdateDto immobilierPostUpdateDto) {

                Immobilier oldImmobilier = immobilierRepository.findById(immobilierId)
                                .orElseThrow(() -> new ImmobilierNotFoundException(
                                                "immobilier Not Found with id" + immobilierId.toString()));
                return immobilierRepository.save(Immobilier.builder().id(oldImmobilier.getId())
                                .adresse(immobilierPostUpdateDto.getAdresse()).city(city)
                                .description(immobilierPostUpdateDto.getDescription())
                                .price(immobilierPostUpdateDto.getPrice())
                                .imageURL(immobilierPostUpdateDto.getImageUrl())
                                .superficie(immobilierPostUpdateDto.getSuperficie()).build());
        }

}
