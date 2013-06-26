<%-- 
    Document   : index
    Created on : Jun 3, 2013, 7:30:29 AM
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link type='text/css' href='css/mapbox.css' rel='stylesheet' />
        <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
        <link type='text/css' href='css/style21.css' rel='stylesheet' />
    </head>
    <body>

        <jsp:include page="templates/header.jsp"></jsp:include>

            <div id="map" class="loading">
            </div> 

            <!---Estadisticas-->
            <div class="stadistic_content well-small">
                <div class="container-fluid">
                    <div class="row-fluid">

                        <div class="span2">                                   

                        </div>


                        <div class="span3" style="text-align: center">

                            <h5>Numero  Total de Incidentes Delincuaneciales Registrados</h5> 
                            <h1 id="num_puntos"></h1>


                        </div><!--/span-->
                        <div class="span5">                          
                            <div id="draw_month"></div>
                        </div>
                        <div class="span2">                                   

                        </div>

                    </div><!--/span-->
                </div><!--/row-->

            </div><!--/.fluid-container-->
            <div class="content">
                <div class="row-fluid">

                    <div class="span2" style="text-align: center">



                    </div>
                    <div class="span10">

                    </div>

                </div>
            </div>
            <!--<div class="content">
                 <div class="draw">
                     <div id="draw_day"></div>
                 </div>-->

        </div>
    </div>
    <!---End Estadisticas-->
    <!--Detalles-->
    <div id="popover" class="modal container hide fade" tabindex="-1">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4>
                <spam class="tipo"></spam>

            </h4>
            <h6>
                Punto registrado a las: <spam class="hora"></spam>  Del Dia: <span class="fecha"></span>

            </h6>
            <p>
                Del Usuario :
            <spam class="usuario"></spam>
            </p>
        </div>

        <div class="modal-body">
            <div class="row">
                <div class="span10">
                    <p class="description"></p>
                </div>
                <div class="span10" style="text-align: center">
                    <img class="img" alt="" style="max-width: 800px" ></div>
            </div>

            <div class="row">
                <div class="span10">
                    Las Autoridades Tienen Conociemiento de este echo: <span class="de_conocimiento"></span>
                </div>
            </div>
        </div>


        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn btn-primary">Cerrar</button>

        </div>
    </div>

    <a href="#popover"  class="click"  data-toggle="modal"></a>


    <!--End Detalles-->


    <!--Menu dias-->

    <div class="tag"> 
        <a  id="slide-toggle" class="slide-toggle" href="#">Close</a>
        <span> Registro por dia</span>


    </div>
    <div class="content_menu">



        <div class="accordion" id="accordion_moth">            
        </div>

    </div>

    <!--End dias-->
    <!--Menu dias-->
    <div class="content_Descripcion">

        <div class="well-small">
            <p>
                Contribuyamos a regitrarar las incidencia delincuenciales 
                que se generan en nuetro territorio, de esta forma,
                sabremos cual el el nivel de inseguridad en el pais.
                Unete con nosostro
            </p>
        </div>

        <div class="well-small" style="text-align: center">
            <a class="btn btn-large btn-primary"  href = '../users/newuser.jsp'>Registrarse</a>

        </div>

        <div class="well-small">

            <p>Tambien Puedes Enviar un Tweet con la palabra  </p>
            <p>
                <span class="label label-important">#insegirudad</span> para ra Registrar un incidente delincuencial.
            </p>

            <p>
                <span class="label label-info">#agujero</span> para Registrar un agujero Negro en las Calles.


            </p>
            <p>
                <span class="label label-success">#tachito</span> para Registrar un puntos donde hay aculacion de basura en las calles.

            </p> 
        </div>


    </div>
    <!--End dias-->


<jsp:include page="templates/footer.jspf"></jsp:include>
<script src='http://api.tiles.mapbox.com/mapbox.js/v0.6.6/mapbox.js'></script>
<script src="http://code.jquery.com/jquery-1.8.0.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
<script src="http://underscorejs.org/underscore-min.js"></script>
<script src="js/get_data.js"></script>
<script src="js/menu.js"></script>
<script src="js/estadisticas_delincuencia.js"></script>
<script src="js/app.js"></script>
</body>
</html>
