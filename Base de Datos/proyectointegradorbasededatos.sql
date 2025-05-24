-- Ponemos los dropt table por si ya existen las tablas
DROP TABLE IF EXISTS  ACTIVIDAD;
DROP TABLE IF EXISTS  USUARIO;
DROP TABLE IF EXISTS  SALA;
DROP TABLE IF EXISTS INSCRIPCION;

-- Creamos la tabla usuario que tiene id, numero de matricula, nombre y apellidos, y ciclo, el id es el primary key
CREATE TABLE USUARIO(
	ID_USUARIO INT(4),
    N_MATRICULA INT(8),
    NOM_APE VARCHAR(50),
    CICLO VARCHAR(50),
    PASSWORD VARCHAR(50),
    CONSTRAINT USUARIO_PK PRIMARY KEY (ID_USUARIO)
);

-- creamos la tabla sala que tiene id , que tipo es y capacidad maxima, el id es la primary key
CREATE TABLE SALA(
	ID_SALA INT(3),
    TIPO_SALA VARCHAR(50),
    CAPACIDAD INT(3),
    CONSTRAINT SALA_PK PRIMARY KEY (ID_SALA)
);

-- creamos la sala de actividad que tiene id, nombre, el numero maximo de alumnos que participa en ella,
-- la fecha, la hora y tiene dos foreign constraint ue son el id de alumno y el id de sala
CREATE TABLE ACTIVIDAD(
	ID_ACTIVIDAD INT(4),
    NOM_ACTIVIDAD VARCHAR(50),
    N_MAX_ALUMNOS INT(3),
    FECHA DATE,
    HORA TIME,
    ID_USUARIO INT(4),
    ID_SALA INT(3),
    
    CONSTRAINT ACTIVIDAD_PK PRIMARY KEY (ID_ACTIVIDAD),
    CONSTRAINT USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID_USUARIO) ON DELETE CASCADE,
    CONSTRAINT SALA_FK FOREIGN KEY (ID_SALA) REFERENCES SALA (ID_SALA) ON DELETE CASCADE
);

CREATE TABLE INSCRIPCION (
    ID_USUARIO INT,
    ID_ACTIVIDAD INT,
    FECHA_INSCRIPCION DATE,
    PRIMARY KEY (ID_USUARIO, ID_ACTIVIDAD),
    FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID_USUARIO) ON DELETE CASCADE,
    FOREIGN KEY (ID_ACTIVIDAD) REFERENCES ACTIVIDAD(ID_ACTIVIDAD) ON DELETE CASCADE
);

-- insertamos los datos a la tabla usuario
INSERT INTO USUARIO VALUES (1, 11111111,'Pedro Sanchez', 'DAM', 'vivamarruecos');
INSERT INTO USUARIO VALUES (2, 22222222,'Santiago Abascal', 'DAW', 'vivaespaña');
INSERT INTO USUARIO VALUES (3, 33333333,'Pablo Iglesias', 'TAFD', 'coleta1234');
INSERT INTO USUARIO VALUES (4, 44444444,'Donald Trump', 'TAFD', 'aranceles');
INSERT INTO USUARIO VALUES (5, 55555555,'Isabel Diaz Ayuso', 'DAW', 'matayayos');
INSERT INTO USUARIO VALUES (6, 66666666,'Puff Diddy', 'Educacion Infantil', 'babyoil');


-- insertamos los datos a la tabla sala
INSERT INTO SALA VALUES (1,'Sala de máquinas',30);
INSERT INTO SALA VALUES (2,'Pista de baloncesto',20);
INSERT INTO SALA VALUES (3,'Pista de pádel',4);
INSERT INTO SALA VALUES (4,'Piscina polideportivo',100);
INSERT INTO SALA VALUES (5,'Sala multiusos',20);
INSERT INTO SALA VALUES (6,'Campo de fútbol',22);
INSERT INTO SALA VALUES (7,'Pista de atletismo',8);
INSERT INTO SALA VALUES (8,'Pista de tenis',4);


-- insertamos los datos a la tabla actividad
INSERT INTO ACTIVIDAD VALUES (1, 'Sesión de entrenamiento', 30, '2025-04-15', '10:00:00', 3, 1);
INSERT INTO ACTIVIDAD VALUES (2, 'Partido de baloncesto', 20, '2025-04-16', '11:30:00', 4, 2);
INSERT INTO ACTIVIDAD VALUES (3, 'Partido de pádel', 4, '2025-04-17', '09:00:00', 3, 3);
INSERT INTO ACTIVIDAD VALUES (4, 'Clase natación', 100, '2025-04-18', '12:00:00', 4, 4);
INSERT INTO ACTIVIDAD VALUES (5, 'Clase de pilates', 20, '2025-04-19', '08:00:00', 3, 5);
INSERT INTO ACTIVIDAD VALUES (6, 'Partido de fútbol', 22, '2025-04-20', '17:00:00', 4, 6);
INSERT INTO ACTIVIDAD VALUES (7, 'Entrenamiento de atletismo', 8, '2025-05-23', '10:00:00', 3, 7);
INSERT INTO ACTIVIDAD VALUES (8, 'Partido de tenis', 4, '2025-05-22', '10:00:00', 4, 8);





