
document.addEventListener('DOMContentLoaded', function() {
    cargarServicios();

    document.getElementById('reservaForm').addEventListener('submit', function(event) {
        event.preventDefault();
        crearReserva();
    });

    document.getElementById('fechaReserva').addEventListener('change', function() {
        const servicioId = document.getElementById('servicioId').value;
        const fecha = this.value;
        if (servicioId && fecha) {
            cargarHorasDisponibles(servicioId, fecha);
        }
    });


    document.getElementById('horaInicio').addEventListener('change', function() {
        const horaInicio = this.value;
        cargarHorasFin(horaInicio);
    });
});

function cargarServicios() {
    fetch('/api/servicios')
        .then(response => response.json())
        .then(servicios => {
            const select = document.getElementById('servicioId');
            servicios.forEach(servicio => {
                const option = document.createElement('option');
                option.value = servicio.id;
                option.textContent = servicio.nombreServicio;
                select.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar servicios:', error);
            mostrarError('Error al cargar los servicios disponibles');
        });
}

function crearReserva() {
    const reserva = {
        usuario: {
            id: document.getElementById('usuarioId').value
        },
        servicio: {
            id: document.getElementById('servicioId').value
        },
        fechaReserva: document.getElementById('fechaReserva').value,
        horaInicio: document.getElementById('horaInicio').value,
        horaFin: document.getElementById('horaFin').value,
        estado: 'PENDIENTE'
    };

    fetch('/api/reservas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reserva)
    })
    .then(response => {
        if (response.ok) {
            mostrarMensajeExito('Reserva creada exitosamente');
            document.getElementById('reservaForm').reset();
        } else {
            throw new Error('Error al crear la reserva');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        mostrarError('Error al crear la reserva');
    });
}

function cargarHorasDisponibles(servicioId, fecha) {

    fetch(`/api/horarios-disponibles/disponibilidad?servicioId=${servicioId}&fecha=${fecha}`)
        .then(response => response.json())
        .then(horarios => {
            const selectHoraInicio = document.getElementById('horaInicio');
            selectHoraInicio.innerHTML = '<option value="">Seleccione hora de inicio</option>';

            horarios.forEach(horario => {
                const option = document.createElement('option');
                option.value = horario.horaInicio;
                option.textContent = formatearHora(horario.horaInicio);
                selectHoraInicio.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar horas disponibles:', error);
            mostrarError('Error al cargar las horas disponibles');
        });
}

function cargarHorasFin(horaInicio) {
    if (!horaInicio) return;

    const duracionMinima = 1; // en horas
    const duracionMaxima = 3; // en horas

    const selectHoraFin = document.getElementById('horaFin');
    selectHoraFin.innerHTML = '<option value="">Seleccione hora de fin</option>';

    let hora = new Date(`2000-01-01T${horaInicio}`);
    const horaLimite = new Date(hora.getTime() + (duracionMaxima * 60 * 60 * 1000));

    while (hora <= horaLimite) {
        if (hora > new Date(`2000-01-01T${horaInicio}`)) {
            const option = document.createElement('option');
            option.value = hora.toTimeString().slice(0, 5);
            option.textContent = formatearHora(hora.toTimeString().slice(0, 5));
            selectHoraFin.appendChild(option);
        }
        hora = new Date(hora.getTime() + (60 * 60 * 1000));
    }
}

function formatearHora(hora) {

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

function mostrarMensajeExito(mensaje) {
    const errorContainer = document.getElementById('errorMessageContainer');
    errorContainer.innerHTML = `<div class="success-message">${mensaje}</div>`;
    setTimeout(() => {
        errorContainer.innerHTML = '';
    }, 3000);
}