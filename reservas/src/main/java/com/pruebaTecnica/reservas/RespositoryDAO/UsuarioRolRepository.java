package com.pruebaTecnica.reservas.RespositoryDAO;

import com.pruebaTecnica.reservas.Entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

}
