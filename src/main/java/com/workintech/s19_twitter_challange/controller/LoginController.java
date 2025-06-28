package com.workintech.s19_twitter_challange.controller;

import com.workintech.s19_twitter_challange.dto.UserRequestDto;
import com.workintech.s19_twitter_challange.dto.UserResponseDto;
import com.workintech.s19_twitter_challange.entity.User;
import com.workintech.s19_twitter_challange.mapper.UserMapper;
import com.workintech.s19_twitter_challange.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequestDto userRequestDto) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDto.getUsername(),
                            userRequestDto.getPassword()
                    )
            );

            User user = userMapper.toEntity(userRequestDto);
            return ResponseEntity.ok(userMapper.toResponseDto(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Geçersiz kullanıcı adı veya şifre");
        }
    }

}
