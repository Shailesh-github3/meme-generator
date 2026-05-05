package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "memes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memeId;

    private String imgUrl;
    private String caption;
    private String genre;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToMany
    @JoinTable(
        name = "meme_trending",
        joinColumns = @JoinColumn(name = "meme_id"),
        inverseJoinColumns = @JoinColumn(name = "trending_id")
    )
    private List<Trending> trends;
}
