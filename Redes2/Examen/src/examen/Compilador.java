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
import java.util.LinkedList;


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
    
    public Object buscar(LinkedList <Object> tabla, String clave, String valor) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException{
        for(Object obj : tabla){
            System.out.println(obj.getClass().getField(clave).get(obj));
            System.out.println(valor);
            if(obj.getClass().getField(clave).get(obj).toString().equals(valor)){
                return obj;
            }
        }
        
        return null;
    }
    
    public Object actualizar(Object actualizable, ArrayList<String> nuevos) throws InstantiationException, IllegalAccessException, NoSuchFieldException{
        ArrayList<Field> campos = new ArrayList<Field>(Arrays.asList(actualizable.getClass().getFields()));
        
        for(int i = 0, j = 1; i < nuevos.size(); i += 2, j += 2){
            Field campo = actualizable.getClass().getField(nuevos.get(i));
            
            switch(campo.getType().toString()){
                case "byte":
                    campo.set(actualizable, Byte.parseByte(nuevos.get(j).toString()));
                    break;
                case "short":
                    campo.set(actualizable, Short.parseShort(nuevos.get(j).toString()));
                    break;
                case "int":
                    campo.set(actualizable, Integer.parseInt(nuevos.get(j).toString()));
                    break;
                case "long":
                    campo.set(actualizable, Long.parseLong(nuevos.get(j).toString()));
                    break;
                case "float":
                    campo.set(actualizable, Float.parseFloat(nuevos.get(j).toString()));
                    break;
                case "double":
                    campo.set(actualizable, Double.parseDouble(nuevos.get(j).toString()));
                    break;
                case "boolean":
                    campo.set(actualizable, Boolean.parseBoolean(nuevos.get(j).toString()));
                    break;
                case "char":
                    campo.set(actualizable, nuevos.get(j).toString().charAt(1));
                    break;
                case "class java.lang.String":
                    campo.set(actualizable, nuevos.get(j).toString());
                    break;
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