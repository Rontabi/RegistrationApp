package com.novlion.appjwtreg.dao;

import com.novlion.appjwtreg.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegDao extends JpaRepository<UserDto, Integer> {

    UserDto findByLogin(String login);
}
