package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.Reserva;
import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.Service.Implementacion.ReservaServiceImpl;
import com.pruebaTecnica.reservas.Service.ReservaService;
import com.pruebaTecnica.reservas.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/userDashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", user);
        return "userDashboard"; // crear otro html para usuario normales
    }

}