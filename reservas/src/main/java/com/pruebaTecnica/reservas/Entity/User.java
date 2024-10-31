package com.pruebaTecnica.reservas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String nombreUsuario;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UsuarioRol> usuarioRols;  // Relación con la tabla intermedia

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<UsuarioRol> getUsuarioRols() {
        return usuarioRols;
    }

    public void setUsuarioRols(Set<UsuarioRol> usuarioRols) {
        this.usuarioRols = usuarioRols;
    }

    public boolean esAdministrador() {
        return usuarioRols.stream().anyMatch(rol -> rol.getRol().getId() == 1);
    }
}

