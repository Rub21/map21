CREATE OR REPLACE FUNCTION insert_punto(idpunto character(40),
				        usuario character(100),				       				         
				        fecha character(50),
				        hora character(50),
				        url_img character(100),				       
				        descripcion  text,
				        tipo character(2),
				        estado boolean,
				        lat numeric,
				        lon numeric				        				            
				        )
RETURNS character(50)
AS $$
DECLARE        
BEGIN
	INSERT INTO puntos(idpunto, usuario, fecha, hora, url_img, descripcion,tipo,estado)
	VALUES (idpunto, usuario,  fecha, hora, url_img,  descripcion,tipo,estado);  
        INSERT INTO geometry(lat, lon, idpunto)
        VALUES (lat, lon, idpunto);
	RETURN 'Insert a Punto';
	
END;
$$ LANGUAGE plpgsql;


select insert_punto('330125401111071296t','ediqp8','02/05/2013','1:03 AM','http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg', '#aquid test en casa de yess http://t.co/28lBJ9UfxL','t',true,-13.251418,-74.126);

CREATE OR REPLACE VIEW select_punto AS
	SELECT p.idpunto, p.usuario, p.fecha, p.hora, p.url_img, p.descripcion, p.tipo, p.estado ,p.fecharegistro,g.lat, g.lon
	FROM puntos as p 
	left join geometry as g  on p.idpunto = g.idpunto;	

select idpunto from select_punto where tipo='w';
select idpunto from select_punto where tipo='t';


select idpunto, usuario, fecha, hora, url_img, descripcion, tipo, estado ,fecharegistro,lat, lon from select_punto where estado=true;

select idpunto, usuario, fecha, hora, url_img, descripcion, tipo, estado ,fecharegistro,lat, lon from select_punto where estado=false;



select insert_punto('000125401111071296t',
'ediqp8',
'02/04/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',
 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',true,-13.15,-74.125);


select insert_punto('000125401424071296t',
'ediqp8',
'12/01/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',

 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',true,-13.45,-74.025);


select insert_punto('000000401111071296t',
'ediqp8',
'06/04/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',
 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',true,-13.14,-74.025);


select insert_punto('0001254011414511071296t',
'ediqp8',
'02/03/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',

 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',true,-13.111,-74.000);


select insert_punto('00046601111071296t',
'ediqp8',
'06/03/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',

 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',true,-13.005,-74.025);


select insert_punto('000145601111071296t',
'ediqp8',
'06/03/2013',
'1:03 AM',
'http://pbs.twimg.com/media/BJTXTsDCQAEpYrL.jpg',

 '#aquid test en casa de yess http://t.co/28lBJ9UfxL',
 't',false,-13.00505,-74.15);


 /************************************************************************************************************estadisticas*/
 --selecion puntos de una fecha 
CREATE OR REPLACE FUNCTION puntos_pendientes(_fecha varchar(50))
RETURNS  INTEGER
AS $$
DECLARE
	_count INTEGER;	
	BEGIN
		_count=(select count(*) as day   FROM puntos where fecha=_fecha and estado=true);	
	RETURN _count;
	END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION puntos_atendidos(_fecha varchar(50))
RETURNS  INTEGER
AS $$
DECLARE
	_count INTEGER;	
	BEGIN
		_count=(select count(*) as day   FROM puntos where fecha=_fecha and estado=false);	
	RETURN _count;
	END;
$$ LANGUAGE plpgsql;

SELECT idpunto, usuario, fecha, hora, url_img, descripcion, 
       tipo, estado, fecharegistro
  FROM puntos;


select puntos_pendientes('01/05/2013');
select puntos_atendidos('01/05/2013');

---vista para seleccion
CREATE OR REPLACE VIEW select_stadistic AS
	select fecha,puntos_pendientes(fecha)as p_pen,puntos_atendidos(fecha)  as p_ate
	from puntos GROUP BY fecha 
	ORDER By to_date(fecha,'dd/mm/yyyy') ASC;

SELECT fecha,p_pen, p_ate FROM select_stadistic;



CREATE OR REPLACE VIEW select_stadistic_month AS
	SELECT substring(fecha,4,7) as month,
	sum(p_pen) as p_pen, 
	sum(p_ate)  as p_ate
	FROM select_stadistic GROUP BY  month ORDER By month ASC;


select month, p_pen,p_ate from select_stadistic_month;

