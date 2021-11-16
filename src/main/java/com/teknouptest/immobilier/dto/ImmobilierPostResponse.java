package com.teknouptest.immobilier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmobilierPostResponse {

    private Long id;
    private String title;
    private String description;

    private String imageUrl;
    private float price;
    private String publicationDuration;
    private String adresse;
    private String city;
    private int contact;
    private float superficie;

}
