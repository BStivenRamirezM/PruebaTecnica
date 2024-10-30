package com.pruebaTecnica.reservas.Service;

import com.pruebaTecnica.reservas.Entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User updateUser(User user);

    User findByNombreUsuario(String nombreUsuario);

    User authenticate(String nombreUsuario, String contrasena);

    String getRolByUsername(String nombreUsuario);

}


