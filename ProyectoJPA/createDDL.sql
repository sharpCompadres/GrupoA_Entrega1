CREATE TABLE ACTIVIDAD (ID_ACTIVIDAD BIGINT NOT NULL, AMBITO VARCHAR(255) NOT NULL, DESCRIPCION VARCHAR(255) NOT NULL, F_FIN DATE NOT NULL, F_INICIO DATE NOT NULL, ONGS_ID_ONG INTEGER, PRIMARY KEY (ID_ACTIVIDAD))
CREATE TABLE USUARIO (DNI INTEGER NOT NULL, DTYPE VARCHAR(31), APELLIDOS VARCHAR(255) NOT NULL, EMAIL VARCHAR(255) NOT NULL, F_NACMIMIENTO DATE, NOMBRE VARCHAR(255) NOT NULL, PASSWORD VARCHAR(8) NOT NULL, GRADO_FACULTAD VARCHAR(255), TIPO_SERVICIO VARCHAR(255), DEPARTAMENTO VARCHAR(255), DESPACHO VARCHAR(255), PRIMARY KEY (DNI))
CREATE TABLE ESTADOACTIVIDAD (ESTADO VARCHAR(255) NOT NULL, ID_ACTIVIDAD BIGINT NOT NULL, DNI INTEGER NOT NULL, PRIMARY KEY (ID_ACTIVIDAD, DNI))
CREATE TABLE INFORME (ID_INFORME BIGINT NOT NULL, CALIFICACION FLOAT, DESCRIPCION VARCHAR(255) NOT NULL, ALUMNO_DNI INTEGER, PDI_DNI INTEGER, ACT_ID_ACTIVIDAD BIGINT NOT NULL, PRIMARY KEY (ID_INFORME, ACT_ID_ACTIVIDAD))
CREATE TABLE ONG (ID_ONG INTEGER NOT NULL, AMBITO VARCHAR(255) NOT NULL, EMAIL VARCHAR(255) NOT NULL, NOMBRE VARCHAR(255) NOT NULL, TELEFONO INTEGER NOT NULL, PRIMARY KEY (ID_ONG))
CREATE TABLE USUARIO_INFORME (Alumno_DNI INTEGER NOT NULL, ID_INFORME BIGINT NOT NULL, ACT_ID_ACTIVIDAD BIGINT NOT NULL, Pdi_DNI INTEGER NOT NULL, PRIMARY KEY (Alumno_DNI, ID_INFORME, ACT_ID_ACTIVIDAD, Pdi_DNI))
CREATE TABLE ONG_ACTIVIDAD (ONG_ID_ONG INTEGER NOT NULL, ONG_ACT_ID_ACTIVIDAD BIGINT NOT NULL, PRIMARY KEY (ONG_ID_ONG, ONG_ACT_ID_ACTIVIDAD))
ALTER TABLE ACTIVIDAD ADD CONSTRAINT FK_ACTIVIDAD_ONGS_ID_ONG FOREIGN KEY (ONGS_ID_ONG) REFERENCES ONG (ID_ONG)
ALTER TABLE ESTADOACTIVIDAD ADD CONSTRAINT FK_ESTADOACTIVIDAD_ID_ACTIVIDAD FOREIGN KEY (ID_ACTIVIDAD) REFERENCES ACTIVIDAD (ID_ACTIVIDAD)
ALTER TABLE ESTADOACTIVIDAD ADD CONSTRAINT FK_ESTADOACTIVIDAD_DNI FOREIGN KEY (DNI) REFERENCES USUARIO (DNI)
ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_PDI_DNI FOREIGN KEY (PDI_DNI) REFERENCES USUARIO (DNI)
ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_ACT_ID_ACTIVIDAD FOREIGN KEY (ACT_ID_ACTIVIDAD) REFERENCES ACTIVIDAD (ID_ACTIVIDAD)
ALTER TABLE INFORME ADD CONSTRAINT FK_INFORME_ALUMNO_DNI FOREIGN KEY (ALUMNO_DNI) REFERENCES USUARIO (DNI)
ALTER TABLE USUARIO_INFORME ADD CONSTRAINT FK_USUARIO_INFORME_Alumno_DNI FOREIGN KEY (Alumno_DNI) REFERENCES USUARIO (DNI)
ALTER TABLE USUARIO_INFORME ADD CONSTRAINT FK_USUARIO_INFORME_Pdi_DNI FOREIGN KEY (Pdi_DNI) REFERENCES USUARIO (DNI)
ALTER TABLE USUARIO_INFORME ADD CONSTRAINT FK_USUARIO_INFORME_ID_INFORME FOREIGN KEY (ID_INFORME, ACT_ID_ACTIVIDAD) REFERENCES INFORME (ID_INFORME, ACT_ID_ACTIVIDAD)
ALTER TABLE ONG_ACTIVIDAD ADD CONSTRAINT FK_ONG_ACTIVIDAD_ONG_ACT_ID_ACTIVIDAD FOREIGN KEY (ONG_ACT_ID_ACTIVIDAD) REFERENCES ACTIVIDAD (ID_ACTIVIDAD)
ALTER TABLE ONG_ACTIVIDAD ADD CONSTRAINT FK_ONG_ACTIVIDAD_ONG_ID_ONG FOREIGN KEY (ONG_ID_ONG) REFERENCES ONG (ID_ONG)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)