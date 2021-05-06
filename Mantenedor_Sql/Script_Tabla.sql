/*
Script Tabla
Base de datos:   mantenedor
Esquema: <DEFAULT>
Objetos: TABLE
*/
DROP TABLE "tarea"
GO

CREATE TABLE "tarea"  ( 
	"IDENTIFICADOR" 	int(11) AUTO_INCREMENT NOT NULL,
	"DESCRIPCION"   	varchar(255) NULL,
	"FECHA_CREACION"	date NULL,
	"VIGENTE"       	tinyint(1) NULL,
	PRIMARY KEY("IDENTIFICADOR") USING BTREE
)
GO

