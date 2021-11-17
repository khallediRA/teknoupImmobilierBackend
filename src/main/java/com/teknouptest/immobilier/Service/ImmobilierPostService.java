package com.teknouptest.immobilier.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.teknouptest.immobilier.Repository.CityRepository;
import com.teknouptest.immobilier.Repository.ImmobilierPostRepository;
import com.teknouptest.immobilier.Repository.ImmobilierRepository;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostResponse;
import com.teknouptest.immobilier.dto.ImmobilierPostUpdateDto;

import com.teknouptest.immobilier.exception.PostNotFoundException;

import com.teknouptest.immobilier.mapper.ImmobilierPostMapper;
import com.teknouptest.immobilier.model.City;
import com.teknouptest.immobilier.model.Immobilier;
import com.teknouptest.immobilier.model.ImmobilierPost;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service

@Transactional
@AllArgsConstructor

public class ImmobilierPostService {

    private final ImmobilierPostRepository immobilierPostRepository;
    private final ImmobilierPostMapper immobilierPostMapper;
    private final CityService cityService;
    private final CityRepository cityRepository;

    private final ImmobilierService immobilierService;

    public List<ImmobilierPostResponse> getAllImmobilierPost() {
        return immobilierPostRepository.findAll().stream().map(immobilierPostMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public ImmobilierPostResponse getImmobilierPost(Long id) {
        ImmobilierPost post = immobilierPostRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id " + id.toString()));
        return immobilierPostMapper.mapToDto(post);
    }

    public void save(ImmobilierPostRequest postRequest) {
        City city = cityService.findOrCreateCity(postRequest);
        Immobilier immobilier = immobilierService.save(postRequest, city);
        immobilierPostRepository.save(immobilierPostMapper.dtoToModel(postRequest, immobilier));
    }

    public void updateImmoblierPost(ImmobilierPostUpdateDto immobilierPostUpdateDto) {
        Long postId = immobilierPostUpdateDto.getId();

        ImmobilierPost post = immobilierPostRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Cannot find find post with id " + postId.toString()));

        City city = cityRepository.findByNameAndCountryName(immobilierPostUpdateDto.getCityname(),
                immobilierPostUpdateDto.getCountryName()).orElseGet(() -> {
                    return cityRepository.save(City.builder().countryName(immobilierPostUpdateDto.getCountryName())
                            .name(immobilierPostUpdateDto.getCityname()).build());
                });

        Long immobilierid = post.getImmobilier().getId();
        Immobilier immobilier = immobilierService.updateImmobilier(immobilierid, city, immobilierPostUpdateDto);

        immobilierPostRepository.save(immobilierPostMapper.dtoUpateToModel(immobilierPostUpdateDto, immobilier));
    }

}
