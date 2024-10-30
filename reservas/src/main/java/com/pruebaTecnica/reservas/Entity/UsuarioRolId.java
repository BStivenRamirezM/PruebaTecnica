package com.pruebaTecnica.reservas.Entity;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioRolId implements Serializable {
    private Long usuario;
    private Long rol;

    // Constructors, equals, and hashCode
    public UsuarioRolId() {}

    public UsuarioRolId(Long usuario, Long rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public Long getUsuario() {
        return usuario;
    }

    public Long getRol() {
        return rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRolId)) return false;
        UsuarioRolId that = (UsuarioRolId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, rol);
    }
}
