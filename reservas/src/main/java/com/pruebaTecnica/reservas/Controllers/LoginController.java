package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.Service.Implementacion.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String nombreUsuario,
            @RequestParam("password") String contrasena,
            HttpSession session,
            Model model) {

        User user = userService.authenticate(nombreUsuario, contrasena);

        if (user != null) {
            session.setAttribute("user", user); // Guardar usuario en la sesión
            return "redirect:/dashboard"; // Redirigir al dashboard
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login"; // Volver a la página de inicio de sesión
        }
    }
}
