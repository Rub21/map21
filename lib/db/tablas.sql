
CREATE TABLE usuario(
	idusuario  VARCHAR(20) NOT NULL PRIMARY KEY,
	correo VARCHAR(100)  NOT NULL unique,
	nombre VARCHAR(200),	
	usuario varchar(20) NOT NULL unique,
	contrasenia text not null,	
	estado boolean not null,
	rol int,	
	fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
 );

INSERT INTO usuario(
            idusuario, correo, nombre, usuario, contrasenia, estado, rol )
VALUES ('1u','rub2106@gmail.com','Ruben Lopez Mendoza','Rub21','1234',true,1);


select count(*) as num from usuario;


/*****************************
Delincuencia
*****************************/
CREATE TABLE puntos_delincuencia
( idpunto character varying(40) NOT NULL  PRIMARY KEY,
  tipo character varying(60),
  idusuario character varying(20) NOT NULL,
  usuario character varying(60),
  fecha character varying(15),
  hora character varying(15),
  descripcion text,
  direc_ref text,
  de_conocimiento character varying(5),
  img character varying(100),
  reg_de character varying(2),
  estado boolean,
  lat numeric,
  lon numeric,
  fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);


alter table puntos_delincuencia
add constraint fk_puntos_delincuencia_usuario
Foreign key (idusuario)
references usuario(idusuario);



CREATE OR REPLACE FUNCTION insert_punto_delincuencia(idpunto character varying, 
						tipo character varying, 
						idusuario character varying,
						usuario character varying, 
						fecha character varying, 
						hora character varying, 
						descripcion text, 
						direc_ref text, 
						de_conocimiento character varying, 
						img character varying, 
						reg_de character varying, 
						estado boolean, 
						lat numeric, 
						lon numeric)
RETURNS character(50)
AS $$
DECLARE 
		_idu character varying;
		_idusuario TEXT;       
		
BEGIN	
	_idu=idusuario;
	_idusuario=(SELECT  u.idusuario  FROM usuario as u  where u.idusuario = _idu);			
			
	IF (_idusuario  IS NULL) THEN		
		INSERT INTO usuario(idusuario, correo, nombre, usuario, contrasenia, estado, rol)
		VALUES (idusuario, '', '', usuario,  idusuario , false, 2);
		
		INSERT INTO puntos_delincuencia(
                   idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, 
                   direc_ref, de_conocimiento, img, reg_de, estado, lat, lon)
	        VALUES (idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, 
                    direc_ref, de_conocimiento, img, reg_de, estado, lat, lon);

	
	ELSE	
	
	INSERT INTO puntos_delincuencia(
            idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, 
            direc_ref, de_conocimiento, img, reg_de, estado, lat, lon)
	VALUES (idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, 
            direc_ref, de_conocimiento, img, reg_de, estado, lat, lon);
        END IF;
           
	RETURN 'Insert a Punto';
	
END;
$$ LANGUAGE plpgsql;

select insert_punto_delincuencia( '341876738759995393t', 'Otros', '713303390', 'ediqp8', '04/06/2013', '11:19 AM', '#inseguridad......pelea en la calle','null', 'No', 'null', 't', true, -13.1553554, -74.2157821);

select insert_punto_delincuencia('1wd','Robo','1u','Rub21','01/06/2013','3:35 AM','asalto en las calle','ref','No','null','w',TRUE,-4.47786,-74.70703);
select insert_punto_delincuencia('2wd','Robo','1u','Rub21','01/06/2013','3:35 AM','asalto en las calle','ref','No','null','w',TRUE,-4.47786,-74.70703);

SELECT idpunto, tipo, idusuario, usuario, fecha, hora, descripcion, direc_ref, de_conocimiento, img, reg_de, estado, lat, lon, fecharegistro FROM puntos_delincuencia;

	
---vista para seleccion de date by day and month
CREATE OR REPLACE FUNCTION num_puntos_delincuencia(_fecha varchar(50))
RETURNS  INTEGER
AS $$
DECLARE
	_count INTEGER;	
	BEGIN
		_count=(select count(*) as day   FROM puntos_delincuencia where fecha=_fecha and estado=true);	
	RETURN _count;
	END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE VIEW select_date_day_delincuencia AS
	select fecha as day,num_puntos_delincuencia(fecha)as num_puntos
	from puntos_delincuencia GROUP BY fecha 
	ORDER By to_date(fecha,'dd/mm/yyyy') ASC;

SELECT day,num_puntos  FROM select_date_day_delincuencia;



CREATE OR REPLACE VIEW select_date_month_delincuencia AS
	SELECT substring(day,4,7) as month,
	sum(num_puntos) as num_puntos	FROM select_date_day_delincuencia GROUP BY  month ORDER By month ASC;



SELECT month,num_puntos FROM select_date_month_delincuencia; 


/*****************************
Agujero Negro
*****************************/
CREATE TABLE puntos_agujero
( idpunto character varying(40) NOT NULL  PRIMARY KEY,
  tipo character varying(60),
  idusuario character varying(20) NOT NULL,
  usuario character varying(60),
  calle character varying(60),
  fecha character varying(15), 
  descripcion text,
  direc_ref text,
  de_conocimiento character varying(5),
  img character varying(100),
  reg_de character varying(2),
  estado boolean,
  lat numeric,
  lon numeric,
  fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);


