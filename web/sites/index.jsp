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

        <jsp:include page="templates/footer.jspf"></jsp:include>
        <script src='http://api.tiles.mapbox.com/mapbox.js/v0.6.6/mapbox.js'></script>
        <script src="http://code.jquery.com/jquery-1.8.0.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
        <script src="http://underscorejs.org/underscore-min.js"></script>
        <script src="js/get_data.js"></script>
        <script src="js/menu.js"></script>
        <script src="js/app.js"></script>
    </body>
</html>
