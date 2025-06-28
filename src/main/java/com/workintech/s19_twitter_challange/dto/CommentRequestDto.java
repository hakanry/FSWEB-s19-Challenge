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
public class CommentRequestDto {

    @NotBlank(message = "Yorum alanı boş olmamalıdır!")
    @NotNull(message = "Yorum alanı zorunludur!")
    @NotEmpty(message = "Yorum alanı boşluk olmamalıdır!")
    @Size(max = 500,message = "Yorum alanı 0-500 karakter arası olmalıdır!")
    private String commentText;
}
