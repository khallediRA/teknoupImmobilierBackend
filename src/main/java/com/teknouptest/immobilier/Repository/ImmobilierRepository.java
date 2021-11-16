package com.teknouptest.immobilier.Repository;

import com.teknouptest.immobilier.model.Immobilier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilierRepository extends JpaRepository<Immobilier, Long> {

}
