document.addEventListener("DOMContentLoaded", function() {
	document.getElementById("contenedor").style.visibility = "hidden";
	document.getElementById("contenedor1").style.visibility = "hidden";
	var xhttp= new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
 			if(this.responseText == "null Error: null"){
				window.location = "login.html";
			}
			else{
				if(this.responseText.search("administrador")!=-1){
 					 document.getElementById("contenedor").style.visibility = "visible";
 					 document.getElementById("contenedor1").style.visibility = "visible";
 					 document.getElementById("contenedor2").style.visibility = "visible";
 					 //consultarUsuarios();
				}
				else {
					var usr= this.responseText.split(" ");
					window.location = usr[0]+".html";
				}
			}
		}
	}
	xhttp.open("GET", "Servlet_Get_User?t="+ Math.random(), true);
	xhttp.send();

})
 
  

function agregar() {
	document.getElementById("mostrar").innerHTML =
	"<form>"+
	"<table class='table '>"+
	"<tr>"+
	"<td>Nombre</td><td><input type=\"text\" placeholder=\"Nombre\" name='registro' class='form-control'><span name='validacion' class='alerta'></span></td>"+
	"</tr>"+
	"<tr>"+
	"<td>Nombre de Usuario</td><td><input type=\"text\" placeholder=\"Nombre de Usuario\" name='registro' class='form-control'><span name='validacion' class='alerta'></span></td>"+
	"</tr>"+
	"<tr>"+
	"<td>Correo</td><td><input type=\"text\" placeholder=\"Correo\" name='registro' class='form-control'><span name='validacion' class='alerta'></span></td>"+
	"</tr>"+
	"<tr>"+
	"<td>Password</td><td><input type=\"password\" placeholder=\"Password\" name='registro' class='form-control'><span name='validacion' class='alerta'></span></td>"+
	"</tr>"+
	"</table>"+
	"<span><small><b>Tipo de usuario</b></small></span><br>"+
	"<div class='container-fluid radio'>"+
	"<input type='radio' name='tipo' value='administrador'> Administrador<br>"+
	"<input type='radio' name='tipo' value='profesor' > Profesor<br>"+
	"<input type='radio' name='tipo' value='estudiante'  checked > Estudiante<br><br>"+
	"</div>"+
	"<button type=\"button\" onclick=\"enviar()\" class='btn btn-success btn-md'><span class='glyphicon glyphicon-plus'></span> Registrar</button> "+
	"<button type=\"button\" onclick=\"borrar('mostrar');consultarUsuarios()\" class='btn btn-warning btn-md'>Cerrar <b>&times;</b></button>"+
	"</form>"
    ;
	
	document.getElementById("tabla").innerHTML= "";
	
	
 
    
	
}

function borrar(id){
	document.getElementById(id).innerHTML = "";
}

function enviar() {
	var x= document.getElementsByName("registro");
	var y= document.getElementsByName("tipo");
	var conteo=0,tipo;
	for (var i = 0; i < x.length ; i++) {
		if (x[i].value == "" || x[i].value==null) {
			document.getElementsByName("validacion")[i].innerHTML = " *Campo Obligatorio";
			conteo++;
		}
		
		else {
			document.getElementsByName("validacion")[i].innerHTML = "";
		}
	}
	
	if (conteo == 0) {
		var parametros= [];
		for (var i = 0; i < x.length ; i++) {
			parametros[i]=x[i].value;
		}
		
		for (var j = 0; j < y.length ; j++) {
			if (y[j].checked) {
				tipo=y[j].value;
			}
		}
		
		var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function(){
	    	if (this.readyState == 4 && this.status == 200){
	    		if(this.responseText=="usuario agregado"){
	    			borrar("mostrar");
	    			document.getElementById("alerta").innerHTML= "<p onmouseover='borrar(\"alerta\")' class='alerta'>Usuario Agregado Exitósamente</p>";
	    			consultarUsuarios();
	    			
	    			
	    		}
	    		
	    		else {
	    			
	    			if(this.responseText=="si existe"){
	    				document.getElementsByName("validacion")[1].innerHTML= "*usuario ya registrado";
	    			}
	    			
	    			else{
	    				document.getElementsByName("validacion")[2].innerHTML= "*correo ya registrado";
	    			}
	    		}
	    	}
	    }
	    
	    xhttp.open("POST", "Servlet_Administrador_Add", true);
	    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xhttp.send("parametros="+parametros+"&tipo="+tipo+"&t"+Math.random());
	}
}

function consultarUsuarios(){
	borrar("mostrar");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("tabla").innerHTML = this.responseText;
		}
	}
	
	xhttp.open("GET", "Servlet_Administrador_Read?t="+ Math.random(), true);
    xhttp.send(); 
}

function editar(){
	borrar("mostrar");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("tabla").innerHTML = this.responseText;
		}
	}
	
	xhttp.open("GET", "Servlet_Administrador_Edit?t="+ Math.random(), true);
    xhttp.send(); 
	
}

