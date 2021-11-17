package com.teknouptest.immobilier.mapper;

import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostUpdateDto;
import com.teknouptest.immobilier.model.City;
import com.teknouptest.immobilier.model.Immobilier;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ImmobilierMapper {

    @Mapping(target = "imageURL", expression = "java(postRequest.getImageUrl())")
    @Mapping(target = "price", expression = "java(postRequest.getPrice())")
    @Mapping(target = "adresse", expression = "java(postRequest.getAdresse())")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "superficie", expression = "java(postRequest.getSuperficie())")
    @Mapping(target = "description", expression = "java(postRequest.getDescription())")
    public abstract Immobilier dtoToModel(ImmobilierPostRequest postRequest, City city);
}
