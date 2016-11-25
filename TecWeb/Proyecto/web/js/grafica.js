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
    valoresPredet();

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
            ecuaciones[0].color = "pink";
            app.data = ecuaciones;
            functionPlot(app);
            terminoC = true;
        }
    });
    $("#ax2-v").keyup(function () {
        var valor = $("#ax2-v").val()
        if(!valor){
            valor = 0;
        }
        ecuaciones[0].fn = valor + "x^2";
        ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
        app.data = ecuaciones;
        $("#ax2-r").val(valor);
        functionPlot(app);
    });
    $("#ax2-r").mousedown(function () {
        $("#ax2-r").mousemove(function () {
            var valor = $("#ax2-r").val()
            if(!valor){
                valor = 0;
            }
            ecuaciones[0].fn = valor + "x^2";
            ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
            app.data = ecuaciones;
            $("#ax2-v").val(valor);
            functionPlot(app);
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
        var valor = $("#bx-v").val()
        if(!valor){
            valor = 0;
        }
        ecuaciones[1].fn = valor + "x";
        ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
        app.data = ecuaciones;
        $("#bx-r").val(valor);
        functionPlot(app);
    });
    $("#bx-r").mousedown(function () {
        $("#bx-r").mousemove(function () {
            var valor = $("#bx-r").val()
            if(!valor){
                valor = 0;
            }
            ecuaciones[1].fn = valor + "x";
            ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
            app.data = ecuaciones;
            $("#bx-v").val(valor);
            functionPlot(app);
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
        var valor = $("#c-v").val()
        if(!valor){
            valor = 0;
        }
        ecuaciones[2].fn = valor;
        ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
        app.data = ecuaciones;
        $("#c-r").val(valor);
        functionPlot(app);
    });
    $("#c-r").mousedown(function () {
        $("#c-r").mousemove(function () {
            var valor = $("#c-r").val()
            if(!valor){
                valor = 0;
            }
            ecuaciones[2].fn = valor;
            ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
            app.data = ecuaciones;
            $("#c-v").val(valor);
            functionPlot(app);
        });
    });
    $("#ini").click(function () {
        valoresPredet();
    });
    $("#ceros").click(function(){
        ceros();
    });
    $("#descargar").click(function(){
        descargarJSON();
    });
});
function graficador(larg,anch) {
    app.width = larg;
    app.height = anch;
	functionPlot(app);
}
function valoresPredet() {
    $("#ax2-v").val(a);
    $("#ax2-r").val(a);
    $("#bx-v").val(b);
    $("#bx-r").val(b);
    $("#c-v").val(c);
    $("#c-r").val(c);
    ecuaciones[0].fn = a+"x^2";
    ecuaciones[1].fn = b+"x";
    ecuaciones[2].fn = c.toString();
    ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
    app.data = ecuaciones;
    functionPlot(app);
}
function ceros() {
    $("#ax2-v").val(0);
    $("#ax2-r").val(0);
    $("#bx-v").val(0);
    $("#bx-r").val(0);
    $("#c-v").val(0);
    $("#c-r").val(0);
    ecuaciones[0].fn = "0";
    ecuaciones[1].fn = "0";
    ecuaciones[2].fn = "0";
    ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
    app.data = ecuaciones;
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
    //Porque jQuery omite las etiquetas a al usar click();
    //Así que uso VanillaJS
    document.getElementById("desjson").click();
}