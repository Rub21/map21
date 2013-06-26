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
        <title>Registro</title>
        <jsp:include page="templates/style.jspf"></jsp:include>
        </head>
        <body>

        <jsp:include page="templates/header.jsp"></jsp:include>
            <!-- Jumbotron -->
            <div class="jumbotron">  
                <h1> Mejorando El Perú!</h1>
                <p class="lead">Registre cualquier incidente con respecto a la Delincuencia, Agujeron Negros, y Puntos de Basura, mediente este metodo podremos hacer escuchar nuestra vos ante nuestro goviernos locales , regionales y central.</p>
                <a class="btn btn-large btn-success" href="../sites/index.jsp">Visualizar Puntos Registrados</a>
            </div>

            <hr>

        <jsp:include page="templates/footer.jspf"></jsp:include>

    </body>
</html>
