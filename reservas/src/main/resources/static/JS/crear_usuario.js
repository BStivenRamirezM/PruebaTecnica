document.getElementById('userForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const nombreUsuario = document.getElementById('nombreUsuario').value;
    const email = document.getElementById('email').value;
    const contrasena = document.getElementById('contrasena').value;

    const usuario = {
        nombreUsuario: nombreUsuario,
        email: email,
        contrasena: contrasena,
        rol: { id: 2 }
    };

    fetch('/api/users/createUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (response.ok) {

            document.getElementById('userForm').reset();

        } else if (response.status === 409) {

            mostrarMensajeError("Error: Email duplicado. Por favor, elige otro.");
        } else {

            mostrarMensajeError("Error: Email o Nombre usuario Duplicado");
        }
    })
    .catch(error => {
        console.error('Hubo un problema:', error);
        mostrarMensajeError("Hubo un problema al crear el usuario. Por favor, intenta nuevamente.");
    });
});


function mostrarMensajeError(mensaje) {
    const mensajeDiv = document.createElement('div');
    mensajeDiv.className = 'error-message';
    mensajeDiv.innerText = mensaje;

    const errorMessageContainer = document.getElementById('errorMessageContainer');
    errorMessageContainer.innerHTML = '';
    errorMessageContainer.appendChild(mensajeDiv);


    setTimeout(() => {
        mensajeDiv.remove();
    }, 3000);
}
