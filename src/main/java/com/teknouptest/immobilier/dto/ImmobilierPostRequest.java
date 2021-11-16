package com.teknouptest.immobilier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmobilierPostRequest {

    private String title;
    private String description;
    private String imageUrl;
    private float price;
    private String adresse;
    private String countryName;
    private String cityname;
    private int contact;
    private float superficie;

}
