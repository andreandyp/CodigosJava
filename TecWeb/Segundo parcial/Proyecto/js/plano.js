$(function(){
    var lineasV = [],lineasH = [];
    var largo = $(window).width();
    var ancho = $(window).height();
    var plano = new fabric.Canvas("plano");
    var ejeX = new fabric.Line([0,ancho/2,largo,ancho/2],{
        stroke: 'black',
        strokeWidth: 3,
        selectable: false
    });

    var ejeY = new fabric.Line([largo/2,0,largo/2,ancho],{
        stroke: 'black',
        strokeWidth: 3,
        selectable: false
    });
    var curva = new fabric.Path('M 65 0 Q 100, 100, 200, 0',{
        stroke: 'blue',
        fill: '',
        strokeWidth: 3,
        selectable: false
    });
    curva.path[0][1] = 0;
    curva.path[0][2] = ancho/2;

    curva.path[1][1] = 250;
    curva.path[1][2] = 250;

    curva.path[1][3] = largo;
    curva.path[1][4] = 250;

    for(var i = 0; i<=20; i++){
        lineasH[i] = new fabric.Line([(largo/20)*(i+1),(ancho/2)-10,(largo/20)*(i+1),(ancho/2)+10],{
            stroke: 'black',
            strokeWidth: 3,
            selectable: false
        });
        plano.add(lineasH[i]);
    }

    for(var i = 0; i<=10; i++){
        lineasV[i] = new fabric.Line([(largo/2)-10,(ancho/10)*(i+1),(largo/2)+10,(ancho/10)*(i+1)],{
            stroke: 'black',
            strokeWidth: 3,
            selectable: false
        });
        plano.add(lineasV[i]);
    }

    plano.add(ejeX);
    plano.add(ejeY);
    plano.add(curva);
    //graficar();

    $(window).resize(function(e){
        largo = $(window).width();
        ancho = $(window).height();
        dibujarEjes(ejeX,ejeY,largo,ancho,lineasH,lineasV);
    });
    function dibujarEjes(eX,eY,lar,anc,linH,linV){
        eX.set({'x1':0,'y1':anc/2,'x2':lar,'y2':anc/2});
        eY.set({'x1':lar/2,'y1':0,'x2':lar/2,'y2':anc});
        for(var i = 0; i<=20; i++)
            linH[i].set({'x1':(lar/20)*(i+1),'y1':(anc/2)-10,'x2':(lar/20)*(i+1),'y2':(anc/2)+10});
        for(var i = 0; i<=10; i++)
            linV[i].set({'x1':(lar/2)-10,'y1':(anc/10)*(i+1),'x2':(lar/2)+10,'y2':(anc/10)*(i+1)});
        plano.renderAll();
    }

    $("#graficar").click(function(){
        graficar();
    });
});