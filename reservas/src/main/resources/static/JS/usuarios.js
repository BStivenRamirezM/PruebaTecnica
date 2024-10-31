document.getElementById('userForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const usuario = obtenerDatosFormulario();
    const action = event.submitter.dataset.action;

    const url = action === 'update' ? `/api/users/edit/${usuario.id}` : '/api/users/createUser';

    if (action === 'update' && !usuario.id) {
        mostrarMensajeError("Error: El ID del usuario no está definido.");
        return;
    }

    fetch(url, {
        method: action === 'update' ? 'PUT' : 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('userForm').reset();
            fetchUsers();
            mostrarMensajeExito(action === 'update' ? "Usuario actualizado con éxito." : "Usuario creado con éxito.");
        } else if (response.status === 409) {
            mostrarMensajeError("Error: Email duplicado. Por favor, elige otro.");
        } else {
            mostrarMensajeError("Error: Email o Nombre usuario duplicado");
        }
    })
    .catch(error => {
        console.error('Hubo un problema:', error);
        mostrarMensajeError("Hubo un problema al procesar la solicitud. Por favor, intenta nuevamente.");
    });
});

function obtenerDatosFormulario() {
    const usuario = {
        nombreUsuario: document.getElementById('nombreUsuario').value,
        email: document.getElementById('email').value,
        rol: { id: 2 } // Cambia esto según el rol que desees
    };

    const contrasena = document.getElementById('contrasena').value;
    if (contrasena) {
        usuario.contrasena = contrasena; // Solo agregar si la contraseña está presente
    }

    const action = document.getElementById('saveChangesButton').dataset.action;
    if (action === 'update') {
        usuario.id = document.getElementById('userId').value; // Incluir el ID solo en actualización
    }

    return usuario;
}

// Función para mostrar mensajes de error
function mostrarMensajeError(mensaje) {
    const mensajeDiv = document.createElement('div');
    mensajeDiv.className = 'error-message';
    mensajeDiv.innerText = mensaje;

    const errorMessageContainer = document.getElementById('errorMessageContainer');
    if (errorMessageContainer) {
        errorMessageContainer.innerHTML = ''; // Limpia mensajes anteriores
        errorMessageContainer.appendChild(mensajeDiv);

        setTimeout(() => {
            mensajeDiv.remove();
        }, 3000);
    } else {
        console.error('Error: Contenedor de mensajes no encontrado.');
    }
}

// Función para mostrar mensajes de éxito
function mostrarMensajeExito(mensaje) {
    const mensajeDiv = document.createElement('div');
    mensajeDiv.className = 'success-message';
    mensajeDiv.innerText = mensaje;

    const errorMessageContainer = document.getElementById('errorMessageContainer');
    if (errorMessageContainer) {
        errorMessageContainer.innerHTML = ''; // Limpia mensajes anteriores
        errorMessageContainer.appendChild(mensajeDiv);

        setTimeout(() => {
            mensajeDiv.remove();
        }, 3000);
    }
}

function fetchUsers() {
    console.log("Cargando usuarios...");
    fetch('/api/users/all')
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la red');
            }
            return response.json();
        })
        .then(users => {
            console.log(users);
            const tableBody = document.querySelector('#usuariosTable tbody');
            tableBody.innerHTML = '';

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

// Función para eliminar un usuario
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

// Función para abrir el modal de edición de usuario
function openEditModal(userId) {
    console.log(`Abriendo modal para el usuario con ID: ${userId}`);
    fetch(`/api/users/user/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los datos del usuario.');
            }
            return response.json();
        })
        .then(user => {
            document.getElementById('userId').value = user.id;
            document.getElementById('nombreUsuario').value = user.nombreUsuario;
            document.getElementById('email').value = user.email;
            document.getElementById('contrasena').value = ''; // Limpiar la contraseña

            $('#editUserModal').modal('show'); // Muestra el modal
        })
        .catch(error => {
            console.error('Hubo un problema con la petición Fetch:', error);
        });
}


// Llamada para cargar usuarios al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    fetchUsers(); // Carga los usuarios al iniciar la página
});
