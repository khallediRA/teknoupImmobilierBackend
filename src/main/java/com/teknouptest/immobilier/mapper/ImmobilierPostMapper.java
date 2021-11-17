package com.teknouptest.immobilier.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostResponse;
import com.teknouptest.immobilier.dto.ImmobilierPostUpdateDto;

import com.teknouptest.immobilier.model.Immobilier;
import com.teknouptest.immobilier.model.ImmobilierPost;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ImmobilierPostMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "post.title")
    @Mapping(target = "description", expression = "java(post.getImmobilier().getDescription())")
    @Mapping(target = "imageUrl", expression = "java(post.getImmobilier().getImageURL())")
    @Mapping(target = "price", expression = "java(post.getImmobilier().getPrice())")
    @Mapping(target = "publicationDuration", expression = "java(getDuration(post))")
    @Mapping(target = "adresse", expression = "java(post.getImmobilier().getAdresse())")
    @Mapping(target = "city", expression = "java(post.getImmobilier().getCity().getName())")
    @Mapping(target = "contact", source = "post.contact")
    @Mapping(target = "superficie", source = "immobilier.superficie")
    public abstract ImmobilierPostResponse mapToDto(ImmobilierPost post);

    @Mapping(target = "title", source = "postRequest.title")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "immobilier", source = "immobilier")
    @Mapping(target = "contact", source = "postRequest.contact")
    public abstract ImmobilierPost dtoToModel(ImmobilierPostRequest postRequest, Immobilier immobilier);

    @Mapping(target = "id", source = "immobilierPostUpdateDto.id")
    @Mapping(target = "title", source = "immobilierPostUpdateDto.title")
    @Mapping(target = "immobilier", source = "immobilier")
    @Mapping(target = "contact", source = "immobilierPostUpdateDto.contact")
    public abstract ImmobilierPost dtoUpateToModel(ImmobilierPostUpdateDto immobilierPostUpdateDto,
            Immobilier immobilier);

    String getDuration(ImmobilierPost post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

}
