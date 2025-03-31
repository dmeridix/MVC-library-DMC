package com.iticbcn.mywebbapp.llibresapp.Serveis;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebbapp.llibresapp.Repositoris.Repositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class LlibreServeiImpl implements LlibreService {

    private final Repositori llibreRepositori;

    @Autowired
    public LlibreServeiImpl(Repositori llibreRepositori) {
        this.llibreRepositori = llibreRepositori;
    }

    @Override
    public List<Llibre> findAll() {
        List<Llibre> result = llibreRepositori.findAll();
        System.out.println("Número de libros encontrados: " + result.size());
        return result;
    }

    @Override
    public Llibre findByTitol(String titol) {
        if (titol == null) {
            return null;
        }
        return llibreRepositori.findAll().stream()
                .filter(llibre -> titol.equalsIgnoreCase(llibre.getTitol()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Llibre> findByTitolAndEditorial(String titol, String editorial) {
        ArrayList<Llibre> result = new ArrayList<>();
        llibreRepositori.findAll().stream()
                .filter(llibre -> llibre.getTitol().equalsIgnoreCase(titol) &&
                        llibre.getEditorial().equalsIgnoreCase(editorial))
                .forEach(result::add);
        return result;
    }

    @Override
    public Optional<Llibre> findByIdLlibre(int id) {
        return llibreRepositori.findById(id);
    }

    @Override
    public boolean validarISBN(String isbn) {
        return isbn != null && isbn.matches("\\d{13}");
    }

    @Override
    public boolean validarDataPub(String dataStr) {
        System.out.println("Validant data de publicació: " + dataStr);
        try {
            LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void save(Llibre llibre) {
        if (llibre == null || llibre.getTitol() == null || llibre.getAutor() == null) {
            throw new IllegalArgumentException("El llibre no pot tenir camps obligatoris nuls");
        }
        Llibre saved = llibreRepositori.save(llibre);
        System.out.println("Llibre guardat amb id: " + saved.getIdLlibre());
    }
}