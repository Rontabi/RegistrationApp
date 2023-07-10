package com.novlion.appjwtreg.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDto {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String login;
    private String password;
}
