package redessniffer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

class Vuelo {
public static Ip4 ip = new Ip4();
	public static Ethernet eth = new Ethernet();
	public static Tcp tcp = new Tcp();
	public static Udp udp = new Udp();
	/*	public static Rip rip = new Rip() {
			void printheader() {
			System.out.println(rip.getHeader());
			}
			}; */
	
	public static Arp arp = new Arp();
	public static Payload payload = new Payload();
	public static byte[] payloadContent;
	public static boolean readdata = false;	public static byte[] myinet = new byte[3];
	public static byte[] mymac = new byte[5];

	public static InetAddress inet;
	public static Enumeration e;
	public static NetworkInterface n;
	public static Enumeration ee;
    public Vuelo() {
        List<PcapIf> alldevs = new ArrayList<PcapIf>();
         final int snaplen = Pcap.DEFAULT_SNAPLEN;
    final int flags = Pcap.MODE_PROMISCUOUS;
    final int timeout = Pcap.DEFAULT_TIMEOUT;
    final StringBuilder errbuf = new StringBuilder();
    int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("No se puede leer lista de dispositivos, error es %s", errbuf
			    .toString());
			return;
		}
                for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "Descripcion no disponible";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
                        if(i==3)
                            mac1=dir_mac;//direccion mac de la lap
		}//for
    }
    
}
