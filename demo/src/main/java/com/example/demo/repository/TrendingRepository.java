package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Trending;

public interface TrendingRepository extends JpaRepository<Trending, Long> {
    

}
