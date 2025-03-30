package com.iticbcn.mywebbapp.llibresapp.Serveis;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;

import java.util.Optional;
import java.util.Set;

public interface LlibreService {

    Set<Llibre> findAll();

    Optional<Llibre> findByTitol(String titol);

    Set<Llibre> findByTitolAndEditorial(String titol, String editorial);

    Optional<Llibre> findByIdLlibre(int id);

    boolean validarISBN(String isbn);

    void save(Llibre llibre);
}
