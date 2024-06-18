package com.example.phi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phi.model.UserDetailsModel;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsModel, Integer> {

}
