<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">

        <title>Nuevo Usuario</title>
    </head>
    <style>
        .content2 {
            width: 300px;
            margin: 0 auto;
            margin-top: 105px;            
        }


        html, body {
            background-color: #333;
        }
        body {
            padding-top: 40px; 
        }

    </style>


    <body>
        <jsp:include page="../sites/templates/header.jsp"></jsp:include>
            <div class="content content2">
                <div class="row">               

                    <form  id="formulario" class="well"  method="post"  action="<%=request.getContextPath()%>/SNewUser">
                    <div class="row">
                        <div class="span6">Correo Electronico</div>
                        <div class="span6"><input type="text" name="correo" value=""  id="correo" style="width: 250px" >
                        </div>
                    </div>
                    <div class="row">
                        <div class="span6">Nombre y Apellidos</div>
                        <div class="span6"><input type="text" name="nombre" value=""  id="nombre" style="width: 250px">
                        </div>
                    </div>
                    <div class="row">
                        <div class="span6">Usuario</div>
                        <div class="span6"> <input type="text" name="usuario" value=""  id="usuario"  style="width: 250px">
                        </div>
                    </div>

                    <div class="row">
                        <div class="span6"></div>
                        <div class="span6">
                            <img src="${pageContext.request.contextPath}/SCaptchas"/>

                        </div>
                        <div class="span6">Inserte en codigo de la imagen superior<br>
                            <span style="color: #FF001C; font-size: 10px"> ${error_captcha}</span>
                        </div>

                        <div class="span6">

                            <input type="text" name="captcha"  id="captcha" style="width: 250px">
                        </div>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary" id="button">  Registrar</button>
                    <button type="reset" class="btn">Cancelar</button>
                </form>
            </div>
        </div>
                         <jsp:include page="../sites/templates/footer.jspf"></jsp:include>
    </body>
    <script src="http://code.jquery.com/jquery-1.8.0.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>

</html>
