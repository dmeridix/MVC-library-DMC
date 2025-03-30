package com.iticbcn.mywebbapp.llibresapp.Repositoris;

import org.springframework.stereotype.Repository;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
@Repository
public interface Repositori extends CrudRepository<Llibre, Integer> {
    @Override
    @NonNull
    Set<Llibre> findAll();
    Llibre findByTitol(String titol);
    Set<Llibre> findByTitolAndEditorial(String titol, String editorial);
}
