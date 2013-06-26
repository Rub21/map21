<%-- 
    Document   : login
    Created on : Apr 7, 2013, 10:51:51 AM
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">


        <style type="text/css">

            html, body {
                background-color: #333;
            }
            body {
                padding-top: 40px; 
            }
            .container2 {
                width: 300px;
                margin-top: 105px;
            }

            /* The white background content wrapper */
            .container > .content {
                background-color: #fff;
                padding: 20px;
                margin: 0 -20px; 
                -webkit-border-radius: 10px 10px 10px 10px;
                -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
            }

            .login-form {
                margin-left: 65px;
            }

            legend {
                margin-right: -50px;
                font-weight: bold;
                color: #404040;
            }

        </style>
    </head>
    <body>

        <jsp:include page="../sites/templates/header.jsp"></jsp:include>
            <div class="container container2">
                <div class="content">
                    <div class="row">

                        <div class="login-form">
                            <h5 name="usuario">Usuario : ${usuario_first}</h5>
                        <h6>Solo por primera  ves tiene la opcion de canbiar su password:</h6>
                        <form name="form" id="form-r" method="post"  action="<%=request.getContextPath()%>/SLogin_changepassword">
                            <fieldset>
                                <!--<div class="clearfix">
                                    <input name="oldpassword" type="password" placeholder="Password Anterior">
                                </div>                  -->            
                                <div class="clearfix">

                                    <input name="newpassword" type="password" placeholder="Password Nuevo">
                                </div>
                                <div class="clearfix">
                                    <input name="confirmpassword" type="password" placeholder="Confirmar Password">
                                </div>
                                <button class="btn btn-primary" type="submit">Confirmar</button>
                                <p style="color: red; font-size: 10px">${error_cofirn_passsword}</p>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="http://code.jquery.com/jquery-1.8.0.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
</html>
