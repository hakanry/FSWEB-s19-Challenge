package com.workintech.s19_twitter_challange.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserRequestDto{

    @NotBlank(message = "Kullanıcı adı boş olmamalıdır!")
    @NotNull(message = "Kullanıcı adı zorunludur!")
    @NotEmpty(message = "Kullanıcı adı boşluk olmamalıdır!")
    @Size(max = 100,message = "Kullanıcı adı 0-100 karakter arası olmalıdır!")
    @Column(name = "username")
    private String username;


    @NotBlank(message = "Şifre boş olmamalıdır!")
    @NotNull(message = "Şifre zorunludur!")
    @NotEmpty(message = "Şifre boşluk olmamalıdır!")
    @Size(min=6,max = 30,message = "Şifre 6-30 karakter arası olmalıdır!")
    @Column(name = "password")
    private String password;

    private Boolean isAdmin;
}
