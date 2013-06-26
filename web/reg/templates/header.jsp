
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="content_user">
    <p style="color: #000"> Bienvenido : ${session_usuario}</p>

    <p><a href="${pageContext.request.contextPath}/SCerrarsesion_users">Cerrar Sesion</a></p>
</div>

<div class="masthead">
    <a href="#" onClick="location.href = 'registrar21.jsp'"> <h3 class="muted">  Registra un Punto de Incidencia </h3></a>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav">
                    <li class="delincuencia"><a  href="#" onClick="location.href = 'registrar_delincuencia.jsp'">Incidencia Delincuenciales</a></li>
                    <li  class="agujeros"><a href="#" onClick="location.href = 'registrar_agujero.jsp'">Agujeros Negros</a></li>
                    <li  class="desechos" ><a href="#" onClick="location.href = 'registrar_desechos.jsp'" >Puntos de Basura</a></li>

                </ul>
            </div>
        </div>
    </div><!-- /.navbar -->
</div>

