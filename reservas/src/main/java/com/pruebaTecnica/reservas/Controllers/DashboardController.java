package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.Service.ReservaService;
import com.pruebaTecnica.reservas.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DashboardController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", user);
        return "dashboard";
    }


    @GetMapping("/crear-reserva")
    public String mostrarFormularioReserva(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", user);
        return "crear-reserva";
    }

    @GetMapping("/mis-reservas")
    public String misReservas(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", user);
        return "mis-reservas";
    }

}