package practica2.redes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;


public class Practica2Redes {
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
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs

		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf
			    .toString());
			return;
		}

		System.out.println("Network devices found:");

		int i = 0;
                try{
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);

		}//for

		PcapIf device = alldevs.get(2); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam máx de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis
                Pcap pcap =
		    Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
			    + errbuf.toString());
			return;
		}//if

                       /********F I L T R O********/
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression ="port 123"; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Filter error: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);
                /****************/


		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
                System.out.println("Antes de");
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
                        int i = 1;
                        @Override
			public void nextPacket(PcapPacket packet, String user) {
                            System.out.println("Aqui entra");
                                
				/*System.out.printf("Paquete recibido el %s caplen=%-4d len=%-4d %s\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );
                                /******Desencapsulado********/
                                System.out.println("Paquete: "+i);
                                ++i;
                                String hexadecimal,macD,macO,tipo,protocolo;
                                hexadecimal = packet.toHexdump();
                                macD = hexadecimal.substring(6, 24);
                                macO = hexadecimal.substring(25, 43);
                                tipo = hexadecimal.substring(45,50);
                                protocolo = (tipo.equals("08 00"))?"IPv4":
                                        (tipo.equals("86 dd"))?"IPv6":
                                        (tipo.equals("02 00"))?"Xerox PUP":
                                        (tipo.equals("08 03"))?"ECMA Internet":
                                        (tipo.equals("08 02"))?"NBS Internet":
                                        (tipo.equals("02 00"))?"Xerox PUP":
                                        (tipo.equals("08 07"))?"XNS Compatibility":
                                        (tipo.equals("09 00"))?"Ungermann-Bass network debugger":
                                        (tipo.equals("80 69"))?"AT&T":
                                        (tipo.equals("80 9b"))?"EtherTalk":
                                        (tipo.equals("81 91"))?"PowerLAN":
                                        (tipo.equals("08 01"))?"X.75 Internet":
                                        (tipo.equals("08 04"))?"CHAOSnet":
                                        (tipo.equals("08 06"))?"Address Resolution Protocol (ARP)":
                                        (tipo.equals("04 00"))?"Nixdorf":
                                        (tipo.equals("60 09"))?"DEC MUMPS":
                                        (tipo.equals("80 c7"))?"Applitek Corporation":
                                        (tipo.equals("00 69"))?"ARP":"Otro";
                                
                                System.out.println("MAC Destino: "+macD);
                                System.out.println("MAC Origen: "+macO);
                                System.out.println("Tipo: "+tipo);
                                System.out.println("Protocolo: "+protocolo);
                                System.out.println();
			}
		};


		/***************************************************************************
		 * Fourth we enter the loop and tell it to capture 10 packets. The loop
		 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
		 * is needed by JScanner. The scanner scans the packet buffer and decodes
		 * the headers. The mapping is done automatically, although a variation on
		 * the loop method exists that allows the programmer to sepecify exactly
		 * which protocol ID to use as the data link type for this pcap interface.
		 **************************************************************************/
                    System.out.println("Antes de entrar al loop");
		pcap.loop(10, jpacketHandler, "");
                    System.out.println("despues de entrar akl loop");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
	}
}