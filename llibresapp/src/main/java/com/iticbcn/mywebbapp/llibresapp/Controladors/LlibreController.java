package com.iticbcn.mywebbapp.llibresapp.Controladors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iticbcn.mywebbapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebbapp.llibresapp.Serveis.LlibreService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/")
public class LlibreController {

    @Autowired
    private LlibreService llibreService;

    // Pàgina inicial (login)
    @GetMapping("/")
    public String iniciar(HttpSession session, Model model) {
        if (session.getAttribute("usuari") != null) {
            return "redirect:/index"; // Redirigeix si l'usuari ja està autenticat
        }
        return "login";
    }

    // Validació d'usuari i contrasenya
    @PostMapping("/index")
    public String login(@RequestParam(name = "usuari") String usuari,
            @RequestParam(name = "password") String password,
            HttpSession session,
            Model model) {
        if ("toni".equals(usuari) && "!!".equals(password)) {
            session.setAttribute("usuari", usuari); // Guarda l'usuari a la sessió
            return "index";
        } else {
            model.addAttribute("error", "Credencials incorrectes");
            return "login";
        }
    }

    // Tancar sessió
    @PostMapping("/logout")
    public String logout(HttpSession session, SessionStatus status) {
        session.invalidate(); // Netegem la sessió
        status.setComplete();
        return "redirect:/"; // Redirigeix al login
    }

    // Pàgina principal
    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        String usuari = (String) session.getAttribute("usuari");
        if (usuari == null) {
            System.out.println("El usuario no está en la sesión.");
            return "redirect:/"; // Redirigeix al login si no hi ha usuari autenticat
        }
        model.addAttribute("usuari", usuari);
        return "index";
    }

    // Mostrar tots els llibres
    @GetMapping("/consulta")
    public String consulta(HttpSession session, Model model) {
        // Verificar si el usuario está autenticado
        String usuari = (String) session.getAttribute("usuari");
        if (usuari == null) {
            return "redirect:/"; // Redirigir al login si no hay sesión
        }

        // Obtener la lista de libros
        List<Llibre> llibres = llibreService.findAll();

        // Añadir datos al modelo
        model.addAttribute("llibres", llibres);
        model.addAttribute("usuari", usuari);
        System.out.println("Modelo contiene el atributo 'usuari': " + model.containsAttribute("usuari"));

        return "consulta";
    }

    // Formulari per inserir un nou llibre
    @GetMapping("/inserir")
    public String inputInserir() {
        return "inserir";
    }

    // Inserir un nou llibre
    @PostMapping("/inserir")
    public String inserirLlibre(@RequestParam(name = "titol") String titol,
            @RequestParam(name = "autor") String autor,
            @RequestParam(name = "editorial") String editorial,
            @RequestParam(name = "datapublicacio") String datapublicacio,
            @RequestParam(name = "tematica") String tematica,
            @RequestParam(name = "isbn") String isbn,
            Model model) {

        // Validar la data de publicació
        LocalDate data;
        if (llibreService.validarDataPub(datapublicacio)) {
            data = LocalDate.parse(datapublicacio, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            model.addAttribute("error", "Data de publicació no vàlida");
            return "inserir";
        }

        // Validar l'ISBN
        if (!llibreService.validarISBN(isbn)) {
            model.addAttribute("error", "ISBN no vàlid");
            return "inserir";
        }

        // Crear un nou llibre
        Llibre llibre = new Llibre();
        llibre.setTitol(titol);
        llibre.setAutor(autor);
        llibre.setEditorial(editorial);
        llibre.setDatapublicacio(data);
        llibre.setTematica(tematica);
        llibre.setIsbn(isbn);

        // Guardar el llibre mitjançant el servei
        llibreService.save(llibre);

        return "redirect:/consulta";
    }

    // Formulari per cercar un llibre per ID
    @GetMapping("/cercaid")
    public String inputCerca(Model model) {
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", new Llibre());
        return "cercaid";
    }

    // Cerca un llibre per ID
    @PostMapping("/cercaid")
    public String cercaId(@RequestParam(name = "idLlibre", required = true) String idLlibreStr,
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
}