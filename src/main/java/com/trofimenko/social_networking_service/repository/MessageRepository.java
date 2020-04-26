package com.trofimenko.social_networking_service.repository;

import com.trofimenko.social_networking_service.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
