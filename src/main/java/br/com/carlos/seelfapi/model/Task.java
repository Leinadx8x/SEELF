package br.com.carlos.seelfapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks") 
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true) 
    private String description;

    @Column(nullable = false)
    private String priority; 

    @Column(nullable = false)
    private String status; 

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true) 
    private LocalDateTime completedAt;
}