package com.novlion.appjwtreg.controller;

import com.novlion.appjwtreg.dto.LoginDto;
import com.novlion.appjwtreg.dao.RegDao;
import com.novlion.appjwtreg.dto.LoginDto;
import com.novlion.appjwtreg.dto.UserDto;
import com.novlion.appjwtreg.service.LoginService;
import com.novlion.appjwtreg.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}
