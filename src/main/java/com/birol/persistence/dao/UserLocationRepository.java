package com.birol.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birol.persistence.model.User;
import com.birol.persistence.model.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
