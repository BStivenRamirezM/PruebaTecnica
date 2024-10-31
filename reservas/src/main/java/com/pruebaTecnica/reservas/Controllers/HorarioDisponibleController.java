package com.pruebaTecnica.reservas.Controllers;
import com.pruebaTecnica.reservas.Entity.HorarioDisponible;
import com.pruebaTecnica.reservas.Service.HorarioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios-disponibles")
public class HorarioDisponibleController {

    @Autowired
    private HorarioDisponibleService horarioDisponibleService;

    @PostMapping
    public ResponseEntity<HorarioDisponible> createHorarioDisponible(@RequestBody HorarioDisponible horarioDisponible) {

        HorarioDisponible createdHorario = horarioDisponibleService.saveHorarioDisponible(horarioDisponible);
        return ResponseEntity.ok(createdHorario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioDisponible> getHorarioDisponibleById(@PathVariable Long id) {
        HorarioDisponible horarioDisponible = horarioDisponibleService.getHorarioDisponibleById(id);
        return horarioDisponible != null ? ResponseEntity.ok(horarioDisponible) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<HorarioDisponible>> getAllHorariosDisponibles() {
        List<HorarioDisponible> horarios = horarioDisponibleService.getAllHorariosDisponibles();
        return ResponseEntity.ok(horarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioDisponible> updateHorarioDisponible(@PathVariable Long id, @RequestBody HorarioDisponible horarioDisponible) {
        horarioDisponible.setId(id);
        HorarioDisponible updatedHorario = horarioDisponibleService.updateHorarioDisponible(horarioDisponible);
        return ResponseEntity.ok(updatedHorario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorarioDisponible(@PathVariable Long id) {
        horarioDisponibleService.deleteHorarioDisponible(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<HorarioDisponible>> getHorariosDisponibles(
            @RequestParam Long servicioId,
            @RequestParam String fecha) {

        List<HorarioDisponible> horariosDisponibles = horarioDisponibleService
                .findDisponiblesByServicioAndFecha(servicioId, fecha);
        return ResponseEntity.ok(horariosDisponibles);
    }
}
