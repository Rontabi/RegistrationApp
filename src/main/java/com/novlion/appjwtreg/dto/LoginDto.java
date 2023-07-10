package com.novlion.appjwtreg.dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class LoginDto {
    private String login;
    private String password;
}
