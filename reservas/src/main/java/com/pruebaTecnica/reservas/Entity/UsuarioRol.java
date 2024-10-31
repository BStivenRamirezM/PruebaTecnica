package com.pruebaTecnica.reservas.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario_rol")
@IdClass(UsuarioRolId.class)
public class UsuarioRol {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference // Evita la serialización en el lado del "hijo"
    private User usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Role rol;

    // Getters y Setters
    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
