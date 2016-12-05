$(function(){
    var paso = false;
    $("#enviar").click(function(){
        if(!paso){
            if($("#claveElec").length)
            var claveElec = $("#claveElec").val();
            if()
            $.post("Sesion",{clave:claveElec},function(respuesta){
                if(respuesta == true){
                    alert("A huevo");
                }else{
                    alert("Valió verga");
                }
            });
        }else{
            
        }
    });
});
