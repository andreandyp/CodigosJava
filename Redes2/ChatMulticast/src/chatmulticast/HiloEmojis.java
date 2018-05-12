package chatmulticast;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class HiloEmojis extends Thread{
    private final DefaultTableModel listaemojis;

    @Override
    public void run() {
        ArrayList<Emoji> emojis = (ArrayList) EmojiManager.getAll();
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        listaemojis.addColumn("");
        for(int i = 0, em = 0; i < 232 && em < emojis.size(); i++){
            String fila[] = new String[7];
            for(int j = 0; j < 7 && em < emojis.size(); j++){
                fila[j] = emojis.get(em++).getUnicode();
            }
            listaemojis.addRow(fila);
        }
        System.out.println("Fin");
        return;
    }
    
    public HiloEmojis(DefaultTableModel listaemojis){
        this.listaemojis = listaemojis;
    }
    
}
