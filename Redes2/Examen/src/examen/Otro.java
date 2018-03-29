
package examen;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Otro{
    public static void main(String[] args) {
	String texto = "crear tabla alumnos(String nombre, int edad, double popularidad)";
	Pattern exp = Pattern.compile("(?<=\\()(\\w+(?:,*\\x20*))+(?=\\))");
	Matcher m = exp.matcher(texto);
        if(!m.find()){
            return;
        }
        for(int i = 0; i < m.groupCount(); i++){
            System.out.println(m.group(i));
        }
    }
}