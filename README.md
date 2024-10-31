Prueba Tecnica Reservas LinkTic

Requisitos
Base de datos: PostgreSQL
Java: JDK 17 o superior
Framework: Spring Boot
Frontend: HTML, CSS, JavaScript
Instalación
Clonar el repositorio:
git clone https://github.com/tu_usuario/reservas.git
cd reservas

Crear la base de datos: Ejecuta el siguiente script SQL en tu cliente de PostgreSQL para crear la base de datos y las tablas necesarias.

-- Crear la base de datos (si es necesario)
CREATE DATABASE reservas;

-- Seleccionar la base de datos
USE reservas;

-- Crear tabla de roles
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL
);

-- Crear tabla de usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

-- Crear tabla intermedia usuario_rol para asignar roles a los usuarios
CREATE TABLE usuario_rol (
    usuario_id INT REFERENCES usuarios(id) ON DELETE CASCADE,
    rol_id INT REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, rol_id)
);

-- Crear tabla de servicios
CREATE TABLE servicios (
    id SERIAL PRIMARY KEY,
    nombre_servicio VARCHAR(100) NOT NULL,
    descripcion TEXT,
    capacidad INT NOT NULL
);

-- Crear tabla de horarios disponibles para los servicios
CREATE TABLE horarios_disponibles (
    id SERIAL PRIMARY KEY,
    servicio_id INT REFERENCES servicios(id) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);

-- Crear tabla de reservas
CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(id) ON DELETE CASCADE,
    servicio_id INT REFERENCES servicios(id) ON DELETE CASCADE,
    fecha_reserva DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado VARCHAR(50) NOT NULL
);

-- Insertar roles iniciales
INSERT INTO roles (nombre_rol) VALUES 
    ('admin'), 
    ('usuario');

-- Insertar usuarios iniciales
INSERT INTO usuarios (nombre_usuario, email, contrasena)
VALUES 
    ('admin_user', 'admin@example.com', '123456');

-- Asignar roles a los usuarios
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES
    ((SELECT id FROM usuarios WHERE nombre_usuario = 'admin_user'), (SELECT id FROM roles WHERE nombre_rol = 'admin'));

-- Insertar servicios
INSERT INTO servicios (nombre_servicio, descripcion, capacidad)
VALUES 
    ('Habitación Deluxe', 'Habitación con vista al mar', 2),
    ('Mesa Restaurante', 'Mesa para dos personas', 2),
    ('Sala de Conferencias', 'Espacio para reuniones y eventos', 20),
    ('Spa Relax', 'Zona de relajación y masajes', 5),
    ('Gimnasio', 'Sala de ejercicios y fitness', 15),
    ('Piscina', 'Piscina climatizada con vista panorámica', 10),
    ('Salón de Belleza', 'Corte, peinado y tratamientos capilares', 3),
    ('Sala VIP', 'Sala exclusiva con servicio personalizado', 8),
    ('Cine Privado', 'Sala de cine privada para grupos pequeños', 12),
    ('Cancha de Tenis', 'Cancha al aire libre para tenis', 4),
    ('Bar', 'Bar con cocteles y aperitivos', 20),
    ('Zona Infantil', 'Espacio recreativo para niños', 10);

-- Insertar horarios disponibles para los servicios
INSERT INTO horarios_disponibles (servicio_id, fecha, hora_inicio, hora_fin, disponible)
VALUES 
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Habitación Deluxe'), '2024-11-01', '14:00', '16:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Mesa Restaurante'), '2024-11-01', '18:00', '20:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Sala de Conferencias'), '2024-11-02', '09:00', '12:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Sala de Conferencias'), '2024-11-02', '13:00', '17:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Spa Relax'), '2024-11-01', '10:00', '12:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Spa Relax'), '2024-11-01', '14:00', '16:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Gimnasio'), '2024-11-01', '08:00', '22:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Piscina'), '2024-11-01', '10:00', '18:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Salón de Belleza'), '2024-11-01', '09:00', '13:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Salón de Belleza'), '2024-11-01', '14:00', '18:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Sala VIP'), '2024-11-02', '15:00', '18:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Cine Privado'), '2024-11-02', '18:00', '20:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Cine Privado'), '2024-11-02', '20:00', '22:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Cancha de Tenis'), '2024-11-03', '09:00', '11:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Bar'), '2024-11-01', '17:00', '23:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Zona Infantil'), '2024-11-01', '10:00', '18:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Sala de Conferencias'), '2024-11-03', '10:00', '13:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Mesa Restaurante'), '2024-11-03', '19:00', '21:00', TRUE),
    ((SELECT id FROM servicios WHERE nombre_servicio = 'Habitación Deluxe'), '2024-11-02', '14:00', '16:00', TRUE);


Configurar el proyecto: Edita el archivo application.properties para configurar la conexión a la base de datos y otros parámetros del proyecto.

spring.application.name=reservas

server.port=8080

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/reservas
spring.datasource.username=tu_usuario
spring.datasource.password=tu contraseña
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
