package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.Reserva;
import com.pruebaTecnica.reservas.Service.Implementacion.ReservaServiceImpl;
import com.pruebaTecnica.reservas.Service.ReservaService;
import com.pruebaTecnica.reservas.Service.UserService;
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
    public String dashboard(Principal principal, Model model) {
        String username = principal.getName();
        String rol = userService.getRolByUsername(username); // Obtener el rol del usuario

        List<Reserva> reservas;
        if ("admin".equals(rol)) {
            reservas = reservaService.getAllReservas(); // Obtener todas las reservas
        } else {
            reservas = reservaService.getReservasByUsuario(username); // Obtener solo las reservas del usuario
        }

        model.addAttribute("reservas", reservas);
        return "dashboard";
    }

}