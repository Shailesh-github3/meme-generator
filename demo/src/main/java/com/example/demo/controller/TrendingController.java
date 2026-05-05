package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Trending;
import com.example.demo.service.TrendingService;

@RestController
@RequestMapping("/trending")
public class TrendingController {

    private final TrendingService trendingService;

    public TrendingController(TrendingService trendingService) {
        this.trendingService = trendingService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTrend(@RequestBody Trending trend) {
        Trending createdTrend = trendingService.createTrend(trend);
        return ResponseEntity.created(URI.create("/trending/" + createdTrend.getTrendingId()))
                .body("Trend created successfully");
    }

    @GetMapping("/{id}")
    public Trending getTrendById(@PathVariable Long id) {
        return trendingService.getTrendById(id);
    }

    @GetMapping("/all")
    public List<Trending> getAllTrends() {
        return trendingService.getAllTrends();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateTrend(@PathVariable Long id, @RequestBody Trending trend) {
        trendingService.updateTrend(id, trend);
        return ResponseEntity.ok("Trend updated successfully");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteTrend(@PathVariable Long id) {
        trendingService.deleteTrend(id);
        return ResponseEntity.ok("Trend deleted successfully");
    }
}
