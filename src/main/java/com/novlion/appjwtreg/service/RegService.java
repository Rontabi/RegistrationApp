package com.novlion.appjwtreg.service;

import com.novlion.appjwtreg.dao.RegDao;
import com.novlion.appjwtreg.dto.LoginDto;
import com.novlion.appjwtreg.dto.UserDto;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RegService {
    @Autowired
    RegDao regDao;
    RegService regService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public RegService(RegDao regDao) {
        this.regDao = regDao;
    }
    @Transactional
    public UserDto createUser(UserDto user) {
        String password = user.getPassword();
        if (password != null) {
            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return regDao.save(user);
    }
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public boolean isPasswordValid(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
    public List<UserDto> getAllData() {
        return regDao.findAll();
    }

    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String login = loginDto.getLogin();
        String password = loginDto.getPassword();
        if (regService.isLoginValid(login, password)) {
            return ResponseEntity.ok("Вы вошли в систему");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный логин или пароль");
    }
    public boolean isLoginValid(String username, String password) {
        UserDto userDto = regDao.findByLogin(username);
        if (userDto != null) {
            String hashedPassword = userDto.getPassword();
            return passwordEncoder.matches(password, hashedPassword);
        }
        return  false;
    }

}
