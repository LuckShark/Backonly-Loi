package com.lucas.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Course {

    @Id //indica a chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) //faz uma sequência quando a gente faz um insert no banco de dados
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String category;
}
