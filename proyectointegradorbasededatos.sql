-- Ponemos los dropt table por si ya existen las tablas
DROP TABLE IF EXISTS  ACTIVIDAD;
DROP TABLE IF EXISTS  USUARIO;
DROP TABLE IF EXISTS  SALA;

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

-- insertamos los datos a la tabla usuario
INSERT INTO USUARIO VALUES (1, 11111111,'Pedro Sanchez', 'DAM', 'vivamarruecos');
INSERT INTO USUARIO VALUES (2, 22222222,'Santiago Abascal', 'DAW', 'vivaespaña');
INSERT INTO USUARIO VALUES (3, 33333333,'Pablo Iglesias', 'TAFD', 'coleta1234');
INSERT INTO USUARIO VALUES (4, 44444444,'Donald Trump', 'TAFD', 'aranceles');
INSERT INTO USUARIO VALUES (5, 55555555,'Isabel Diaz Ayuso', 'DAW', 'matayayos');
INSERT INTO USUARIO VALUES (6, 66666666,'Puff Diddy', 'Educacion Infantil', 'babyoil');


-- insertamos los datos a la tabla sala
INSERT INTO SALA VALUES (1,'sala de maquinas',30);
INSERT INTO SALA VALUES (2,'pista de basket',20);
INSERT INTO SALA VALUES (3,'pista de padel',4);
INSERT INTO SALA VALUES (4,'piscina polideportivo',100);
INSERT INTO SALA VALUES (5,'sala de pilates',20);
INSERT INTO SALA VALUES (6,'campo de futbol',22);

-- insertamos los datos a la tabla actividad
INSERT INTO ACTIVIDAD VALUES (1, 'sesion de entrenamiento', 30, '2025-04-15', '10:00:00', 3, 1);
INSERT INTO ACTIVIDAD VALUES (2, 'partido de basket', 20, '2025-04-16', '11:30:00', 4, 2);
INSERT INTO ACTIVIDAD VALUES (3, 'partido de padel', 4, '2025-04-17', '09:00:00', 3, 3);
INSERT INTO ACTIVIDAD VALUES (4, 'clase natacion', 100, '2025-04-18', '12:00:00', 4, 4);
INSERT INTO ACTIVIDAD VALUES (5, 'clase de pilates', 20, '2025-04-19', '08:00:00', 3, 5);
INSERT INTO ACTIVIDAD VALUES (6, 'partido de futbol', 22, '2025-04-20', '17:00:00', 4, 6);




