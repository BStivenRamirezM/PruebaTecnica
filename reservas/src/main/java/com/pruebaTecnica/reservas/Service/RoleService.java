package com.pruebaTecnica.reservas.Service;

import com.pruebaTecnica.reservas.Entity.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
    void deleteRole(Long id);
    Role updateRole(Role role);
}