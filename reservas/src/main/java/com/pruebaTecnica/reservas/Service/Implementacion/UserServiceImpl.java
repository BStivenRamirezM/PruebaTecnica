package com.pruebaTecnica.reservas.Service.Implementacion;

import com.pruebaTecnica.reservas.Entity.User;
import com.pruebaTecnica.reservas.RespositoryDAO.UserRepository;
import com.pruebaTecnica.reservas.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

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
            return user; // Autenticación exitosa
        }
        return null; // Autenticación fallida
    }
}