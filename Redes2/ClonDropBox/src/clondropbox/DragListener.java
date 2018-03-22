package clondropbox;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
public class DragListener implements DropTargetListener  {
    private DefaultListModel dm;
    private String names;
    private Servicio conexion;
    
    JList list = new JList();
 
    
    public DragListener(JList list, Servicio conexion, DefaultListModel dm){
        this.dm = dm;
        this.list = list;
        this.conexion = conexion;
    }
    
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
       
    }
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
    @Override
    public void dragExit(DropTargetEvent dte) {
    }
    @Override
    public void drop(DropTargetDropEvent ev) {
        
        //ACCESPT DROPPED ITEMS
        ev.acceptDrop(DnDConstants.ACTION_COPY);
         //GET DROPPED ITEMS
        Transferable t=ev.getTransferable();
        
        //GET DATA FORMATS OF THE ITEMS
        DataFlavor[] df=t.getTransferDataFlavors();
        for(DataFlavor f: df)
        {
            try
            {
                
                if(f.isFlavorJavaFileListType())
                {
                    List<File> files=(List<File>) t.getTransferData(f);
                    this.names = "";
                    //LOOP THRU THEM
                    for(File file: files){
                        conexion.enviarArchivo(file);
                        dm.clear();
                        ArrayList<String> archivos = conexion.obtenerListaArchivos();
                        for(String archivo : archivos){
                            dm.addElement(archivo);
                        }
                    }

                }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public String getNames() {
        return names;
    }
}