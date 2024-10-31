document.addEventListener('DOMContentLoaded', function() {
    cargarMisReservas();
});

function cargarMisReservas() {
    const nombreUsuario = document.querySelector('meta[name="username"]').content;

    fetch(`/api/reservas/usuario/${nombreUsuario}`)
        .then(response => response.json())
        .then(reservas => {
            const tableBody = document.querySelector('#misReservasTable tbody');
            tableBody.innerHTML = '';

            reservas.forEach(reserva => {
                const row = document.createElement('tr');
                const estado = reserva.estado === 'RESERVADA' ? 'Confirmada' : 'Pendiente';

                row.innerHTML = `
                    <td>${reserva.servicio ? reserva.servicio.nombreServicio : 'N/A'}</td>
                    <td>${reserva.fechaReserva || 'N/A'}</td>
                    <td>${formatearHora(reserva.horaInicio) || 'N/A'}</td>
                    <td>${formatearHora(reserva.horaFin) || 'N/A'}</td>
                    <td><span class="estado-${estado.toLowerCase()}">${estado}</span></td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al cargar reservas:', error);
            mostrarError('Error al cargar las reservas');
        });
}

function formatearHora(hora) {
    if (!hora) return '';
    const [hours, minutes] = hora.split(':');
    const horaNum = parseInt(hours);
    const ampm = horaNum >= 12 ? 'PM' : 'AM';
    const hora12 = horaNum % 12 || 12;
    return `${hora12}:${minutes} ${ampm}`;
}

function mostrarError(mensaje) {
    const errorContainer = document.getElementById('errorMessageContainer');
    errorContainer.innerHTML = `<div class="error-message">${mensaje}</div>`;
    setTimeout(() => {
        errorContainer.innerHTML = '';
    }, 3000);
}