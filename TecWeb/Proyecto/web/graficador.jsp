<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inserta el titulo aquí :P</title>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/d3.min.js"></script>
        <script src="js/function-plot.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <%
            String a = "",b = "",c = "";
            if(request.getParameter("a") != null && !request.getParameter("a").equals(""))
                a = request.getParameter("a");
            else
                a = "0";
            if(request.getParameter("b") != null && !request.getParameter("b").equals(""))
                b = request.getParameter("b");
            else
                b = "0";
            if(request.getParameter("c") != null && !request.getParameter("c").equals(""))
                c = request.getParameter("c");
            else
                c = "0"; 
        %>
        <script>
            var a = <%out.println(a);%>;
            var b = <%out.println(b);%>;
            var c = <%out.println(c);%>;
        </script>
        <script src="js/grafica.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css"></link>
        <style type="text/css">
            #pantalla{
                background-color: #FFFFE0;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 80%;
            }
            #desjson{
                display: none;
            }
            h4{
                text-align: center;
            }
            #grafica{
                position: fixed;
                top: 0;
                left: 10%;
                width: 80%;
                height: 80%;   
            }
            #controles{
                position: fixed;
                top: 80%;
                left: 0;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <div id="acercaDe" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Graficador Jendre</h4>
                    </div>
                    <div class="modal-body">
                        <h6>Desarrolladores</h6>
                        <ul>
                            <li><a href="https://andreandyp.github.io">André Michel</a></li>
                            <li><a href="">Jessica Areli</a></li>
                            <li><a href="https://www.instagram.com/andiiburciaga/">Rodrigo Andrés</a></li>
                        </ul>
                        <h6>Frameworks usados</h6>
                        <ul>
                            <li><a href="https://getbootstrap.com/">Twitter Bootstrap</a></li>
                            <li><a href="https://jquery.com/">jQuery</a></li>
                            <li><a href="https://d3js.org/">D3 (3.5.17)</a></li>
                            <li><a href="https://maurizzzio.github.io/function-plot/">Function Plot</a></li>
                        </ul>
                        <p>Esta aplicación es un proyecto para la materia de Tecnologías para la WEB.
                        El proyecto consta de un mini-sistema de alumnos y maestros que incluye un graficador
                        de ecuaciones de 2° grado. Esta basado en un graficador de la Universida de Colorado</p>
                        <a href="https://phet.colorado.edu/sims/equation-grapher/equation-grapher_en.html">Graficador original (Desarrollado en flash)</a>
                        <p>Versión Java</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="pantalla">
            <div id="grafica"></div>
        </div>
        <div id="controles">
            <div class="row">
                <div class="col-sm-3" >
                    <h4>ax<sup>2</sup></h4>
                    <input type="checkbox" id="ax2-l"/>Mostrar<br>
                    Valor: <input type="number" id="ax2-v" size="2" maxlength="2"><br>
                    <input type="range" id="ax2-r" min="-50" max="50" step="2"><br>
                </div>
                <div class="col-sm-3">
                    <h4>bx</h4>
                    <input type="checkbox" id="bx-l"/>Mostrar<br>
                    Valor: <input type="number" id="bx-v" size="2" maxlength="2"><br>
                    <input type="range" id="bx-r" min="-50" max="50" step="2"><br>
                </div>
                <div class="col-sm-3">
                    <h4>c</h4>
                    <input type="checkbox" id="c-l"/>Mostrar<br>
                    Valor: <input type="number" id="c-v" size="2" maxlength="2"><br>
                    <input type="range" id="c-r" min="-50" max="50" step="2"><br>
                </div>
                <div class="col-sm-3">
                    <div class="row center-block">
                        <div class="col-sm-6">
                            <button type="button" id="ini" class="btn btn-danger center-block">Valores iniciales</button>
                        </div>
                        <div class="col-sm-6">
                            <button type="button" id="ceros" class="btn btn-warning center-block">Poner a ceros</button>
                        </div>
                    </div>
                    <div class="row center-block">
                        <div class="col-sm-6">
                            <button type="button" id="descargar" class="btn btn-success center-block">Guardar</button>
                            <a id="desjson" download="grafica.json"></a>
                        </div>
                        <div class="col-sm-6">
                            <button type="button" class="btn btn-info center-block" data-toggle="modal" data-target="#acercaDe">Acerca de</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>