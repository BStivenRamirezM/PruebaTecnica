package com.pruebaTecnica.reservas.Service.Implementacion;
import com.pruebaTecnica.reservas.Entity.Reserva;
import com.pruebaTecnica.reservas.RespositoryDAO.ReservaRepository;
import com.pruebaTecnica.reservas.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva updateReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> getReservasByUserId(Long userId) {
        return reservaRepository.findByUser_Id(userId);
    }
}
