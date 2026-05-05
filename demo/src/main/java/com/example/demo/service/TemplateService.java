package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Template;
import com.example.demo.repository.TemplateRepository;

import java.util.List;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template createTemplate(Template template) {
        return templateRepository.save(template);
    }

    public Template getTemplateById(Long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public Template updateTemplate(Long id, Template updatedTemplate) {
        Template template = getTemplateById(id);

        template.setName(updatedTemplate.getName());
        template.setImgUrl(updatedTemplate.getImgUrl());
        template.setCategory(updatedTemplate.getCategory());
        template.setKeyword(updatedTemplate.getKeyword());

        return templateRepository.save(template);
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}

