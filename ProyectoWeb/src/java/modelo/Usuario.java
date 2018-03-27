package modelo;

public class Usuario {
	
	private String usuario, password;
	
	public Usuario(String usuario, String password){
		this.usuario = usuario;
		this.password = password;
	}
	
	
	public boolean existeUsuario(String usuario){
		
		if(this.usuario.equals(usuario)){
			return true;
		}
		
		else return false;
	} 
	
	public boolean compruebaPass(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		
		else return false;
	}
	
	public String getUser() {
		return usuario;
	}
	

}
