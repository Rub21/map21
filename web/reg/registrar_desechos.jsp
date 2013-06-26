<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String usuario3 = (String) session.getAttribute("session_usuario");
    if (usuario3 == null) {
        response.sendRedirect("../users/login_users.jsp");
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Puntos de Desechos</title>

        <!--Styles-->
        <link rel="stylesheet" href="http://leafletjs.com/dist/leaflet.css" />
        <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="http://leafletjs.com/dist/leaflet.ie.css" />
        <![endif]-->
        <link rel="stylesheet" href="css/style21.css" />

        <link rel="stylesheet" href="css/style_desechos.css" />

        <link rel="stylesheet" href="css/style_footer.css" />
        <link rel="stylesheet" href="css/style_header.css" />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
        <link rel="stylesheet" href="css/jquery.ptTimeSelect.css" />
        <link rel='stylesheet' href='css/bootstrapSwitch.css' />
        <!--End Styles-->

    </head>

    <body>
       
            <jsp:include page="templates/header.jsp"></jsp:include>

                <div id="contentform">
                    <div id="form" >
                        <form  id="formulario" class="content well span6"  method="post"  action="<%=request.getContextPath()%>/SRegistrar_Desechos" enctype="multipart/form-data" >

                        <div class="row">
                            <div class="span4">Fecha <span class="obligatorio">*</span></div>
                            <div class="span4"> <input type="text" name="fecha" value=""  id="fecha" style="width:400px" placeholder="Haga click para mostrarla la Fecha" >
                            </div>
                        </div>

                        <div class="row">
                            <div class="span4">Hora <span class="obligatorio">*</span></div>
                            <div class="span4"> <input type="text" name="hora" value=""  id="hora" style="width:400px"  placeholder="Haga click para mostrarla Hora">
                            </div>
                        </div>

                        <div class="row">
                            <div class="span4">Descripción <span class="obligatorio">*</span></div>
                            <div class="span4">
                                <textarea name="des" rows="4" cols="75"  id="descripcion" style="width:400px" placeholder="Ingrese la descripcion del incidente"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="span4">Direcion/Referencia</div>
                            <div class="span4">
                                <textarea name="direccion" rows="2" cols="75"  id="direcion" style="width:400px" placeholder="Ingrese la direccion exacta o la referencia"></textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="span4">Latitud <span class="obligatorio">*</span></div>
                            <div class="span4">
                                <input type="text" name="lat" value=""  id="latitud" placeholder="click en el Mapa" style="width:400px">
                            </div>
                            <div class="span4">Longitud <span class="obligatorio">*</span> </div>
                            <div class="span4">
                                <input type="text" name="lon"  id="longitud" placeholder="click en el Mapa" style="width:400px">
                            </div>
                        </div>

                        <div class="row">
                            <div class="span4">Las autoridades tiene conocimiento de este Incidente :

                            </div>
                            <div class="span4 well">
                                <input type="radio" name="de_conocimiento" value="Si"> Si<br>
                                <input type="radio" name="de_conocimiento" value="No" checked> No<br>
                                <input type="radio" name="de_conocimiento" value="Nose" > Desconosco
                            </div>

                        </div>

                        <div class="row">
                            <div class="span4">Imagen del punto de desecho<span class="obligatorio">(Opcional)</span></div>
                            <div class="span4">
                                <input type="file" name="imagen"   id="imagen"/>
                            </div>
                        </div>

                        <br>
                        <button type="submit" class="btn btn-primary" id="button">  Registrar</button>
                        <button type="reset" class="btn">Cancelar</button>
                    </form>

                </div>
                <!--mapa-->
                <div id="map"></div>

                <div class="left">
                    <a id="geojsonLayer"   href="#" ></a>
                </div>

            </div>


            <jsp:include page="templates/footer.jspf"></jsp:include>

        
        <!--Scripts-->
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
        <script src="js/jquery.ptTimeSelect.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="http://leafletjs.com/dist/leaflet.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>  
        <script type="text/javascript" src="js/validate_delincuencia.js"></script>
        <script type="text/javascript" src="js/app_desechos.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <script type="text/javascript" src="js/form.js"></script>
    </body>
</html>
