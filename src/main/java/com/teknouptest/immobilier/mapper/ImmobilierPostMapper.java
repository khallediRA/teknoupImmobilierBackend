package com.teknouptest.immobilier.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.teknouptest.immobilier.Repository.CityRepository;
import com.teknouptest.immobilier.Repository.ImmobilierRepository;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostResponse;
import com.teknouptest.immobilier.model.City;
import com.teknouptest.immobilier.model.Immobilier;
import com.teknouptest.immobilier.model.ImmobilierPost;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ImmobilierPostMapper {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ImmobilierRepository immobilierRepository;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "post.title")
    @Mapping(target = "description", expression = "java(post.getImmobilier().getDescription())")
    @Mapping(target = "imageUrl", expression = "java(post.getImmobilier().getImageURL())")
    @Mapping(target = "price", expression = "java(post.getImmobilier().getPrice())")
    @Mapping(target = "publicationDuration", expression = "java(getDuration(post))")
    @Mapping(target = "adresse", expression = "java(post.getImmobilier().getAdresse())")
    @Mapping(target = "city", expression = "java(post.getImmobilier().getCity().getName())")
    public abstract ImmobilierPostResponse mapToDto(ImmobilierPost post);

    @Mapping(target = "title", source = "postRequest.title")
    // @Mapping(target = "creationDate", expression =
    // "java(java.time.Instant.now())")
    @Mapping(target = "immobilier", expression = "java(createImmobilier(postRequest))")
    @Mapping(target = "contact", source = "postRequest.contact")
    @Mapping(target = "superficie", source = "postRequest.superficie")
    public abstract ImmobilierPost dtoToModel(ImmobilierPostRequest postRequest);

    String getDuration(ImmobilierPost post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    Immobilier createImmobilier(ImmobilierPostRequest postRequest) {
        City city = cityRepository.findByNameAndCountryName(postRequest.getCityname(), postRequest.getCountryName())
                .orElseGet(() -> {
                    return createNewCity(postRequest.getCountryName(), postRequest.getCityname());
                });
        return immobilierRepository
                .save(Immobilier.builder().adresse(postRequest.getAdresse()).description(postRequest.getDescription())
                        .imageURL(postRequest.getImageUrl()).price(postRequest.getPrice()).city(city).build());

    }

    City createNewCity(String countryName, String cityName) {

        return cityRepository.save(City.builder().countryName(countryName).name(cityName).build());
    }

}
