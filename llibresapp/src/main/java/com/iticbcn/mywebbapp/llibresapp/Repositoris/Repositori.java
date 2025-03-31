package com.iticbcn.mywebbapp.llibresapp.Repositoris;

import org.springframework.stereotype.Repository;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface Repositori extends CrudRepository<Llibre, Integer> {
    @Override
    @NonNull
    List<Llibre> findAll();
    Llibre findByTitol(String titol);
    ArrayList<Llibre> findByTitolAndEditorial(String titol, String editorial);
    Optional<Llibre> findById(int id);
}
