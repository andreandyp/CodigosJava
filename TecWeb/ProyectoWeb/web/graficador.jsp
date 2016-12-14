<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Graficador Jendre</title>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/d3.min.js"></script>
        <script src="js/function-plot.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-filestyle.min.js"></script>
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
            var aIni = <%out.println(a);%>;
            var bIni = <%out.println(b);%>;
            var cIni = <%out.println(c);%>;
        </script>
        <script src="js/grafica.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/graficador.css"/>
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
                            <li><a href="https://www.instagram.com/jess_roary/">Jessica Areli</a></li>
                            <li><a href="https://www.instagram.com/andiiburciaga/">Rodrigo Andrés</a></li>
                        </ul>
                        <h6>Frameworks usados</h6>
                        <ul>
                            <li><a href="https://getbootstrap.com/">Twitter Bootstrap</a></li>
                            <li><a href="https://jquery.com/">jQuery</a></li>
                            <li><a href="https://d3js.org/">D3 (3.5.17)</a></li>
                            <li><a href="https://maurizzzio.github.io/function-plot/">Function Plot</a></li>
                            <li><a href="https://markusslima.github.io/bootstrap-filestyle/">Bootstrap FileStyle</a></li>
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
        
        <div id="operaciones" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Abrir/Guardar</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-4 center-block">
                                <button type="button" id="enviar" class="btn btn-default center-block">Guardar</button>
                            </div>
                            <div class="col-sm-4 center-block">
                                <button type="button" id="descargar" class="btn btn-default center-block">Guardar (JSON)</button>
                            </div>
                            <div class="col-sm-4 center-block">
                                <input type="file" class="filestyle" data-input="false" data-badge="false" data-buttonText="Abrir (JSON)" data-buttonName="btn-default" data-icon="false" id="abrir"/>
                            </div>
                        </div>
                        <h4 id="estado"></h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="limpiar" data-dismiss="modal">Cerrar</button>
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
                    <h4 id="ax2-e"><input type="checkbox" id="ax2-l"/>Mostrar ax<sup>2</sup></h4>
                    <input type="number" class="form-control" id="ax2-v" size="2" maxlength="2"/>
                    <input type="range" id="ax2-r" min="-50" max="50" step="2"/>
                </div>
                <div class="col-sm-3">
                    <h4 id="bx-e"><input type="checkbox" id="bx-l"/>Mostrar bx</h4>
                    <input type="number" class="form-control" id="bx-v" size="2" maxlength="2"/>
                    <input type="range" id="bx-r" min="-50" max="50" step="2"/>
                </div>
                <div class="col-sm-3">
                    <h4 id="c-e"><input type="checkbox" id="c-l"/>Mostrar c</h4>
                    <input type="number" class="form-control" id="c-v" size="2" maxlength="2"/>
                    <input type="range" id="c-r" min="-50" max="50" step="2"/>
                </div>
                <div class="col-sm-3">
                    <div class="row center-block">
                        <div class="col-sm-12" id="ecuacion"></div>
                    </div>
                    <div class="row center-block">
                        <div class="col-sm-6">
                            <button type="button" class="btn btn-success center-block" data-toggle="modal" data-target="#operaciones">Abrir/Guardar</button>
                            <a id="desjson" download="grafica.json"></a>
                            
                        </div>
                        <div class="col-sm-6">
                            <button type="button" class="btn btn-info center-block" data-toggle="modal" data-target="#acercaDe">Acerca de</button>
                        </div>
                    </div>
                    <div class="row center-block">
                        <div class="col-sm-6">
                            <button type="button" id="ceros" class="btn btn-warning center-block">Poner a ceros</button>
                        </div>
                        <div class="col-sm-6">
                            <button type="button" id="ini" class="btn btn-danger center-block">Valores iniciales</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>