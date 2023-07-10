package com.novlion.appjwtreg.controller;

import com.novlion.appjwtreg.dao.RegDao;
import com.novlion.appjwtreg.dto.LoginDto;
import com.novlion.appjwtreg.dto.UserDto;
import com.novlion.appjwtreg.service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("registration")
@RestController
public class RegController {
    @Autowired
    RegService regService;
    @PostMapping("userReg")
    public UserDto createUser(@RequestBody UserDto user) {
        return regService.createUser(user);
    }

    @GetMapping("/getData")
    public List<UserDto> getAllData(UserDto user){
        return regService.getAllData();
    }

}
