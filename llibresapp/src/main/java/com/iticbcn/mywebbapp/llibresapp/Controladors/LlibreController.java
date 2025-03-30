package com.iticbcn.mywebbapp.llibresapp.Controladors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebbapp.llibresapp.Serveis.LlibreService;


@Controller
@RequestMapping("/")
public class LlibreController {
    @Autowired
    private LlibreService llibreService;

    // Pàgina inicial (login)
    @GetMapping("/")
    public String iniciar() {
        return "login";
    }

    // Validació d'usuari i contrasenya
    @PostMapping("/")
    public String login(@RequestParam(name = "usuari") String usuari,
                        @RequestParam(name = "password") String password,
                        Model model) {
        if ("toni".equals(usuari) && "!!".equals(password)) {
            return "index"; // Redirigeix a la pàgina principal si les credencials són correctes
        } else {
            model.addAttribute("error", "Usuari o contrasenya incorrectes");
            return "login"; // Mostra un missatge d'error i torna al formulari de login
        }
    }

    // Pàgina principal
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Mostrar tots els llibres
    @GetMapping("/consulta")
    public String consulta(Model model) {
        Set<Llibre> llibres = llibreService.findAll();
        model.addAttribute("llibres", llibres);
        return "consulta";
    }

    // Formulari per inserir un nou llibre
    @GetMapping("/inserir")
    public String inputInserir() {
        return "inserir";
    }

    // Formulari per cercar un llibre per ID
    @GetMapping("/cercaid")
    public String inputCerca(Model model) {
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", new Llibre());
        return "cercaid";
    }

    // Inserir un nou llibre
    @PostMapping("/inserir")
    public String inserirLlire(@RequestParam(name = "titol") String titol,
                               @RequestParam(name = "autor") String autor,
                               @RequestParam(name = "editorial") String editorial,
                               @RequestParam(name = "datapublicacio") String datapublicacio,
                               @RequestParam(name = "tematica") String tematica,
                               @RequestParam(name = "isbn") String isbn,
                               Model model) {

        // Convertir la data a LocalDate
        LocalDate data;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            data = LocalDate.parse(datapublicacio, formatter);
        } catch (Exception e) {
            model.addAttribute("error", "El format de la data és incorrecte (yyyy-MM-dd)");
            return "inserir";
        }

        // Validar l'ISBN
        if (!llibreService.validarISBN(isbn)) {
            model.addAttribute("error", "L'ISBN no és vàlid");
            return "inserir";
        }

        // Crear un nou llibre
        Llibre llibre = new Llibre();
        llibre.setIdLlibre(generarIdLlibre()); // Genera un ID únic per al llibre
        llibre.setTitol(titol);
        llibre.setAutor(autor);
        llibre.setEditorial(editorial);
        llibre.setDatapublicacio(data);
        llibre.setTematica(tematica);
        llibre.setIsbn(isbn);

        // Guardar el llibre mitjançant el servei
        llibreService.save(llibre);

        // Redirigir a la pàgina de consulta
        return "redirect:/consulta";
    }

    // Cerca un llibre per ID
    @PostMapping("/cercaid")
    public String cercaId(@RequestParam(name = "idLlibre", required = false) String idLlibreStr,
                          Model model) {

        int idLlibre;
        String message = "";
        boolean llibreErr = false;

        try {
            idLlibre = Integer.parseInt(idLlibreStr);

            Optional<Llibre> optionalLlibre = llibreService.findByIdLlibre(idLlibre);
            if (optionalLlibre.isPresent()) {
                model.addAttribute("llibre", optionalLlibre.get());
            } else {
                message = "No hi ha cap llibre amb aquesta id";
                llibreErr = true;
            }

        } catch (NumberFormatException e) {
            message = "La id de llibre ha de ser un nombre enter";
            llibreErr = true;
        }

        model.addAttribute("message", message);
        model.addAttribute("llibreErr", llibreErr);

        return "cercaid";
    }

    // Mètode auxiliar per generar un ID únic
    private static int contadorId = 1;

    private int generarIdLlibre() {
        return contadorId++;
    }
}