package com.trofimenko.social_networking_service.repository;

import com.trofimenko.social_networking_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User,String> {
}
