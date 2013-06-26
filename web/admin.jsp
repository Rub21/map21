<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE>
<%

    String usuario = (String) session.getAttribute("user");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
    }

%>


<html>
    <head>
        <title>Admin</title>
        <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
        <link rel='stylesheet' href='css/style.css' />   
        <link rel='stylesheet' href='css/bootstrap.css' />
        <link rel='stylesheet' href='css/bootstrapSwitch.css' />

        <link href='css/mapbox.css' rel='stylesheet' />
    </head>
    <body>

        <div id='masthead'>
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a href="#" class="brand">Administración de Puntos de Desechos</a>
                        <div class="nav-collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">Inicio</a></li>

                            </ul>

                            <div class="pull-right">
                                Bienvenido : ${user}

                                <p><a href="${pageContext.request.contextPath}/SCerrarsesion">Cerrar Sesion</a></p>

                            </div>
                        </div><!-- /.nav-collapse -->
                    </div>
                </div>
            </div>
        </div>
        <div class="content_bandera">
            <div class="btn-group" id="uniqueID">
                <input type="button" class="btn b1 btn-primary" value="Puntos Pendientes">
                <input type="button" class="btn b0 " value="Puntos Atendidos">
            </div>


        </div>

        <div class="content_map_menu">   
            <div class="content_menu">
                <div class="accordion" id="accordion_moth">            
                </div>

            </div>

            <div id="map">

            </div>
        </div>
       <div id="popover" class="modal container hide fade" tabindex="-1">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h5>
                    Punto registrado a las: <spam class="hora"></spam>  Del Dia: <span class="fecha"></span>

                </h5>
                <p>
                    Del Usuario :
                <spam class="usuario"></spam>
                </p>
            </div>

            <div class="modal-body">

                <div id="mensaje_alert"></div>
                <div class="row">
                    <div class="span10">
                        <p class="description"></p>
                    </div>
                    <div class="span10" style="text-align: center">
                        <img class="img" alt="" style="max-width: 800px" ></div>
                </div>
            </div>

            <div class="modal-footer">
                <form name="form" id="form"  action="Servlet_Actualizar">  

                    <input name="id_punto" id="id_punto"/>
                    <button type="button"  id="button_submit" class="btn btn-success">Registrar Como Punto Atendido</button>  

                </form>

                <button type="button" data-dismiss="modal" class="btn btn-primary">Close</button>

            </div>
        </div>

        <a href="#popover"  class="click"  data-toggle="modal"></a>

        <script src='http://code.jquery.com/jquery-1.8.2.js'></script>

        <!--Scripts-->		
        <script src='http://api.tiles.mapbox.com/mapbox.js/v0.6.6/mapbox.js'></script>



        <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
        <script src='js/underscore.min.js'></script>


        <script src='js/get_data.js'></script>
        <script src='js/puntos.js'></script>
        <script src='js/app.js' ></script>   

    </body>
</html>
