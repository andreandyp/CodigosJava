/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graficas {
    int tcp = 0,udp = 0,icmp = 0,igmp = 0,arp = 0,llc = 0;
    JFreeChart grafica;
    DefaultCategoryDataset datos = new DefaultCategoryDataset();
    public void aumentar(int tcp,int udp,int icmp,int igmp,int arp,int llc){
        this.tcp += tcp;
        this.udp += udp;
        this.icmp += icmp;
        this.igmp += igmp;
        this.arp += arp;
        this.llc += llc;
    }
    public void ceros(){
        this.tcp = 0;
        this.udp = 0;
        this.icmp = 0;
        this.igmp = 0;
        this.arp = 0;
        this.llc = 0;
    }
    public void terminar(){
        datos.addValue(tcp, "TCP", "Paquetes");
        datos.addValue(udp, "UDP", "Paquetes");
        datos.addValue(icmp, "ICMP", "Paquetes");
        datos.addValue(igmp, "IGMP", "Paquetes");
        datos.addValue(arp, "ARP", "Paquetes");
        datos.addValue(llc, "LLC", "Paquetes");
        

grafica = ChartFactory.createBarChart("Paquetes analizados",
"", "", datos,
PlotOrientation.VERTICAL, true, false, false);
ChartPanel panel = new ChartPanel(grafica);

JFrame Ventana = new JFrame("JFreeChart");
Ventana.getContentPane().add(panel);
Ventana.pack();
Ventana.setVisible(true);
Ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
