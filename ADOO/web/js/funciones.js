$(function () {
    $("#inicioHuella").hide();
    var validado = false;
    $("#enviar").click(function () {
        if (!validado) {
            var claveElec = $("#claveElec").val();
            if (claveElec.length < 18) {
                $("#info").html("La clave de elector debe de contener 18 caracteres");
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
            //Verificar huella
            window.location.href = ""
        }
        
    });
});
