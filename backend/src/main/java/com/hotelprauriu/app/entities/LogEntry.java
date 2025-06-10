package com.hotelprauriu.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Logs")
@Getter
@Setter
public class LogEntry {
  @Id
  @GeneratedValue(generator = "uuid")
  private UUID id;

  private LocalDateTime timestamp = LocalDateTime.now();

  private String type;

  @Column(length = 511)
  private String message;
}
