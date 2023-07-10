package com.novlion.appjwtreg.service;

import com.novlion.appjwtreg.dao.RegDao;
import com.novlion.appjwtreg.dto.LoginDto;
import com.novlion.appjwtreg.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginService {
    @Autowired
    private RegDao regDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> login(LoginDto loginDto) {
        String login = loginDto.getLogin();
        String password = loginDto.getPassword();
        if (isLoginValid(login, password)) {
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
        return false;
    }
}
