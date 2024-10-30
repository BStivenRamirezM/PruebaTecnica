package com.pruebaTecnica.reservas.Service.Implementacion;

import com.pruebaTecnica.reservas.Entity.Role;
import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.Entity.UsuarioRol;
import com.pruebaTecnica.reservas.RespositoryDAO.RoleRepository;
import com.pruebaTecnica.reservas.RespositoryDAO.UserRepository;
import com.pruebaTecnica.reservas.RespositoryDAO.UsuarioRolRepository;
import com.pruebaTecnica.reservas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Inyectar el repositorio de roles

    @Autowired
    private UsuarioRolRepository usuarioRolRepository; // Inyectar el repositorio de UsuarioRol

    @Override
    public User saveUser(User user) {
        // Guardar el usuario
        User savedUser = userRepository.save(user);

        // Obtener el rol por defecto (ID = 2)
        Role defaultRole = roleRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        // Crear y guardar la relación UsuarioRol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(savedUser);
        usuarioRol.setRol(defaultRole);
        usuarioRolRepository.save(usuarioRol);

        return savedUser;
    }

//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByNombreUsuario(String nombreUsuario) {
        return userRepository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public User authenticate(String nombreUsuario, String contrasena) {
        User user = findByNombreUsuario(nombreUsuario);
        if (user != null && user.getContrasena().equals(contrasena)) {
            return user;
        }
        return null;
    }

    @Override
    public String getRolByUsername(String nombreUsuario) {
        return userRepository.getRolByUsername(nombreUsuario);
    }
}

