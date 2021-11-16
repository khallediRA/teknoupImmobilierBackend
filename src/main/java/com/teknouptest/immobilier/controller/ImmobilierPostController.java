package com.teknouptest.immobilier.controller;

import java.util.List;

import com.teknouptest.immobilier.Service.ImmobilierPostService;
import com.teknouptest.immobilier.dto.ImmobilierPostRequest;
import com.teknouptest.immobilier.dto.ImmobilierPostResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class ImmobilierPostController {
    private final ImmobilierPostService postService;

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<ImmobilierPostResponse>> getAllPost() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllImmobilierPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImmobilierPostResponse> getImmobilierPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getImmobilierPost(id));
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody ImmobilierPostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
