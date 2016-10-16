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
                    System.out.printf("\nPaquete recibido el %s caplen=%-4d longitud=%-4d %s\n",
                            new Date(packet.getCaptureHeader().timestampInMillis()),
                            packet.getCaptureHeader().caplen(),  // Length actually captured
                            packet.getCaptureHeader().wirelen(), // Original length
                            user                                 // User supplied object
                    );
                    /******Desencapsulado********/
                    for(int i=0;i<packet.size();i++){
                        System.out.printf("%02X ",packet.getUByte(i));
                        
                        if(i%16==15)
                            System.out.println();
                    }//if
                    int longitud = (packet.getUByte(12)*256)+packet.getUByte(13);
                    System.out.printf("\nLongitud: (%04X) ",longitud );
                    if(longitud<1500){
                        System.out.println("(Trama IEEE802.3)");
                        System.out.printf("\nMAC Destino: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(0),packet.getUByte(1),packet.getUByte(2),packet.getUByte(3),packet.getUByte(4),packet.getUByte(5));
                        System.out.printf("\nMAC Origen: %02X:%02X:%02X:%02X:%02X:%02X",packet.getUByte(6),packet.getUByte(7),packet.getUByte(8),packet.getUByte(9),packet.getUByte(10),packet.getUByte(11));
                        System.out.printf("\nLongitud: %d",longitud);
                        System.out.printf("\nDSAP:");
                        System.out.printf("\n\t%02X (Individual)",packet.getUByte(14));
                        
                        
                        int ssap = packet.getUByte(15)& 0x00000001;
                        String c_r = (ssap==1)?"(Respuesta)":(ssap==0)?"(Comando)":"(Otro)";
                        System.out.printf("\nSSAP:");
                        System.out.printf("\n\t%02X %s",packet.getUByte(15), c_r);
                        
                        System.out.println("\nCampo de control:");
                        tipoTrama(packet.getUByte(16),packet.getUByte(17));
                        
                    } else if(longitud>=1500){
                        System.out.println("-->Trama ETHERNET");
                    }//else
                    //System.out.println("\n\nEncabezado: "+ packet.toHexdump());
                }

                private void tipoTrama(int byte16,int byte17) {
                    String ns = "",nr = "",codigo = "";
                    int temp = 0;
                    StringBuilder b16,b17;
                    b16 = new StringBuilder(Integer.toBinaryString(byte16));
                    b17 = new StringBuilder(Integer.toBinaryString(byte17));
                    b16.reverse();
                    b17.reverse();
                    while(b16.length() != 8)
                        b16.append("0");
                    while(b17.length() != 8)
                        b17.append("0");
                    if(Integer.parseInt(b16.substring(0, 1)) == 0){
                        System.out.println("Tipo I");
                        System.out.print("N(s): ");
                        ns = b16.substring(1,8);
                        for (int i = 0; i < 7; i++) {
                            if(ns.charAt(i) == '1')
                                temp = temp+(int)(1*Math.pow(2, i));
                        }
                        System.out.println(temp);
                        System.out.print("N(r): ");
                        nr = b17.substring(1, 8);
                        temp = 0;
                        for (int i = 0; i < 7; i++) {
                            if(nr.charAt(i) == '1')
                                temp = temp+(int)(1*Math.pow(2, i));
                        }
                        System.out.println(temp);
                    }
                    else{
                        if(Integer.parseInt(b16.substring(1, 2)) == 0){
                            System.out.println("\tTipo S");
                            System.out.print("\tCódigo: ");
                            codigo = b16.substring(2,4);
                            switch(codigo){
                                case "00":
                                    System.out.println("RR");
                                    break;
                                case "01":
                                    System.out.println("REJ");
                                    break;
                                case "10":
                                    System.out.println("RNR");
                                    break;
                                case "11":
                                    System.out.println("SREJ");
                                    break;
                            }
                            System.out.print("\tN(r): ");
                            nr = b17.substring(1, 8);
                            temp = 0;
                            for (int i = 0; i < 7; i++) {
                                if (nr.charAt(i) == '1')
                                    temp = temp + (int) (1 * Math.pow(2, i));
                            }
                            System.out.println(temp);
                        }
                        else{
                            System.out.println("\tTipo U");
                            System.out.print("\tCódigo: ");
                            codigo = b16.substring(2,4);
                            switch(codigo){
                                case "00":
                                    System.out.println("RR");
                                    break;
                                case "01":
                                    System.out.println("REJ");
                                    break;
                                case "10":
                                    System.out.println("RNR");
                                    break;
                                case "11":
                                    System.out.println("SREJ");
                                    break;
                            }
                        }
                    }
                }
            };
            pcap.loop(-1, jpacketHandler, " ");
            pcap.close();
	}
}