package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Trending;
import com.example.demo.repository.TrendingRepository;

import java.util.List;

@Service
public class TrendingService {

    private final TrendingRepository trendingRepository;

    public TrendingService(TrendingRepository trendingRepository) {
        this.trendingRepository = trendingRepository;
    }

    public Trending createTrend(Trending trend) {
        return trendingRepository.save(trend);
    }

    public Trending getTrendById(Long id) {
        return trendingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trend not found"));
    }

    public List<Trending> getAllTrends() {
        return trendingRepository.findAll();
    }

    public Trending updateTrend(Long id, Trending updatedTrend) {
        Trending trend = getTrendById(id);

        trend.setCategory(updatedTrend.getCategory());
        trend.setSource(updatedTrend.getSource());
        trend.setPopularityScore(updatedTrend.getPopularityScore());
        trend.setKeyword(updatedTrend.getKeyword());

        return trendingRepository.save(trend);
    }

    public void deleteTrend(Long id) {
        trendingRepository.deleteById(id);
    }
}

