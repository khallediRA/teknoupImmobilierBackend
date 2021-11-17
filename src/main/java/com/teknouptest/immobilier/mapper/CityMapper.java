package com.teknouptest.immobilier.mapper;

import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostUpdateDto;
import com.teknouptest.immobilier.model.City;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CityMapper {

    @Mapping(target = "name", source = "countryName")
    @Mapping(target = "countryName", source = "cityname")
    public abstract City dtoToCity(ImmobilierPostRequest postRequest);

    @Mapping(target = "name", source = "cityname")
    @Mapping(target = "countryName", source = "countryName")
    public abstract City dtoUpdateToCity(ImmobilierPostUpdateDto immobilierPostUpdateDto);

}
