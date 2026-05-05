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

import com.example.demo.entity.Template;
import com.example.demo.service.TemplateService;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTemplate(@RequestBody Template template) {
        Template createdTemplate = templateService.createTemplate(template);
        return ResponseEntity.created(URI.create("/templates/" + createdTemplate.getTemplateId()))
                .body("Template created successfully");
    }

    @GetMapping("/{id}")
    public Template getTemplateById(@PathVariable Long id) {
        return templateService.getTemplateById(id);
    }

    @GetMapping("/all")
    public List<Template> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateTemplate(@PathVariable Long id, @RequestBody Template template) {
        templateService.updateTemplate(id, template);
        return ResponseEntity.ok("Template updated successfully");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
        return ResponseEntity.ok("Template deleted successfully");
    }
}
