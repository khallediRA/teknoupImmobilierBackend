package com.teknouptest.immobilier.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.teknouptest.immobilier.Repository.ImmobilierPostRepository;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostResponse;
import com.teknouptest.immobilier.exception.PostNotFoundException;
import com.teknouptest.immobilier.mapper.ImmobilierPostMapper;
import com.teknouptest.immobilier.model.ImmobilierPost;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@AllArgsConstructor

public class ImmobilierPostService {

    private final ImmobilierPostRepository immobilierPostRepository;
    private final ImmobilierPostMapper immobilierPostMapper;

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
        immobilierPostRepository.save(immobilierPostMapper.dtoToModel(postRequest));
    }

}
