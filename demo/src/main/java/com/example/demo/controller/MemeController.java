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

import com.example.demo.entity.Meme;
import com.example.demo.service.MemeService;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMeme(@RequestBody Meme meme) {
        Meme createdMeme = memeService.createMeme(meme);
        return ResponseEntity.created(URI.create("/memes/" + createdMeme.getMemeId()))
                .body("Meme created successfully");
    }

    @GetMapping("/{id}")
    public Meme getMemeById(@PathVariable Long id) {
        return memeService.getMemeById(id);
    }

    @GetMapping("/all")
    public List<Meme> getAllMemes() {
        return memeService.getAllMemes();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateMeme(@PathVariable Long id, @RequestBody Meme meme) {
        memeService.updateMeme(id, meme);
        return ResponseEntity.ok("Meme updated successfully");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteMeme(@PathVariable Long id) {
        memeService.deleteMeme(id);
        return ResponseEntity.ok("Meme deleted successfully");
    }
}
