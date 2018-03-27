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
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;


public class Compilador {
    private final String nombreTabla;
    private StringWriter writer;
    
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
            out.print("private ");
            if(campos[i].equals("string")){
                out.print("String");
            }else{
                out.print(campos[i]);
            }
            out.print(" "+campos[j]+";");
            out.print("\n");
        }

        out.print("\n");
        out.print("public "+nombreTabla+"(");

        for(int i = 0, j = 1; i < campos.length; i += 2, j += 2){
            if(campos[i].equals("string")){
                out.print("String");
            }else{
                out.print(campos[i]);
            }
            out.print(" "+campos[j]);
            out.print(j == campos.length - 1? "" : ", ");
        }

        out.println("){");

        for(int i = 1; i < campos.length; i += 2){
            out.println("this."+campos[i]+" = "+campos[i]+";");
        }
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
        System.out.println("Success: " + success);
        return success;
    }
    
    public Object instanciar(String nombreClase) throws MalformedURLException, ClassNotFoundException{
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("").toURI().toURL() });
        return Class.forName(nombreClase, true, classLoader).getConstructors();
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