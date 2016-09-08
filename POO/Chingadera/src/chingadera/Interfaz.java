package chingadera;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class Interfaz extends JFrame implements ActionListener {
    
    JLabel Jl1, Jl2, mensaje;
    JButton Jb1, Jb2, Jb3;
    ImageIcon imag[];
    URL url0,url1,url2;
    
//CONSTRUCTOR
public Interfaz(){
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    url0 = this.getClass().getResource("piedra2.jpg");
    url1 = this.getClass().getResource("papel2.jpg");
    url2 = this.getClass().getResource("tijeras2.jpg");
    imag = new ImageIcon[3];
    imag[0] = new ImageIcon(url0);
    imag[1] = new ImageIcon(url1);
    imag[2] = new ImageIcon(url2);
    
    JPanel Jp1=new JPanel();
    Jb1 = new JButton(imag[0]);
    Jb2 = new JButton(imag[1]);
    Jb3 = new JButton(imag[2]);
    Jp1.add(Jb1); Jp1.add(Jb2); Jp1.add(Jb3);
    
    mensaje = new JLabel ("A jugar");
    Jl1 = new JLabel ("Your choice");
    Jl2 = new JLabel ("                             Opponent");
    JPanel Jp2 = new JPanel();
    Jp2.add(Jl1);Jp2.add(Jl2);
    Jb1.addActionListener(this);
    Jb2.addActionListener(this);
    Jb3.addActionListener(this);
    getContentPane().add("South",Jp1);
    getContentPane().add("Center",Jp2);
    getContentPane().add("West",mensaje);
    setSize(600,600);
    setVisible(true);

}

    @Override
    public void actionPerformed(ActionEvent e){
        int x = (int)(Math.random()*3);
        Jl2.setIcon(imag[x]);
        
        JButton Jb=(JButton)e.getSource();
 //EMPATES       
        if(Jb==Jb1 && imag[x]==imag[0]) 
            mensaje.setText("EMPATE");
            
        if(Jb==Jb2 && imag[x]==imag[1])
            mensaje.setText("EMPATE");
        
        if(Jb==Jb3 && imag[x]==imag[2])
            mensaje.setText("EMPATE");
  //VICTORIAS
  
        if(Jb==Jb1 && imag[x]==imag[2])//PIEDRA VS TIJERA
            mensaje.setText("GANASTE");
        if(Jb==Jb3 && imag[x]==imag[1])//TIJERA VS PAPEL
            mensaje.setText("GANASTE");
        if(Jb==Jb2 && imag[x]==imag[0])//PAPEL VS PIEDRA
            mensaje.setText("GANASTE");
        
  //DERROTAS
        if(Jb==Jb1 && imag[x]==imag[1])//PIEDRA VS PAPEL
            mensaje.setText("PERDISTE");
        if(Jb==Jb3 && imag[x]==imag[0])//TIJERA VS PIEDRA
            mensaje.setText("PERDISTE");
        if(Jb==Jb2 && imag[x]==imag[2])//PAPEL VS TIJERA
            mensaje.setText("PERDISTE"); 
}
}
