package com.pruebaTecnica.reservas.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirigir a la página de inicio de sesión si no está autenticado
        }
        return "dashboard"; // Mostrar la vista del dashboard
    }
}
