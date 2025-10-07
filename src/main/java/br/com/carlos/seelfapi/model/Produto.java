package br.com.carlos.seelfapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String brand;
    private String category;

    @Column(unique = true, nullable = false)
    private String sku;

    private int quantity;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}