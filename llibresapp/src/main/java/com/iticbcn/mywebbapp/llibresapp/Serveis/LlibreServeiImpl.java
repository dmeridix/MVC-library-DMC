package com.iticbcn.mywebbapp.llibresapp.Serveis;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebbapp.llibresapp.Repositoris.Repositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Set;

@Service
public class LlibreServeiImpl implements LlibreService {

    private final Repositori llibreRepositori;

    @Autowired
    public LlibreServeiImpl(Repositori llibreRepositori) {
        this.llibreRepositori = llibreRepositori;
    }

    @Override
    public Set<Llibre> findAll() {
        return llibreRepositori.findAll();
    }

    @Override
    public Optional<Llibre> findByTitol(String titol) {
        return Optional.ofNullable(llibreRepositori.findByTitol(titol));
    }

    @Override
    public Set<Llibre> findByTitolAndEditorial(String titol, String editorial) {
        return llibreRepositori.findByTitolAndEditorial(titol, editorial);
    }

    @Override
    public Optional<Llibre> findByIdLlibre(int id) {
        return llibreRepositori.findById(id); 
    }

    @Override
    public boolean validarISBN(String isbn) {
        try {
            int suma = 0;
            for (int i = 0; i < 13; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                suma += (i % 2 == 0) ? digit : digit * 3;
            }
    
            return suma % 10 == 0;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean validarData(String dataStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(dataStr, formatter);
            return !data.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void save(Llibre llibre) {
        llibreRepositori.save(llibre);
    }


}
