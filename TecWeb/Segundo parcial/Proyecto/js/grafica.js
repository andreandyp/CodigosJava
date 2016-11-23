var ecuaciones = [{
            fn: "x^2",
            color: "transparent",
            skipTip: true
        },{
            fn: "x",
            color: "transparent",
            skipTip: true 
        },{
            fn: "1",
            color: "transparent",
            skipTip: true 
        },{
            fn: "x^2+x+1",
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
    }
$(function () {
    var terminoC = false;
    var terminoL = false;
    var terminoI = false;
	var largo = $("#grafica").width();
    var ancho = $("#grafica").height();
	graficador(largo,ancho);

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
    $("#bx-r").mousemove(function () {
        var valor = $("#bx-r").val()
        if(!valor){
            valor = 0;
        }
        ecuaciones[0].fn = valor + "x";
        ecuaciones[3].fn = ecuaciones[0].fn+"+"+ecuaciones[1].fn+"+"+ecuaciones[2].fn;
        app.data = ecuaciones;
        $("#bx-v").val(valor);
        functionPlot(app);
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
});
function graficador(larg,anch) {
    app.width = larg;
    app.height = anch;
	functionPlot(app);
}