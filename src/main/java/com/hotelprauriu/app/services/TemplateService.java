package com.hotelprauriu.app.services;

import com.hotelprauriu.app.entities.Template;
import com.hotelprauriu.app.repositories.TemplateRepository;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Iterable<Template> findAll() {
        return templateRepository.findAll();
    }

    public void save(Template template) {
        templateRepository.save(template);
    }

    public Template findById(java.util.UUID id) {
        return templateRepository.findById(id).orElse(null);
    }

    public void delete(java.util.UUID id) {
        templateRepository.deleteById(id);
    }
}
