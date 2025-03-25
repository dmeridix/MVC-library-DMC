package com.iticbcn.mywebbapp.llibresapp.Model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Llibre {
    private int idLlibre;
    private String titol;
    private String autor;
    private String editorial;
    private LocalDate datapublicacio;
    private String tematica;  
    private String isbn;
}