alter table puntos_agujero
add constraint fk_puntos_agujero_usuario
Foreign key (idusuario)
references usuario(idusuario);


CREATE OR REPLACE FUNCTION insert_punto_agujero( idpunto character varying(40),
						  tipo character varying(60),
						  idusuario character varying(20),
						  usuario character varying(60),
						  calle character varying(60),
						  fecha character varying(15), 
						  descripcion text,
						  direc_ref text,
						  de_conocimiento character varying(5),
						  img character varying(100),
						  reg_de character varying(2),
						  estado boolean,
						  lat numeric,
						  lon numeric
						  )
RETURNS character(50)
AS $$
DECLARE        
BEGIN
		INSERT INTO puntos_agujero(idpunto, tipo, idusuario, usuario, calle, fecha, descripcion, 
			    direc_ref, de_conocimiento, img, reg_de, estado, lat, lon)
		    VALUES ( idpunto, tipo, idusuario, usuario, calle, fecha, descripcion, 
			    direc_ref, de_conocimiento, img, reg_de, estado, lat, lon);

	RETURN 'Insert a Punto';
	
END;
$$ LANGUAGE plpgsql;
select insert_punto_agujero('1wa','Agujero','1u','Rub21','Principal','01/06/2013','Agujeron en la Calle','ref','No','null','w',TRUE,-4.47786,-74.70703);
select insert_punto_agujero('2wa','Agujero','1u','Rub21','Principal','01/06/2013','Agujeron en la Calle','ref','No','null','w',TRUE,-4.786,-74.703);

SELECT idpunto, tipo, idusuario, usuario, calle, fecha, descripcion, direc_ref, de_conocimiento, img, reg_de, estado, lat, lon, fecharegistro FROM puntos_agujero;

--select count(*) as num from puntos_agujero where reg_de='w';



---vista para seleccion de date by day and month
CREATE OR REPLACE FUNCTION num_puntos_agujero(_fecha varchar(50))
RETURNS  INTEGER
AS $$
DECLARE
	_count INTEGER;	
	BEGIN
		_count=(select count(*) as day   FROM puntos_agujero where fecha=_fecha and estado=true);	
	RETURN _count;
	END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE VIEW select_date_day_agujero AS
	select fecha as day,num_puntos_agujero(fecha)as num_puntos
	from puntos_agujero GROUP BY fecha 
	ORDER By to_date(fecha,'dd/mm/yyyy') ASC;

SELECT day,num_puntos  FROM select_date_day_agujero;



CREATE OR REPLACE VIEW select_date_month_agujero AS
	SELECT substring(day,4,7) as month,
	sum(num_puntos) as num_puntos	FROM select_date_day_agujero GROUP BY  month ORDER By month ASC;



SELECT month,num_puntos FROM select_date_month_agujero; 















/*****************************
desecho
*****************************/
CREATE TABLE puntos_desecho
( idpunto character varying(40) NOT NULL  PRIMARY KEY,
  idusuario character varying(20) NOT NULL,
  usuario character varying(60),
  fecha character varying(15), 
  hora character varying(15),
  descripcion text,
  direc_ref text,
  de_conocimiento character varying(5),
  img character varying(100),
  reg_de character varying(2),
  estado boolean,
  lat numeric,
  lon numeric,
  fecharegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);


alter table puntos_desecho
add constraint fk_puntos_desecho_usuario
Foreign key (idusuario)
references usuario(idusuario);


CREATE OR REPLACE FUNCTION insert_punto_desecho( idpunto character varying(40) ,
						  idusuario character varying(20),
						  usuario character varying(60),
						  fecha character varying(15), 
						  hora character varying(15),
						  descripcion text,
						  direc_ref text,
						  de_conocimiento character varying(5),
						  img character varying(100),
						  reg_de character varying(2),
						  estado boolean,
						  lat numeric,
						  lon numeric)
  						  
RETURNS character(50)
AS $$
DECLARE        
BEGIN
		INSERT INTO puntos_desecho(idpunto, idusuario, usuario, fecha, hora, descripcion, direc_ref, 
            de_conocimiento, img, reg_de, estado, lat, lon)
             VALUES ( idpunto, idusuario, usuario, fecha, hora, descripcion, direc_ref, 
            de_conocimiento, img, reg_de, estado, lat, lon);
	RETURN 'Insert a Punto';
	
END;
$$ LANGUAGE plpgsql;

select insert_punto_desecho('1wdes','1u','Rub21','01/06/2013','100:00AM','Basuras en la Calle','ref','No','null','w',TRUE,-4.786,-74.703);

SELECT idpunto, idusuario, usuario, fecha, hora, descripcion, direc_ref, de_conocimiento, img, reg_de, estado, lat, lon, fecharegistro  FROM puntos_desecho;

select count(*) as num from puntos_desecho where reg_de='w';
