
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<link rel="stylesheet" href="css/style_header.css" />
<link rel="stylesheet" href="../sites/css/style_header.css" />
<div class="navbar1">




    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a href="index.html" data-section="html" class="brand scroller">PERUANO ACTIVO</a>
                <div class="nav-collapse collapse">
                    <ul class="nav pull-right">
                        <li>
                            <a  href="<%=request.getContextPath()%>/sites/indexde.jsp">Inicio</a>
                        </li>

                        <li>
                            <a href="#sobrenos"  data-toggle="modal" >Sobre Nosotros</a>
                        </li>
                        <li>
                            <a href="#contactenos"  data-toggle="modal" >Contactenos</a>
                        </li>


                    </ul>
                </div>
            </div>
        </div>

        <%

            String usuario = (String) session.getAttribute("session_usuario");
            if (usuario != null) {
        %>
        <div class="content_user">
            <p style="color: #fff"> Bienvenido : ${session_usuario}
            </p>
            <p>
                <a href="${pageContext.request.contextPath}/SCerrarsesion_users">Cerrar Sesion</a>
            </p>
        </div>
        <% }%>






    </div>

</div>
<div class="navbar2">

    <div class="navbar navbar-inverse navbar-fixed-top ">
        <div class="navbar-inner">
            <!--<a href="#" class="brand">Peruano Activo</a>-->

            <ul class="nav" >
                <li class="active">
                    <a href="#">Mapa de Crimenes</a>
                </li>
                <li>
                    <a href="#estadisticas"  data-toggle="modal">Mapa de Agujeros Negros</a>
                </li>                     

                <li>
                    <a href="#sobrenos"  data-toggle="modal" >Mapa de Basura</a>
                </li>



            </ul>
            <%

                String usuario2 = (String) session.getAttribute("session_usuario");
                if (usuario2 == null) {
            %>
            <a  class="btn btn-primary pull-right"  href = '../users/login_users.jsp'>Login</a>
            <a  class="btn btn-primary pull-right" href = '../users/newuser.jsp'>Registrarse</a> 
            <% } else {%>

            <a  class="btn btn-danger pull-right" href = '../reg/registrar21.jsp'>Registrar Punto de Incidencias </a>
            <%     }

            %>



        </div>

    </div>

</div>

<!--Sobre Nosotros-->

<div id="sobrenos" class="modal container hide fade" tabindex="-1">
    <div class="modal-header">   
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <p>
            Somos una grupos de Estudiantes egresados que venimos  implementando una  
            aplicacion que pueda haciedno uso de maps , para mejorar hacer participe a todo ciudadanno en el Peru, mediante su
            registro de puntos criticos que por juicio son donde los gobiernos distritales, provinciales , regionales, y nacional deberis de intervenir
        </p>       
    </div>
    <div class="modal-body" >
    </div>
</div>


<!--End Sobre Nosotros-->


<!--contactenos-->

<div id="contactenos" class="modal container hide fade" tabindex="-1">
    <div class="modal-header">   
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

    </div>
    <div class="modal-body" >
    </div>
</div>
  <!--End Sobre contactenos-->
