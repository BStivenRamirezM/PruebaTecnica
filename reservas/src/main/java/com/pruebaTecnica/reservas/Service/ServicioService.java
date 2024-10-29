package com.pruebaTecnica.reservas.Service;

import com.pruebaTecnica.reservas.Entity.Servicio;

import java.util.List;

public interface ServicioService {
    Servicio saveServicio(Servicio servicio);
    Servicio getServicioById(Long id);
    List<Servicio> getAllServicios();
    void deleteServicio(Long id);
    Servicio updateServicio(Servicio servicio);
}
