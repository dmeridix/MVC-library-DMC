package com.iticbcn.mywebbapp.llibresapp.Repositoris;

import org.springframework.stereotype.Repository;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
@Repository
public class Repositori extends CrudRepository<Llibre, Integer> {
    @Override
    @NonNull
    Set<Llibre> findAll();
}
