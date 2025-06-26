package com.workintech.s19_twitter_challange.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @NotBlank(message = "Yorum alanı boş olmamalıdır!")
    @NotNull(message = "Yorum alanı zorunludur!")
    @NotEmpty(message = "Yorum alanı boşluk olmamalıdır!")
    @Size(max = 500,message = "Yorum alanı 0-500 karakter arası olmalıdır!")
    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "tuser_id")
    private User user;


}
