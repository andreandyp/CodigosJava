$(function () {
    $("#inicioHuella").hide();
    var validado = false;
    var claveElec;
    $("#enviar").click(function () {
        if (validado === false) {
            claveElec = $("#claveElec").val();
            var er = /^[0-9A-Z]*$/;
            if (claveElec.length < 18 || er.test(claveElec) === false) {
                $("#info").html("La clave de elector debe de contener 18 caracteres, letras mayúsculas y numeros");
            } else {
                $("#info").html("");
                $.post("Autentificacion", {clave: claveElec,valido:validado}, function (respuesta) {
                    if (respuesta == "true") {
                        $("#inicioClave").hide();
                        $("#inicioHuella").show();
                        $("#info").html("");
                        validado = true;
                    } else {
                        $("#info").html("No existe esa clave de elector en el sistema");
                    }
                });
            }
        } else {
            $.post("Autentificacion", {clave: claveElec,valido:validado}, function (respuesta) {
                    if (respuesta == "true") {
                        window.location.href = "http://localhost:8080/ADOO/procesoElectoral.jsp";
                    } else {
                        alert("jue");
                    }
                });
        }
        
    });
    $("#votar").click(function(){
        $("#acciones").hide();
        $("#sufragios").show();
    });
    $(".opcion").click(function(){
        $.post("ProcesoElectoral",{partido:$(this).attr("alt"),candidato:$(this).parent().children("h5").html()},function(){
            $.get("ProcesoElectoral",{hola:"hola"},function(datos){
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
});