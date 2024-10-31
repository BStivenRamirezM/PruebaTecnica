document.addEventListener('DOMContentLoaded', function() {
    cargarReservas();
});

function cargarReservas() {
    fetch('/api/reservas')
        .then(response => response.json())
        .then(reservas => {
            const tableBody = document.querySelector('#reservasTable tbody');
            tableBody.innerHTML = '';

            reservas.forEach(reserva => {
                const row = document.createElement('tr');
                const estado = reserva.estado === 'RESERVADA' ? 'Reservada' : 'Pendiente';
                row.innerHTML = `
                    <td>${reserva.usuario ? reserva.usuario.nombreUsuario : 'N/A'}</td>
                    <td>${reserva.servicio ? reserva.servicio.nombreServicio : 'N/A'}</td>
                    <td>${reserva.fechaReserva || 'N/A'}</td>
                    <td>${reserva.horaInicio || 'N/A'}</td>
                    <td>${reserva.horaFin || 'N/A'}</td>
                    <td><span class="estado-${estado.toLowerCase()}">${estado}</span></td>
                    <td class="table-actions">
                        <button onclick="cambiarEstadoReserva(${reserva.id}, '${estado === 'Pendiente' ? 'RESERVADA' : 'PENDIENTE'}')">${estado === 'Pendiente' ? 'Confirmar' : 'Cancelar'}</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al cargar reservas:', error);
            mostrarError('Error al cargar las reservas');
        });
}

function cambiarEstadoReserva(reservaId, nuevoEstado) {
    fetch(`/api/reservas/${reservaId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: reservaId,
            estado: nuevoEstado
        })
    })
    .then(response => {
        if (response.ok) {
            cargarReservas(); // Recargar la tabla
            mostrarMensajeExito(`Reserva ${nuevoEstado === 'RESERVADA' ? 'confirmada' : 'cancelada'} exitosamente`);
        } else {
            throw new Error('Error al actualizar el estado de la reserva');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarError('Error al actualizar el estado de la reserva');
    });
}

function mostrarError(mensaje) {
    const errorContainer = document.getElementById('errorMessageContainer');
    errorContainer.innerHTML = `<div class="error-message">${mensaje}</div>`;
    setTimeout(() => {
        errorContainer.innerHTML = '';
    }, 3000);
}

function mostrarMensajeExito(mensaje) {
    const errorContainer = document.getElementById('errorMessageContainer');
    errorContainer.innerHTML = `<div class="success-message">${mensaje}</div>`;
    setTimeout(() => {
        errorContainer.innerHTML = '';
    }, 3000);
}