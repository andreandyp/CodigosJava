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
    $("img").click(function(){
        $.post("ProcesoElectoral",{partido:$(this).attr("alt"),candidato:"hue"},function(){
            
        });
    });
});