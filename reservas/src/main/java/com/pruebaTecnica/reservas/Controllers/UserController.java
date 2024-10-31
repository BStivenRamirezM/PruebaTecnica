package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User createdUser = userService.saveUser(user);
//        return ResponseEntity.ok(createdUser);
//    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario creado");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping ("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no se encuentra
        }

        user.setId(id);
        User updatedUser = userService.updateUser(user); // Aquí llamas a tu servicio para actualizar
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
