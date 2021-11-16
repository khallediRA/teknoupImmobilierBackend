
package com.teknouptest.immobilier.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ImmobilierPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Immobilier cannot be blank")
    private String title;

    private Instant createdDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "immobilierId", referencedColumnName = "id")
    private Immobilier immobilier;

    private int contact;

    private float superficie;

}
