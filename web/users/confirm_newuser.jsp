<%-- 
    Document   : confirm_newuser
    Created on : May 16, 2013, 4:57:51 PM
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
        <title>JSP Page</title>
              
    </head>

    <style>
        .content2 {
            width: 400px;
            margin: 0 auto;
            margin-top: 150px;            
        }

    </style>
    <body>
        <jsp:include page="../sites/templates/header.jsp"></jsp:include> 
        <div class="content content2">
            <div class="row">
                <div class="well" >
                    <h3><p>Su registro a sido procesado correctamente, su password ha sido enviado a su correo, haora pude iniciar el registor 
                        de incidentes en el territorio peruano</p></h3>

                    <input type="button" class="btn btn-success" onClick="location.href = 'login_users.jsp'" value="Iniciar a Registrar Incidentes" style="width:200px; height: 40px;" value='click here'>
                </div>
            </div>
        </div>

    </body>
    <script src="http://code.jquery.com/jquery-1.8.0.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
</html>
