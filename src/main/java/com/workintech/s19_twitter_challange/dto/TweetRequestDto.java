package com.workintech.s19_twitter_challange.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TweetRequestDto {

    @NotBlank(message = "Tweet boş olmamalıdır!")
    @NotNull(message = "Tweet zorunludur!")
    @NotEmpty(message = "Tweet boşluk olmamalıdır!")
    @Size(max = 100,message = "Tweet 0-1200 karakter arası olmalıdır!")
    private String tweetText;
}
