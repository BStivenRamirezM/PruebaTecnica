package com.pruebaTecnica.reservas.RespositoryDAO;
import com.pruebaTecnica.reservas.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
