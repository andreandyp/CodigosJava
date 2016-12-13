document.addEventListener("DOMContentLoaded", function() {
	window.history.forward(1);
	var xhttp= new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			
		}
	}
	xhttp.open("GET", "Servlet_DeleteSession?t="+ Math.random(), true);
	xhttp.send();
	});






function cargar (){
	var xhttp= new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			alert(this.responseText);
		}
	}
	xhttp.open("GET", "Servlet_DeleteSession?t="+ Math.random(), true);
	xhttp.send();
}

function valida() {
	
	var user= document.getElementById("usrname").value;
	var pass= document.getElementById("pass").value;
	
	if((pass==null || pass == "") && (user==null || user == "")){
		 document.getElementById("rusrname").innerHTML = "*Campo Obligatorio";
		 document.getElementById("rpass").innerHTML = "*Campo Obligatorio";
	}
	
	else if ((pass==null || pass == "") && (user!=null || user != "")) {
		document.getElementById("rpass").innerHTML = "*Campo Obligatorio";
		document.getElementById("rusrname").innerHTML = "";
	}
	
	else if ((pass!=null || pass != "") && (user==null || user == "")) {
		document.getElementById("rusrname").innerHTML = "*Campo Obligatorio";
		document.getElementById("rpass").innerHTML = "";
	}
	
	else{
		document.getElementById("rpass").innerHTML = "";
		document.getElementById("rusrname").innerHTML = "";
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function(){
	    	if (this.readyState == 4 && this.status == 200) {
	    		var cadenaValidacion= this.responseText;
		    	  if (cadenaValidacion=="Bienvenido estudiante"){
		    		  window.location = "estudiante.html";
		    	  }
		    	  
		    	  if(cadenaValidacion=="Bienvenido profesor"){
		    		  window.location = "profesor.html";
		    	  }
		    	  
		    	  if(cadenaValidacion=="Bienvenido administrador"){
		    		  window.location = "administrador.html";
		    	  }
		    	  
		    	  else{
		    		  if(cadenaValidacion == "Contraseña invalida"){
		    			  document.getElementById("rpass").innerHTML = this.responseText;  
		    		  }
		    		  
		    		  if(cadenaValidacion == "Usuario Invalido"){
		    			  document.getElementById("rusrname").innerHTML = this.responseText;
		    		  }
		    		  
		    	  }
		        
	    	}
	    }
	    xhttp.open("POST", "Servlet_login", true);
	    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xhttp.send("user="+user+"&pass="+pass);
	}	
}


