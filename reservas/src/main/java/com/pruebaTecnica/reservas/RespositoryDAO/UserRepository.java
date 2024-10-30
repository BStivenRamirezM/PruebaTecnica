package com.pruebaTecnica.reservas.RespositoryDAO;

import com.pruebaTecnica.reservas.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNombreUsuario(String nombreUsuario);

    @Query("SELECT ur.rol.nombreRol FROM UsuarioRol ur WHERE ur.usuario.nombreUsuario = ?1")
    String getRolByUsername(String nombreUsuario);

}
