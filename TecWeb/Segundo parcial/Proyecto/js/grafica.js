var ecuaciones = [{
            fn: "x^2",
            color: "pink",
            skipTip: true
        },{
            fn: "x",
            color: "green",
            skipTip: true 
        },{
            fn: "1",
            color: "blue",
            skipTip: true 
        },{
            fn: "1x^2+1x+1",
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
    var terminoC = true;
    var terminoL = true;
    var terminoI = true;
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