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

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en el envío: ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        console.log('Usuario creado:', data);

        document.getElementById('userForm').reset();
    })
    .catch(error => {
        console.error('Hubo un problema:', error);
    });
});
