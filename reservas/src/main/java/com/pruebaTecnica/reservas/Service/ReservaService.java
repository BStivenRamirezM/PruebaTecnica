package com.pruebaTecnica.reservas.Service;

import com.pruebaTecnica.reservas.Entity.Reserva;

import java.util.List;

public interface ReservaService {
    Reserva saveReserva(Reserva reserva);
    Reserva getReservaById(Long id);
    List<Reserva> getAllReservas();
    void deleteReserva(Long id);
    Reserva updateReserva(Reserva reserva);
}





