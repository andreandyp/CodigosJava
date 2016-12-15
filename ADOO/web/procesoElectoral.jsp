
<%@page import="ine.Votante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("Usuario") == null){
        response.sendRedirect("http://localhost:8080/ADOO/index.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proceso electoral</title>
        <script src="js/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/funciones.js"></script>
        <style type="text/css">
            h1,h2,h3,h4,h5,p{
                text-align: center;
            }
            img{
                float: left;
            }
        </style>
        <script>
            $(function(){
                var votos = 0;
                $("#sufragios").hide();
                var voEmitido = <%out.println( ((Votante)sesion.getAttribute("Usuario")).isVotoEmitido() );%>;
                if(voEmitido == true){
                    $("#votar").prop("disabled","true");
                }
                $("#votar").click(function(){
                    $.get("ProcesoElectoral",{hola:"hola"},function(datos){
                        if (datos == "true" || votos > 2){
                            alert("Ya terminaste de votar");
                            <%((Votante)sesion.getAttribute("Usuario")).setNumVotos(0);%>
                            location.reload();
                        }
                        ++votos;
                        var json = $.parseJSON(datos);
                        $("#estado").html(json.Eleccion);
                        $("img[alt='PRI']").parent().children("h5").html(json.PRI);
                        $("img[alt='PAN']").parent().children("h5").html(json.PAN);
                        $("img[alt='PRD']").parent().children("h5").html(json.PRD);
                        $("img[alt='MORENA']").parent().children("h5").html(json.MORENA);
                        $("img[alt='MC']").parent().children("h5").html(json.MC);
                        $("img[alt='PT']").parent().children("h5").html(json.PT);
                        $("img[alt='PVEM']").parent().children("h5").html(json.PVEM);
                        $("img[alt='PANAL']").parent().children("h5").html(json.PANAL);
                        $("img[alt='PH']").parent().children("h5").html(json.PH);
                        $("img[alt='PES']").parent().children("h5").html(json.PES);
                    });
                });

            });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row center-block" id="acciones">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="jumbotron">
                            <h1>Bienvenido<br> <%out.println(((Votante) sesion.getAttribute("Usuario")).getNombre());%></h1>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-lg btn-success center-block" id="votar">Votar</button>
                    <h4>Continua o empieza el proceso electoral</h4>
                </div>
                <div class="col-sm-4">
                    <button class="btn btn-lg btn-default center-block">Ver tendencias</button>
                    <h4>Ve las tendencias al momento de los votos emitidos</h4>
                </div>
                <div class="sol-sm-4">
                    <h4>Casilla asignada: <br><%out.println(((Votante) sesion.getAttribute("Usuario")).getCasilla());%></h4>
                    <h4>Número de votos faltantes: <br><%out.println( 3- ((Votante)sesion.getAttribute("Usuario")).getNumVotos() );%></h4>
                </div>
            </div>
            <div class="row" id="sufragios">
                <br>
                <h2>Da click en el partido que quieras votar. Boleta actual:</h2>
                <h2 id="estado"></h2>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <img src="imagenes/PRM.png" alt="PRI" class="center-block opcion"/>
                        <h4>Partido Revolucionario Institucional</h4>
                        <h5></h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="imagenes/PAN.png" alt="PAN" class="center-block opcion"/>
                        <h4>Partido Acción Nacional</h4>
                        <h5></h5>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <img src="imagenes/PRD.png" alt="PRD" class="center-block opcion"/>
                        <h4>Partido de la Revolución Democrática</h4>
                        <h5></h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="imagenes/MORENA.png" alt="MORENA" class="center-block opcion"/>
                        <h4>Movimiento de Regeneración Nacional</h4>
                        <h5></h5>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <img src="imagenes/MC.png" alt="MC" class="center-block opcion"/>
                        <h4>Movimiento Ciudadano</h4>
                        <h5></h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="imagenes/PT.png" alt="PT" class="center-block opcion"/>
                        <h4>Partido del Trabajo</h4>
                        <h5></h5>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <img src="imagenes/PVEM.png" alt="PVEM" class="center-block opcion"/>
                        <h4>Partido Verde Ecologista de México</h4>
                        <h5></h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="imagenes/PANAL.png" alt="PANAL" class="center-block opcion"/>
                        <h4>Partido Nueva Alianza</h4>
                        <h5></h5>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <img src="imagenes/PH.png" alt="PH" class="center-block opcion"/>
                        <h4>Partido Humanista</h4>
                        <h5></h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="imagenes/PES.png" alt="PES" class="center-block opcion"/>
                        <h4>Partido Encuentro Social</h4>
                        <h5></h5>
                    </div>
                </div>
                <h1>¡Recuerda!</h1>
                <h1>El voto es libre, secreto y directo.</h1>
            </div>
        </div>
    </body>
</html>
