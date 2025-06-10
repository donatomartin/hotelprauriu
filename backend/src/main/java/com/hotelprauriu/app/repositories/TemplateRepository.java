package com.hotelprauriu.app.repositories;

import com.hotelprauriu.app.entities.Template;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends CrudRepository<Template, UUID> {}
