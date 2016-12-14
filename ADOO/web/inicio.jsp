<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("Usuario") != null){
        response.sendRedirect("http://localhost:8080/ADOO/procesoElectoral.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>INE ADOO</title>
        <script src="js/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/funciones.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <img src="imagenes/ine.png" alt="Logo INE" />
            </div>
            <p id="info"></p>
            <div class="row" id="inicioClave">
                <div class="form-group">
                    <label for="claveElec" class="control-label col-sm-2">Clave de elector</label>
                    <div class="col-sm-10">
                        <input type="text" id="claveElec" class="form-control" maxlength="18"/>
                    </div>
                </div>
            </div>
            <div class="row" id="inicioHuella">
                <p>Inserta tu huela digital</p>
                <img src="imagenes/huella.png" alt="Huella digital"/>
            </div>
            <button type="submit" class="btn btn-primary" id="enviar">Iniciar sesión</button>
        </div>
        <footer style="position: absolute; bottom: 0px;">
            <div class="row">
                <div class="col-sm-3">
                    <p>Logo del gobierno</p>
                </div>
                <div class="col-sm-3">
                    <p>Logo INE</p>
                </div>
                <div class="col-sm-3">
                    <p>Logo IPN</p>
                </div>
                <div class="col-sm-3">
                    <p>Aquí iría nuestro logo chido</p>
                </div>
            </div>
        </footer>
    </body>
</html>