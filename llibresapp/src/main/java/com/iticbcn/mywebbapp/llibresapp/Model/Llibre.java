package com.iticbcn.mywebbapp.llibresapp.Model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;
    private String titol;
    private String autor;
    private String editorial;
    private LocalDate datapublicacio;
    private String tematica;  
    private String isbn;
}