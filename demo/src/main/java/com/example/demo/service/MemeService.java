package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Meme;
import com.example.demo.entity.Template;
import com.example.demo.entity.Trending;
import com.example.demo.entity.User;
import com.example.demo.repository.MemeRepository;
import com.example.demo.repository.TemplateRepository;
import com.example.demo.repository.TrendingRepository;
import com.example.demo.repository.UserRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemeService {

    private final MemeRepository memeRepository;
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final TrendingRepository trendingRepository;

    public MemeService(MemeRepository memeRepository, UserRepository userRepository,
            TemplateRepository templateRepository, TrendingRepository trendingRepository) {
        this.memeRepository = memeRepository;
        this.userRepository = userRepository;
        this.templateRepository = templateRepository;
        this.trendingRepository = trendingRepository;
    }

    public Meme createMeme(Meme meme) {
        Meme memeToSave = buildMemeForSave(meme);
        memeToSave.setCreatedAt(LocalDateTime.now());
        return memeRepository.save(memeToSave);
    }

    public Meme getMemeById(Long id) {
        return memeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meme not found"));
    }

    public List<Meme> getAllMemes() {
        return memeRepository.findAll();
    }

    public Meme updateMeme(Long id, Meme updatedMeme) {
        Meme meme = getMemeById(id);

        meme.setCaption(updatedMeme.getCaption());
        meme.setImgUrl(updatedMeme.getImgUrl());
        meme.setGenre(updatedMeme.getGenre());

        if (updatedMeme.getUser() != null && updatedMeme.getUser().getUserId() != null) {
            User user = userRepository.findById(updatedMeme.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            meme.setUser(user);
        }

        if (updatedMeme.getTemplate() != null && updatedMeme.getTemplate().getTemplateId() != null) {
            Template template = templateRepository.findById(updatedMeme.getTemplate().getTemplateId())
                    .orElseThrow(() -> new RuntimeException("Template not found"));
            meme.setTemplate(template);
        }

        if (updatedMeme.getTrends() != null) {
            List<Trending> resolvedTrends = new ArrayList<>();
            for (Trending trend : updatedMeme.getTrends()) {
                if (trend.getTrendingId() == null) {
                    throw new RuntimeException("Trending id is required");
                }
                Trending resolvedTrend = trendingRepository.findById(trend.getTrendingId())
                        .orElseThrow(() -> new RuntimeException("Trend not found"));
                resolvedTrends.add(resolvedTrend);
            }
            meme.setTrends(resolvedTrends);
        }

        return memeRepository.save(meme);
    }

    public void deleteMeme(Long id) {
        memeRepository.deleteById(id);
    }

    private Meme buildMemeForSave(Meme requestMeme) {
        Meme meme = new Meme();
        meme.setCaption(requestMeme.getCaption());
        meme.setImgUrl(requestMeme.getImgUrl());
        meme.setGenre(requestMeme.getGenre());

        if (requestMeme.getUser() != null && requestMeme.getUser().getUserId() != null) {
            User user = userRepository.findById(requestMeme.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            meme.setUser(user);
        }

        if (requestMeme.getTemplate() != null && requestMeme.getTemplate().getTemplateId() != null) {
            Template template = templateRepository.findById(requestMeme.getTemplate().getTemplateId())
                    .orElseThrow(() -> new RuntimeException("Template not found"));
            meme.setTemplate(template);
        }

        if (requestMeme.getTrends() != null) {
            List<Trending> resolvedTrends = new ArrayList<>();
            for (Trending trend : requestMeme.getTrends()) {
                if (trend.getTrendingId() == null) {
                    throw new RuntimeException("Trending id is required");
                }
                Trending resolvedTrend = trendingRepository.findById(trend.getTrendingId())
                        .orElseThrow(() -> new RuntimeException("Trend not found"));
                resolvedTrends.add(resolvedTrend);
            }
            meme.setTrends(resolvedTrends);
        }

        return meme;
    }
}


