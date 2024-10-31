package com.pruebaTecnica.reservas.Controllers;

import com.pruebaTecnica.reservas.Entity.Reserva;
import com.pruebaTecnica.reservas.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.saveReserva(reserva);
            return ResponseEntity.ok(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            Reserva existingReserva = reservaService.getReservaById(id);
            if (existingReserva == null) {
                return ResponseEntity.notFound().build();
            }

            existingReserva.setEstado(reserva.getEstado());

            Reserva updatedReserva = reservaService.updateReserva(existingReserva);
            return ResponseEntity.ok(updatedReserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
