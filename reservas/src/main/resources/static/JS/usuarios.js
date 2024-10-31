document.addEventListener("DOMContentLoaded", function() {
    fetchUsers();
});

// Función para obtener todos los usuarios
function fetchUsers() {
    fetch('/api/users/all')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la red');
            }
            return response.json();
        })
        .then(users => {
            const tableBody = document.querySelector('#usuariosTable tbody');
            tableBody.innerHTML = ''; // Limpia la tabla antes de agregar nuevos datos

            users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.nombreUsuario}</td>
                    <td>${user.email}</td>
                    <td>
                        <button onclick="openEditModal(${user.id})">Editar</button>
                        <button onclick="deleteUser(${user.id})">Eliminar</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Hubo un problema con la petición Fetch:', error);
        });
}

// Función para abrir un modal de edición (opcional)
function openEditModal(userId) {
    // Aquí podrías abrir un modal para editar los datos del usuario
    // y luego llamar a editUser en el evento de guardar del modal.
    const updatedData = {
        nombreUsuario: "nuevo_nombre", // Reemplaza con los datos del modal
        email: "nuevo_email@example.com",
        contrasena: "nueva_contrasena"
    };
    editUser(userId, updatedData);
}


// Función para eliminar usuario
function deleteUser(userId) {
    fetch(`/api/users/delete/${userId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el usuario.');
        }
        console.log(`Usuario con ID ${userId} eliminado.`);
        fetchUsers(); // Recarga la lista de usuarios después de la eliminación
    })
    .catch(error => {
        console.error('Hubo un problema con la petición Fetch:', error);
    });
}
//// Función para actualizar usuario
//function editUser(userId, updatedData) {
//    fetch(`/api/users/edit/${userId}`, {
//        method: 'PUT', // Cambiado a PUT para actualizar el usuario
//        headers: {
//            'Content-Type': 'application/json',
//        },
//        body: JSON.stringify(updatedData),
//    })
//    .then(response => {
//        if (!response.ok) {
//            throw new Error('Error al actualizar el usuario.');
//        }
//        return response.json();
//    })
//    .then(data => {
//        console.log('Usuario actualizado:', data);
//        fetchUsers(); // Recarga la lista de usuarios después de la actualización
//    })
//    .catch(error => {
//        console.error('Hubo un problema con la petición Fetch:', error);
//    });
//}