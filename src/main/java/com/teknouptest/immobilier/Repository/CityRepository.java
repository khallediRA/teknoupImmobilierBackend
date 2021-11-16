package com.teknouptest.immobilier.Repository;

import java.util.Optional;

import com.teknouptest.immobilier.model.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByNameAndCountryName(String cityname, String countryName);

}
