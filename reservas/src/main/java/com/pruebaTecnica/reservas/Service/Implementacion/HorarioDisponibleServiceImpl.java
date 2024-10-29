package com.pruebaTecnica.reservas.Service.Implementacion;
import com.pruebaTecnica.reservas.Entity.HorarioDisponible;
import com.pruebaTecnica.reservas.RespositoryDAO.HorarioDisponibleRepository;
import com.pruebaTecnica.reservas.Service.HorarioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioDisponibleServiceImpl implements HorarioDisponibleService {

    @Autowired
    private HorarioDisponibleRepository horarioDisponibleRepository;

    @Override
    public HorarioDisponible saveHorarioDisponible(HorarioDisponible horarioDisponible) {
        return horarioDisponibleRepository.save(horarioDisponible);
    }

    @Override
    public HorarioDisponible getHorarioDisponibleById(Long id) {
        return horarioDisponibleRepository.findById(id).orElse(null);
    }

    @Override
    public List<HorarioDisponible> getAllHorariosDisponibles() {
        return horarioDisponibleRepository.findAll();
    }

    @Override
    public void deleteHorarioDisponible(Long id) {
        horarioDisponibleRepository.deleteById(id);
    }

    @Override
    public HorarioDisponible updateHorarioDisponible(HorarioDisponible horarioDisponible) {
        return horarioDisponibleRepository.save(horarioDisponible);
    }
}
