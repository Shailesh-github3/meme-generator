package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Meme;

public interface MemeRepository extends JpaRepository<Meme, Long> {

    

}
