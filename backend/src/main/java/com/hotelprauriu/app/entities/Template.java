package com.hotelprauriu.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Templates")
@Getter
@Setter
public class Template {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 511)
    private String content;
}
