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

    @GetMapping("/crear_usuario") // Nuevo método para manejar la ruta
    public String crearUsuario() {
        return "crear_usuario"; // Devuelve la vista crear_usuario.html
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

            // Verificar si el usuario es administrador
            if (user.esAdministrador()) {
                return "redirect:/dashboard"; // Redirigir al dashboard si es admin
            } else {
                return "redirect:/userDashboard"; // Redirigir a una vista diferente para usuarios normales
            }
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login"; // Volver a la página de inicio de sesión si las credenciales no son válidas
        }
    }
}
