var ecuaciones = [{
            fn: "0",
            color: "transparent",
            skipTip: true
        },{
            fn: "0",
            color: "transparent",
            skipTip: true 
        },{
            fn: "0",
            color: "transparent",
            skipTip: true 
        },{
            fn: "0",
            color: "red",
            skipTip: true
        }];
var app = {
        target: "#grafica",
        disableZoom: true,
        width: 0,
        height: 0,
        data: ecuaciones,
        xAxis:{
            domain: [-10,10],
            label: "Eje X"
        },
        yAxis:{
            label: "Eje Y"
        }
    };
$(function () {
    var terminoC = false;
    var terminoL = false;
    var terminoI = false;
    var largo = $("#grafica").width();
    var ancho = $("#grafica").height();
    graficador(largo,ancho);
    establecer(aIni,bIni,cIni);

    $(window).resize(function(e){
        largo = $("#grafica").width();
    	ancho = $("#grafica").height();
        graficador(largo,ancho);
    });

    $("#ax2-l").change(function () {
        if(terminoC){
            ecuaciones[0].color = "transparent";
            app.data = ecuaciones;
            functionPlot(app);
            terminoC = false;
        }
        else{
            ecuaciones[0].color = "#f0f";
            app.data = ecuaciones;
            functionPlot(app);
            terminoC = true;
        }
    });
    $("#ax2-v").keyup(function () {
        var valor = $("#ax2-v").val();
        if(!valor)
            valor = 0;
        $("#ax2-r").val(valor);
        graficar(valor,null,null);
    });
    $("#ax2-r").mousedown(function () {
        $("#ax2-r").mousemove(function () {
            var valor = $("#ax2-r").val();
            if(!valor)
                valor = 0;
            $("#ax2-v").val(valor);
            graficar(valor,null,null);
        });
        
    });
    $("#bx-l").change(function () {
        if(terminoL){
            ecuaciones[1].color = "transparent";
            app.data = ecuaciones;
            functionPlot(app);
            terminoL = false;
        }
        else{
            ecuaciones[1].color = "green";
            app.data = ecuaciones;
            functionPlot(app);
            terminoL = true;
        }
    });
    $("#bx-v").keyup(function () {
        var valor = $("#bx-v").val();
        if(!valor)
            valor = 0;
        $("#bx-r").val(valor);
        graficar(null,valor,null);
    });
    $("#bx-r").mousedown(function () {
        $("#bx-r").mousemove(function () {
            var valor = $("#bx-r").val();
            if(!valor)
                valor = 0;
            $("#bx-v").val(valor);
            graficar(null,valor,null);
        });
    });
    $("#c-l").change(function(){
        if(terminoI){
            ecuaciones[2].color = "transparent";
            app.data = ecuaciones;
            functionPlot(app);
            terminoI = false;
        }
        else{
            ecuaciones[2].color = "blue";
            app.data = ecuaciones;
            functionPlot(app);
            terminoI = true;
        }
    });
    $("#c-v").keyup(function () {
        var valor = $("#c-v").val();
        if(!valor)
            valor = 0;
        $("#c-r").val(valor);
        graficar(null,null,valor);
    });
    $("#c-r").mousedown(function () {
        $("#c-r").mousemove(function () {
            var valor = $("#c-r").val();
            if(!valor)
                valor = 0;
            $("#c-v").val(valor);
            graficar(null,null,valor);
        });
    });
    $("#abrir").change(function(){
        var archivo = document.getElementById("abrir").files[0];
        if (archivo) {
            var reader = new FileReader();
            reader.readAsText(archivo, "UTF-8");
            reader.onload = function (evt) {
                var coeficientes = JSON.parse(evt.target.result);
                establecer(coeficientes.a,coeficientes.b,coeficientes.c);
                $("#estado").html("Valores del JSON establecidos");
            };
            reader.onerror = function (evt) {
                alert("Error en el archivo");
            };
        }
    });
    $("#enviar").click(function(){
        alert("que pedo?");
        var a = $("#ax2-v").val();
        var b = $("#bx-v").val();
        var c = $("#c-v").val();
        $.get("Servlet",{a:a,b:b,c:c},function(respuesta){
            alert("Ya debió regresar");
            $("#estado").html(respuesta);
        });
    });
    $("#ini").click(function () {
        establecer(aIni,bIni,cIni);
    });
    $("#ceros").click(function(){
        establecer(0,0,0);
    });
    $("#descargar").click(function(){
        descargarJSON();
        $("#estado").html("Descargando JSON");
    });
    $("#limpiar").click(function(){
        $("#estado").empty();
    });
});
function graficador(larg,anch) {
    app.width = larg;
    app.height = anch;
	functionPlot(app);
}
function descargarJSON(){
    var a = $("#ax2-v").val();
    var b = $("#bx-v").val();
    var c = $("#c-v").val();
    var objetoJSON = {
        a: a,
        b: b,
        c: c
    };
    var json = "text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(objetoJSON));
    $("#desjson").attr("href","data:"+json);
    //Uso VanillaJS porque el click() de jQuery no funciona para <a>
    document.getElementById("desjson").click();
}
function establecer(a,b,c){
    $("#ax2-v").val(a);
    $("#ax2-r").val(a);
    $("#bx-v").val(b);
    $("#bx-r").val(b);
    $("#c-v").val(c);
    $("#c-r").val(c);
    graficar(a,b,c);
}
function graficar(valA,valB,valC) {
    ecuaciones[0].fn = (valA==null?ecuaciones[0].fn:valA+"x^2");
    ecuaciones[1].fn = (valB==null?ecuaciones[1].fn:valB+"x");
    ecuaciones[2].fn = (valC==null?ecuaciones[2].fn:valC.toString());
    ecuaciones[3].fn = ecuaciones[0].fn+(ecuaciones[1].fn.indexOf("-")>-1?"":"+")+ecuaciones[1].fn+(ecuaciones[2].fn.indexOf("-")>-1?"":"+")+ecuaciones[2].fn;
    app.data = ecuaciones;
    functionPlot(app);
    $("#ecuacion").empty();
    var ecu = "<h4>"+ecuaciones[3].fn+" = 0</h4>";
    ecu = ecu.replace("x^2","x<sup>2</sup>");
    $("#ecuacion").append(ecu);
}