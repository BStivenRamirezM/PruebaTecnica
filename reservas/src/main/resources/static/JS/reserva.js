 // JavaScript para manejar el formulario y las peticiones
    document.addEventListener('DOMContentLoaded', function() {
        // Cargar servicios disponibles
        cargarServicios();

        // Manejar el envío del formulario
        document.getElementById('reservaForm').addEventListener('submit', function(event) {
            event.preventDefault();
            crearReserva();
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