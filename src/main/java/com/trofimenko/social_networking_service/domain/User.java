package com.trofimenko.social_networking_service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "usr")
public class User {
    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locate;
    private LocalDateTime lastVisit;

}
