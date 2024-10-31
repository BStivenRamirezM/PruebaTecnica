package com.pruebaTecnica.reservas.RespositoryDAO;
import com.pruebaTecnica.reservas.Entity.HorarioDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioDisponibleRepository extends JpaRepository<HorarioDisponible, Long> {

    List<HorarioDisponible> findByServicioIdAndFechaAndDisponibleTrue(Long servicioId, String fecha);

}
