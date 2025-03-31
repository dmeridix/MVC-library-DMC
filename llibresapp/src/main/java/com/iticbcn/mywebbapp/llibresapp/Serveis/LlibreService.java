package com.iticbcn.mywebbapp.llibresapp.Serveis;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LlibreService {

    List<Llibre> findAll();

    Llibre findByTitol(String titol);

    ArrayList<Llibre> findByTitolAndEditorial(String titol, String editorial);

    Optional<Llibre> findByIdLlibre(int id);

    boolean validarISBN(String isbn);

    boolean validarDataPub(String dataPublicacio);
    void save(Llibre llibre);
}
