package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trending")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trendingId;

    private String category;
    private String source;
    private Double popularityScore;

    private String keyword;

    @JsonIgnore
    @ManyToMany(mappedBy = "trends")
    private List<Meme> memes;
}
