package com.pruebaTecnica.reservas.Service.Implementacion;
import com.pruebaTecnica.reservas.Entity.Servicio;
import com.pruebaTecnica.reservas.RespositoryDAO.ServicioRepository;
import com.pruebaTecnica.reservas.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio saveServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio getServicioById(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public Servicio updateServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
}
