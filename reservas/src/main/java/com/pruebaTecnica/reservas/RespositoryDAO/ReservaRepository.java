package com.pruebaTecnica.reservas.RespositoryDAO;
import com.pruebaTecnica.reservas.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
