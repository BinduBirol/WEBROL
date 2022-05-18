package com.birol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birol.persistence.model.NewLocationToken;
import com.birol.persistence.model.UserLocation;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