function eliminarUsuario(id){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("alerta").innerHTML = "<p onmouseout='borrar(\"alerta\")' class='alerta'>Usuario Eliminado</p>";
			editar();
		}
	}	
	
	xhttp.open("GET", "Servlet_Administrador_Erase?t="+ Math.random()+"&id="+id, true);
    xhttp.send();
}

function editarUsuario(id){
	borrar("tabla");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("tabla").innerHTML = this.responseText;
		}
	}
	xhttp.open("POST", "Servlet_Administrador_EditUser", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("id="+id+"&t="+Math.random());
	
}

function actualizarUsuario(id){
	var elementos= document.getElementsByName("valid");
	var x= document.getElementsByName("tipo");
	var conteo=0;
	for (var i = 0; i < elementos.length ; i++) {
		if (elementos[i].value == "" || elementos[i].value==null) {
			document.getElementsByName("validacion")[i].innerHTML = " *Campo Obligatorio";
			conteo++;
		}
		
		else {
			document.getElementsByName("validacion")[i].innerHTML = "";
		}
	}
	
	for (var i=0; i< x.length; i++){
		if(x[i].checked){
			tipo = x[i].value;
		}
	}
	
	if(conteo==0){
		var parametros=[];
		
		for(var i=0; i < elementos.length ;i++ ){
			parametros[i] = elementos[i].value;
		}
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if(this.readyState == 4 && this.status == 200){
				if(this.responseText=="editado"){
					borrar("mostrar");
					editar();
					document.getElementById("alerta").innerHTML= "<p class=\"alerta\" onmouseout=\"borrar('alerta')\">Usuario editado exitosamente</p>"
				}
				
				else{
					
					if(this.responseText=="si existe"){
						document.getElementsByName("validacion")[1].innerHTML ="*El usuario ya existe, Seleccione otro nombre";
					}
					
					else{
						document.getElementsByName("validacion")[2].innerHTML ="*El correo ya existe, Seleccione otro correo";
					}
					
				}

				
			}
		}
		xhttp.open("POST", "Servlet_Administrador_UpdateUser", true);
	    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xhttp.send("parametros="+parametros+"&id="+id+"&tipo="+tipo+"&t="+Math.random());
	}
	
}


function agregarGrupo(){
	borrar("mostrar");
	borrar("tabla");
	document.getElementById("mostrar").innerHTML =
		"<form>"+
		"<table>"+
		"<tr>"+
		"<td>Nombre Grupo</td><td><input type=\"text\" placeholder=\"Nombre Grupo\" class='form-control' name='registroGrupo'><span name='validacion' class='alerta'></span></td>"+
		"</tr>"+
		"<tr>"+
		"<td>Numero de Alumnos</td><td><input type=\"number\" min=\"1\" max=\"30\" class='form-control' placeholder=\"Máximo 30 \" name='registroGrupo'><span name='validacion' class='alerta'></span></td>"+
		"</tr>"+
		"<tr><td><button type=\"button\" onclick=\"addGrupo()\">Registrar</button></td></tr>"+
		"</table>"+
		"</form>"
	    ;
}  

function addGrupo(){
	var x= document.getElementsByName("registroGrupo");
	var conteo=0;
	for(var i=0;i<x.length;i++){
		if (x[i].value == "" || x[i].value==null){
			document.getElementsByName("validacion")[i].innerHTML = " *Campo Obligatorio";
			conteo++;
		}
		else {
			document.getElementsByName("validacion")[i].innerHTML = "";
		}
	}
	
	if(conteo==0 ){
		
		if(x[1].value <= 30){
			var parametros= [];
			for (var i = 0; i < x.length ; i++) {
				parametros[i]=x[i].value.toLowerCase();
			}
			
					
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					if(this.responseText=="grupo agregado"){
						document.getElementById("alerta").innerHTML = "<p onmouseout='borrar(\"alerta\")' class='alerta'>Grupo agregado</p>";
						consultarGrupos();
					}
					else{
						document.getElementById("alerta").innerHTML = "<p onmouseout='borrar(\"alerta\")' class='alerta'>El grupo ya existe</p>";
					}
				}
			}
			xhttp.open("POST", "Servlet_BrowseGroup", true);
		    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		    xhttp.send("parametros="+parametros+"&t="+Math.random());
		}
		
		else{
			document.getElementsByName("validacion")[1].innerHTML = "*Introduzca un numero menor a 30";
		}
		
		
	}
} 

function consultarGrupos(){
	borrar("mostrar");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			document.getElementById("tabla").innerHTML = this.responseText;
		}
	}
	
	xhttp.open("GET", "Servlet_Administrador_ReadGrupos?t="+ Math.random(), true);
    xhttp.send(); 
}

function cerrarSesion(){
	var xhttp= new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			window.location="login.html";
		}
	}
	xhttp.open("GET", "Servlet_DeleteSession?t="+ Math.random(), true);
	xhttp.send();
}

$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
})



function comenzar(){
    $("#jmbnt").hide();
    consultarUsuarios();

}
