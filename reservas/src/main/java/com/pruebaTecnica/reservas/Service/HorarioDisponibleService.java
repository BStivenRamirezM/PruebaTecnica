package com.pruebaTecnica.reservas.Service;

import com.pruebaTecnica.reservas.Entity.HorarioDisponible;

import java.util.List;

public interface HorarioDisponibleService {
    HorarioDisponible saveHorarioDisponible(HorarioDisponible horarioDisponible);
    HorarioDisponible getHorarioDisponibleById(Long id);
    List<HorarioDisponible> getAllHorariosDisponibles();
    void deleteHorarioDisponible(Long id);
    HorarioDisponible updateHorarioDisponible(HorarioDisponible horarioDisponible);
    List<HorarioDisponible> findDisponiblesByServicioAndFecha(Long servicioId, String fecha);
}
