package examen;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;


public class Compilador {
    private final String nombreTabla;
    private StringWriter writer;
    
    public Compilador(String nombreTabla){
        this.nombreTabla = nombreTabla;
    }
    
    public Compilador(String nombreTabla, String [] campos) throws IOException {
        this.nombreTabla = nombreTabla;
        this.writer = new StringWriter();
        
        PrintWriter out = new PrintWriter(writer);
        crearClase(campos, out);
        
        out.close();
        
        System.out.println(writer.toString());
    }
    
    private void crearClase(String [] campos, PrintWriter out){
        out.println("public class "+nombreTabla+"{");
        for(int i = 0, j = 1; i < campos.length; i += 2, j += 2){
            out.print("public ");
            if(campos[i].equals("string")){
                out.print("String");
            }else{
                out.print(campos[i]);
            }
            out.print(" "+campos[j]+";");
            out.print("\n");
        }
        
        out.println("public "+nombreTabla+"(){}");
        
        for(int i = 0, j = 1; i < campos.length; i += 2, j += 2){
            out.println("public "+campos[i]+" get"+campos[j]+"(){");
            out.println("return this."+campos[j]+";");
            out.println("}");
        }
        out.print("\n");
        
        out.println("@Override");
        out.println("public String toString(){");
        out.print("return ");
        for(int i = 0, j = 1; i < campos.length; i += 2, j += 2){
            out.print("\" "+campos[j]+": \"+this."+campos[j]+"");
            out.print(j == campos.length - 1? "" : "+");
        }
        out.println(";");
        out.println("}");
        out.println("}");
    }
  
    public static void main(String[] args) throws IOException {
        new Compilador("Prueba", new String[]{"String", "nombre", "int", "edad", "float", "calificaciones"});
    }

    public boolean compilar() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        JavaFileObject file = new JavaSourceFromString(nombreTabla, writer.toString());

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean success = task.call();
        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getCode());
            System.out.println(diagnostic.getKind());
            System.out.println(diagnostic.getPosition());
            System.out.println(diagnostic.getStartPosition());
            System.out.println(diagnostic.getEndPosition());
            System.out.println(diagnostic.getSource());
            System.out.println(diagnostic.getMessage(null));

        }
        return success;
    }
    
    public Object instanciar(ArrayList datos) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
        Class clase = Class.forName(nombreTabla, true, classLoader);
        
        Object instancia = clase.newInstance();
        Field [] campos = clase.getFields();
        
        if(campos.length != datos.size()){
            return null;
        }
        for(int i = 0; i < campos.length; i++){
            try{
                convertir(i, campos, instancia, datos);
            }
            catch(NumberFormatException ex){
                System.out.println("Error: "+ex.getMessage());
                return null;
            }
        }
        classLoader.close();
        return instancia;
    }
    
    public Object actualizar(Object actualizable, ArrayList<String> nuevos) throws InstantiationException, IllegalAccessException{
        Class clase = actualizable.getClass();
        
        Object instancia = clase.newInstance();
        ArrayList<Field> campos = new ArrayList<Field>(Arrays.asList(clase.getFields()));
        
        for(int i = 0; i < campos.size(); i++){
            try{
                if(nuevos.contains(campos.get(i).getName())){
                    convertir(i, campos, instancia, nuevos);
                }
                else{
                    convetir()
                }
            }catch(Exception ex){
                System.out.println("Valió barriga: "+ex.getMessage());
            }
        }
        
        return actualizable;
    }
    
    private void convertir(int i, Field[] campos, Object instancia, ArrayList datos) throws IllegalArgumentException, IllegalAccessException{
        switch(campos[i].getType().toString()){
            case "byte":
                campos[i].set(instancia, Byte.parseByte(datos.get(i).toString()));
                break;
            case "short":
                campos[i].set(instancia, Short.parseShort(datos.get(i).toString()));
                break;
            case "int":
                campos[i].set(instancia, Integer.parseInt(datos.get(i).toString()));
                break;
            case "long":
                campos[i].set(instancia, Long.parseLong(datos.get(i).toString()));
                break;
            case "float":
                campos[i].set(instancia, Float.parseFloat(datos.get(i).toString()));
                break;
            case "double":
                campos[i].set(instancia, Double.parseDouble(datos.get(i).toString()));
                break;
            case "boolean":
                campos[i].set(instancia, Boolean.parseBoolean(datos.get(i).toString()));
                break;
            case "char":
                campos[i].set(instancia, datos.get(i).toString().charAt(1));
                break;
            case "class java.lang.String":
                campos[i].set(instancia, datos.get(i).toString());
                break;
        }
    }
}

class JavaSourceFromString extends SimpleJavaFileObject {
    final String code;

    JavaSourceFromString(String name, String code) {
      super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
      this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
      return code;
    }
}