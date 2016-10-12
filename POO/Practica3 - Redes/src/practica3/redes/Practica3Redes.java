package practica3.redes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

public class Practica3Redes {

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(final byte[] mac) {
    final StringBuilder buf = new StringBuilder();
    for (byte b : mac) {
      if (buf.length() != 0) {
        buf.append(':');
      }
      if (b >= 0 && b < 16) {
        buf.append('0');
      }
      buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
    }

    return buf.toString();
  }

	public static void main(String[] args) {
            Pcap pcap=null;
            List<PcapIf> alldevs = new ArrayList<PcapIf>();
            StringBuilder errbuf = new StringBuilder();
            String fname = "paquetes3.pcap";
            pcap = Pcap.openOffline(fname, errbuf);
            if (pcap == null) {
                System.err.printf("Error while opening device for capture: "+ errbuf.toString());
                return;
            }
            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                
                public void nextPacket(PcapPacket packet, String user) {
                    
                    System.out.printf("\n\nPaquete recibido el %s caplen=%-4d longitud=%-4d %s\n\n",
                            new Date(packet.getCaptureHeader().timestampInMillis()),
                            packet.getCaptureHeader().caplen(),  // Length actually captured
                            packet.getCaptureHeader().wirelen(), // Original length
                            user                                 // User supplied object
                    );
                    
                    
                    /******Desencapsulado********/
                    for(int i=0;i<packet.size();i++){
                        System.out.printf("%02X ",packet.getUByte(i));
                        
                        if(i%16==15)
                            System.out.println("");
                    }//if
                    
                    int longitud = (packet.getUByte(12)*256)+packet.getUByte(13);
                    System.out.printf("\nLongitud: %d (%04X)",longitud,longitud );
                    if(longitud<1500){
                        System.out.println("--->Trama IEEE802.3");
                        System.out.printf(" |-->MAC Destino: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(0),packet.getUByte(1),packet.getUByte(2),packet.getUByte(3),packet.getUByte(4),packet.getUByte(5));
                        System.out.printf("\n |-->MAC Origen: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(6),packet.getUByte(7),packet.getUByte(8),packet.getUByte(9),packet.getUByte(10),packet.getUByte(11));
                        System.out.printf("\n |-->Longitud: %02X",longitud);
                        System.out.printf("\n |-->DSAP: %02X",packet.getUByte(14));
                        int ssap = packet.getUByte(15)& 0x00000001;
                        String c_r = (ssap==1)?"Respuesta":(ssap==0)?"Comando":"Otro";
                        System.out.printf("\n |-->SSAP: %02X   %s",packet.getUByte(15), c_r);
                        System.out.println("Tipo de trama: ");
                        
                        System.out.println("N(s):");
                        System.out.println("N(r):");
                        System.out.println("Código:");
                        
                    } else if(longitud>=1500){
                        System.out.println("-->Trama ETHERNET");
                    }//else
                    
                    
                    //System.out.println("\n\nEncabezado: "+ packet.toHexdump());
                    
                    
                }
            };
            pcap.loop(-1, jpacketHandler, " ");
            pcap.close();
	}
}