package paquete;

import org.jnetpcap.PcapDumper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.PcapHandler;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class Interfaz extends javax.swing.JFrame {
    Graficas hue = new Graficas();

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

    static String mac1 = "";
    String res = "";

    public Interfaz() {

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("Redes de Computadoras");
    }

    private void ejecutar() {
        int tam = 0;
        if (Entrada.getText().equals("0") || Entrada.getText().equals("")) {
            tam = 10;
            JOptionPane.showMessageDialog(rootPane, "Valor establecido a 10");
        } else {
            tam = Integer.parseInt(Entrada.getText());
        }

        List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
        StringBuilder errbuf = new StringBuilder(); // For any error msgs

        int r = Pcap.findAllDevs(alldevs, errbuf);
        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
            System.err.printf("No se puede leer lista de dispositivos, error es %s", errbuf
                    .toString());
            return;
        }

        System.out.println("Dispositivos encontrados:");

        int i = 0;
        try {
            for (PcapIf device : alldevs) {
                String description
                        = (device.getDescription() != null) ? device.getDescription()
                                : "Descripcion no disponible";
                final byte[] mac = device.getHardwareAddress();
                String dir_mac = (mac == null) ? "No tiene direccion MAC" : asString(mac);
                System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);
                if (i == 3) {
                    mac1 = dir_mac;//direccion mac de la lap
                }
            }//for

            PcapIf device = alldevs.get(2); // We know we have atleast 1 device
            System.out.printf("\nElige nombre de la tarjeta de '%s' :\n", (device.getDescription() != null) ? device.getDescription()
                    : device.getName());

            int snaplen = 64 * 1024;           // Capture all packets, no trucation
            int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
            int timeout = 1000;           // 10 seconds in millis
            Pcap pcap
                    = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

            if (pcap == null) {
                System.err.printf("Error mientras se abria el dispositivo de captura: " + errbuf.toString());
                return;
            }//i

            /**
             * ******F I L T R O*******
             */
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression = ""; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Error de filtro: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);

            PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

                public void nextPacket(PcapPacket packet, String user) {

                    TextArea.setText(res);
                    System.out.printf("Paquete recibido a las: %s caplen=%-4d len=%-4d %s\n",
                            new Date(packet.getCaptureHeader().timestampInMillis()),
                            packet.getCaptureHeader().caplen(), // Length actually captured
                            packet.getCaptureHeader().wirelen(), // Original length
                            user // User supplied object
                    );
                    /**
                     * ****Desencapsulado*******
                     */
                    IEEE802dot3 i3e = new IEEE802dot3();
                    if (packet.hasHeader(i3e)) {
                        int longitud = i3e.getUShort(13);
                        System.out.printf("\nLongitud %X", longitud);
                    }//ieee802dot3

                    Ethernet eth = new Ethernet();
                    if (packet.hasHeader(eth)) {

                        int longitud = eth.getUShort(12);
                        int tipo = 0;

                        tipo = eth.type();
                        switch (tipo) {
                            case (int) 1:
                                System.out.println("\nTrama LLC individual");
                                IEEE802dot2 llc = new IEEE802dot2();
                                if (packet.hasHeader(llc)) {
                                    int ssap = llc.ssap();
                                    int dsap = llc.dsap();
                                    int control = llc.control();
                                    System.out.printf("SSAP: %s", ssap);
                                    System.out.printf("DSAP: %s", dsap);
                                    System.out.printf("Control: %x", control);
                                }//if
                                break;
                            case (int) 2:
                                System.out.println("\nTrama LLC de grupo");
                                IEEE802dot2 llcg = new IEEE802dot2();
                                if (packet.hasHeader(llcg)) {
                                    int ssap = llcg.ssap();
                                    int dsap = llcg.dsap();
                                    int control = llcg.control();
                                    System.out.printf("SSAP: %s", ssap);
                                    System.out.printf("DSAP: %s", dsap);
                                    System.out.printf("Control: %x", control);
                                }//if
                                break;
                            case (int) 2054:

                                Arp arp = new Arp();
                                if (packet.hasHeader(arp)) {

                                    res = res + "\nMensaje ARP\n";
                                    System.out.printf("Mensaje ARP");
                                    res = res + "Encabezado:\n" + packet.toHexdump();
                                    System.out.println("Encabezado: " + packet.toHexdump());
                                    res = res + "\nMac Destino: ";
                                    System.out.printf("Mac Destino: ");

                                    for (int i = 0; i < 6; i++) {

                                        res = res + packet.getUByte(i);
                                        System.out.printf("%X:", packet.getUByte(i));
                                    }

                                    res = res + "\nMac Origen: ";
                                    System.out.printf("\nMac Origen: ");
                                    for (int i = 6; i < 12; i++) {

                                        res = res + packet.getUByte(i);
                                        System.out.printf("%X:", packet.getUByte(i));
                                    }
                                    res = res + tipo + "\n";
                                    System.out.println(" (%X)\n" + tipo);

                                    int operacion = arp.operation();
                                    int[] sp = new int[4];
                                    int[] tp = new int[4];
                                    int[] sh = new int[4];
                                    int[] th = new int[4];
                                    int ht = arp.hardwareType();

                                    sp[0] = ((arp.spa()[0]) < 0) ? (arp.spa()[0]) + 256 : arp.spa()[0];
                                    sp[1] = ((arp.spa()[1]) < 0) ? (arp.spa()[1]) + 256 : arp.spa()[1];
                                    sp[2] = ((arp.spa()[2]) < 0) ? (arp.spa()[2]) + 256 : arp.spa()[2];
                                    sp[3] = ((arp.spa()[3]) < 0) ? (arp.spa()[3]) + 256 : arp.spa()[3];

                                    tp[0] = ((arp.tpa()[0]) < 0) ? (arp.tpa()[0]) + 256 : arp.tpa()[0];
                                    tp[1] = ((arp.tpa()[1]) < 0) ? (arp.tpa()[1]) + 256 : arp.tpa()[1];
                                    tp[2] = ((arp.tpa()[2]) < 0) ? (arp.tpa()[2]) + 256 : arp.tpa()[2];
                                    tp[3] = ((arp.tpa()[3]) < 0) ? (arp.tpa()[3]) + 256 : arp.tpa()[3];
                                    if (operacion == 1) {
                                        if (sp.equals(tp)) {
                                            System.out.println("ARP gratuito direccion " + sp[0] + "." + sp[1] + "." + sp[2] + "." + sp[3]);
                                            res = res + "\nARP gratuito direccion " + sp[0] + "." + sp[1] + "." + sp[2] + "." + sp[3] + "\n";
                                            System.out.println("ARP tha: " + th[0] + "." + th[1] + "." + th[2] + "." + th[3]);
                                            res = res + "ARP tha: " + th[0] + "." + th[1] + "." + th[2] + "." + th[3] + "\n";
                                        } else {
                                            System.out.println("ARP hardware type: " + arp.hardwareType());
                                            res = res + "\nARP hardware type: " + arp.hardwareType();
                                            System.out.println("ARP hlen: " + arp.hlen());
                                            res = res + "\nARP hlen: " + arp.hlen();
                                            System.out.println("ARP operation: " + arp.operation());
                                            res = res + "\nARP operation: " + arp.operation();
                                            System.out.println("ARP plen: " + arp.plen());
                                            res = res + "\nARP plen: " + arp.plen();
                                            System.out.println("ARP protocol type: " + arp.protocolType());
                                            res = res + "\nARP protocol type: " + arp.protocolType();
                                            System.out.println("ARP sha: " + FormatUtils.mac(arp.sha()));
                                            res = res + "\nARP sha: " + FormatUtils.mac(arp.sha());
                                            System.out.println("ARP tha: " + FormatUtils.mac(arp.tha()));
                                            res = res + "\nARP tha: " + FormatUtils.mac(arp.tha()) + "\n";
                                        }//else
                                    } else if (operacion == 2) {
                                        System.out.println("Respuesta ARP " + sp[0] + "." + sp[1] + "." + sp[2] + "." + sp[3] + " es" + asString(arp.sha()));
                                    }//if
                                    hue.aumentar(0, 0, 0, 0, 1, 0);
                                }//if
                                break;
                            case (int) 2048:

                                Ip4 ip = new Ip4();
                                if (packet.hasHeader(ip)) {

                                    res = res + "\nEncabezado:\n" + packet.toHexdump();
                                    System.out.println("\nEncabezado:\n" + packet.toHexdump());
                                    res = res + "\nMac Destino: ";
                                    System.out.printf("\nMac Destino: ");

                                    for (int i = 0; i < 6; i++) {

                                        res = res + packet.getUByte(i);
                                        System.out.printf("%X:", packet.getUByte(i));
                                    }

                                    res = res + "\nMac Origen: ";
                                    System.out.printf("\nMac Origen: ");
                                    for (int i = 6; i < 12; i++) {

                                        res = res + packet.getUByte(i);
                                        System.out.printf("%X:", packet.getUByte(i));
                                    }
                                    res = res + "\nTipo descrp: " + "(" + eth.typeEnum() + ")";
                                    res = res + tipo + "\n";
                                    System.out.println("\nTipo descrp: " + eth.typeEnum());
                                    System.out.printf(" (%X)\n", tipo);
                                    int s1 = ((ip.source()[0]) < 0) ? (ip.source()[0]) + 256 : ip.source()[0];
                                    int s2 = ((ip.source()[1]) < 0) ? (ip.source()[1]) + 256 : ip.source()[1];
                                    int s3 = ((ip.source()[2]) < 0) ? (ip.source()[2]) + 256 : ip.source()[2];
                                    int s4 = ((ip.source()[3]) < 0) ? (ip.source()[3]) + 256 : ip.source()[3];
                                    int d1 = ((ip.destination()[0]) < 0) ? (ip.destination()[0]) + 256 : ip.destination()[0];
                                    int d2 = ((ip.destination()[1]) < 0) ? (ip.destination()[1]) + 256 : ip.destination()[1];
                                    int d3 = ((ip.destination()[2]) < 0) ? (ip.destination()[2]) + 256 : ip.destination()[2];
                                    int d4 = ((ip.destination()[3]) < 0) ? (ip.destination()[3]) + 256 : ip.destination()[3];

                                    res = res + "IP destino: " + d1 + "." + d2 + "." + d3 + "." + d4 + "\n";
                                    System.out.printf("IP destino: " + d1 + "." + d2 + "." + d3 + "." + d4 + "\n");
                                    res = res + "IP origen: " + s1 + "." + s2 + "." + s3 + "." + s4 + "\n";
                                    System.out.printf("IP origen: " + s1 + "." + s2 + "." + s3 + "." + s4 + "\n");
                                    int protocolo = ip.type();
                                    switch (protocolo) {
                                        case 1:
                                            Icmp icmp = new Icmp();
                                            if (packet.hasHeader(icmp)) {
                                                System.out.println("Protocolo: " + "ICMP " + "(" + protocolo + ")");
                                                res = res + "\nProtocolo: " + "ICMP " + "(" + protocolo + ")";
                                                System.out.println("Tipo:" + icmp.type());
                                                res = res + "\nTipo:" + icmp.type();
                                                System.out.println("Tipo:" + icmp.typeDescription());
                                                res = res + "\nTipo:" + icmp.typeDescription();
                                                System.out.println("Tipo:" + icmp.typeEnum());
                                                res = res + "\nTipo:" + icmp.typeEnum();
                                                System.out.println("Codigo :" + icmp.code());
                                                res = res + "\nCodigo :" + icmp.code();
                                                System.out.printf("Checksum: " + "%02X", icmp.checksum());
                                                res = res + "\nChecksum: " + icmp.checksum();
                                                byte[] data = packet.getByteArray(0, packet.size());

                                                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
                                                String linea = "";

                                                try {
                                                    while ((linea = br.readLine()) != null) {
                                                        System.out.println(linea);
                                                        res = res + linea + "\n";
                                                    }//while
                                                    br.close();
                                                    System.out.println("\n\n");
                                                    res = res + "\n\n";
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }//if
                                                hue.aumentar(0, 0, 1, 0, 0, 0);
                                            break;
                                        case 2:
                                            System.out.println("Protocolo IGMP'");
                                            hue.aumentar(0, 0, 0, 1, 0, 0);
                                            break;

                                        case 6:
                                            Tcp tcp = new Tcp();

                                            if (packet.hasHeader(tcp)) {

                                                res = res + "Protocolo: " + "TCP " + "(" + protocolo + ")" + "\n";
                                                System.out.println("Protocolo: " + "TCP " + "(" + protocolo + ")");
                                                res = res + "Puerto origen:" + tcp.source() + "\n";
                                                System.out.println("Puerto origen:" + tcp.source());
                                                res = res + "Puerto destino:" + tcp.destination() + "\n";
                                                System.out.println("Puerto destino:" + tcp.destination());
                                                res = res + "Ventana: " + tcp.window() + "\n";
                                                System.out.println("Ventana: " + tcp.window());
                                                res = res + "Checksum: " + (+tcp.checksum());
                                                System.out.printf("Checksum: " + "%02X", tcp.checksum());
                                                res = res + "---" + tcp.isChecksumValid() + "\n";
                                                System.out.println("---" + tcp.isChecksumValid());
                                                res = res + "Numero de secuencia: " + tcp.seq() + "\nNumero de acuse: " + tcp.ack();
                                                System.out.println("Numero de secuencia: " + tcp.seq() + "\nNumero de acuse: " + tcp.ack());
                                                res = res + "\nBandera ack: " + tcp.flags_ACK() + "\nBandera syn: " + tcp.flags_SYN();
                                                System.out.println("Bandera ack: " + tcp.flags_ACK() + "\nBandera syn: " + tcp.flags_SYN());

                                                byte[] data = packet.getByteArray(0, packet.size());

                                                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
                                                String linea = "";

                                            }//if
                                            hue.aumentar(1, 0, 0, 0, 0, 0);
                                            break;
                                        case 17:
                                            Udp udp = new Udp();

                                            if (packet.hasHeader(udp)) {
                                                res = res + "Protocolo: " + "UDP " + "(" + protocolo + ")" + "\n";
                                                System.out.println("Protocolo: " + "UDP " + "(" + protocolo + ")");

                                                res = res + "Puerto origen:" + udp.source() + "\nPuerto destino:" + udp.destination() + "\n";
                                                System.out.println("Puerto origen:" + udp.source() + "\nPuerto destino:" + udp.destination());
                                                res = res + "Longitud: " + udp.length() + "\n";
                                                System.out.println("Longitud: " + udp.length());
                                                res = res + "Checksum: " + udp.checksum();
                                                System.out.printf("Checksum: " + "%02X", udp.checksum());
                                                res = res + "---" + udp.isChecksumValid() + "\n";
                                                System.out.printf("---" + udp.isChecksumValid() + "\n");

                                                byte[] data = packet.getByteArray(0, packet.size());
                                                BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data)));
                                                String linea = "";
                                            }//if udp
                                            hue.aumentar(0, 1, 0, 0, 0, 0);
                                            break;
                                    }//switch_protocolo                                                
                                }//if_ip
                                break;
                        }//switch

                    }//if
                }
            };
            hue.ceros();
            pcap.loop(tam, jpacketHandler, "Redes de computadoras");
            TextArea.setText(res);
            pcap.close();
            hue.terminar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        res = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo = new javax.swing.ButtonGroup();
        Entrada = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(Entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 130, 20));

        jLabel4.setText("Numero de paquetes");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 20));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Sniffer");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 280, 30));

        jButton1.setText("Analizar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 230, 30));

        jToolBar1.setRollover(true);
        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        TextArea.setColumns(20);
        TextArea.setLineWrap(true);
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 450, 550));

        jButton2.setText("Leer Archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jButton3.setText("Crear Archivo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TextArea.setText("");
        ejecutar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String archivo = JOptionPane.showInputDialog(null, "Nombre del archivo a abrir");
        Pcap pcap = null;
        StringBuilder errbuf = new StringBuilder(); // For any error msgs
        String fname = archivo;
        pcap = Pcap.openOffline(fname, errbuf);
        if (pcap == null) {
            System.err.printf("Error while opening device for capture: " + errbuf.toString());
            return;
        }//if
        PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {
            int contador = 0;

            public void nextPacket(PcapPacket packet, String user) {

                System.out.printf("\n\nPaquete recibido el %s caplen=%-4d longitud=%-4d %s\n\n",
                        new Date(packet.getCaptureHeader().timestampInMillis()),
                        packet.getCaptureHeader().caplen(), // Length actually captured
                        packet.getCaptureHeader().wirelen(), // Original length
                        user // User supplied object
                );
                String Trama = "";
                for (int i = 0; i < packet.size(); i++) {
                    Trama = Trama + String.format("%02X ", packet.getUByte(i));
                    if (i % 16 == 15) {
                        //System.out.println("");
                        Trama = Trama + "\n";
                    }
                }//if
                System.out.println(Trama);

                int longitud = (packet.getUByte(12) * 256) + packet.getUByte(13);
                System.out.printf("\nLongitud: %d (%04X)", longitud, longitud);

                if (longitud < 1500) {
                    System.out.println("--->Trama IEEE802.3");
                    System.out.println("Direccion MACDestino");
                    String MACDestino = "";
                    for (int i = 0; i < 6; i++) {
                        MACDestino = MACDestino + String.format("%02X", packet.getUByte(i));
                        if (i % 16 == 15) {
                            System.out.println("");
                            MACDestino = MACDestino + "\n";
                        }
                    }
                    System.out.println(MACDestino);
                    System.out.println("Direccion MACOrigen");
                    String MACOrigen = "";
                    for (int i = 6; i < 12; i++) {
                        MACOrigen = MACOrigen + String.format("%02X", packet.getUByte(i));
                        if (i % 16 == 15) {
                            System.out.println("");
                        }
                    }
                    System.out.println(MACOrigen);
                    System.out.printf("\n |-->DSAP: %02X", packet.getUByte(14));
                    String DSAP = String.format("%02X", packet.getUByte(14));
                    int ssap = packet.getUByte(15) & 0x00000001;
                    String c_r = (ssap == 1) ? "Respuesta" : (ssap == 0) ? "Comando" : "Otro";
                    System.out.printf("\n |-->SSAP: %02X   %s", packet.getUByte(15), c_r);
                    String SSAP = String.format("%02X", packet.getUByte(15));
                    System.out.println("");
                    System.out.println("Longitud = " + longitud);
                    int dato = packet.getByte(16);
                    int datoax = packet.getByte(17);
                    String arreglo = "";
                    for (int i = 0; i < 8; i++) {
                        int tmp = dato;
                        tmp = tmp >> i;
                        int bit = tmp & 00000001;
                        String b = String.valueOf(bit);
                        arreglo = arreglo + b;
                    }
                    contador++;
                    hue.aumentar(0, 0, 0, 0, 0, 1);
                } else if (longitud >= 1500) {
                    System.out.println("-->Trama ETHERNET");
                    System.out.println("Direccion MACDestino");
                    String MACDestino = "";
                    for (int i = 0; i < 6; i++) {
                        MACDestino = MACDestino + String.format("%02X", packet.getUByte(i));
                        if (i % 16 == 15) {
                            System.out.println("");
                            MACDestino = MACDestino + "\n";
                        }
                    }
                    System.out.println(MACDestino);
                    System.out.println("Direccion MACOrigen");
                    String MACOrigen = "";
                    for (int i = 6; i < 12; i++) {
                        MACOrigen = MACOrigen + String.format("%02X", packet.getUByte(i));
                        if (i % 16 == 15) {
                            System.out.println("");
                        }
                    }
                    System.out.println(MACOrigen);
                    System.out.println("Tipo");
                    for (int i = 12; i < 14; i++) {
                        System.out.printf("%02X ", packet.getUByte(i));
                        if (i % 16 == 15) {
                            System.out.println("");
                        }
                    }
                    System.out.println("");
                    String tipo;
                    tipo = Integer.toHexString((int) packet.getByte(12));
                    tipo = tipo + Integer.toHexString((int) packet.getByte(13));
                    contador++;
                    Tcp tcp = new Tcp();
                    Udp udp = new Udp();
                    Icmp icmp = new Icmp();
                    Arp arp = new Arp();
                    if(packet.hasHeader(tcp)){
                        hue.aumentar(1, 0, 0, 0, 0, 0);
                    }else{
                        if(packet.hasHeader(udp)){
                            hue.aumentar(0, 1, 0, 0, 0, 0);
                        }else{
                            if(packet.hasHeader(icmp)){
                                hue.aumentar(0, 0, 1, 0, 0, 0);
                            }else{
                                if(packet.hasHeader(arp)){
                                    hue.aumentar(0, 0, 0, 0, 1, 0);
                                }else{
                                    hue.aumentar(0, 0, 0, 1, 0, 0);
                                }
                            }
                        }
                    }
                        
                    
                    
                }//else
            }
        };
        hue.ceros();
        pcap.loop(-1, jpacketHandler, " ");
        pcap.close();
        hue.terminar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        StringBuilder errbuf = new StringBuilder();
        String fname = "tests/test-afs.pcap";

        Pcap pcap = Pcap.openOffline(fname, errbuf);
        try {

            String ofile = "C:/Users/AndreAndyP/Desktop/hue.cap";
            PcapDumper dumper = pcap.dumpOpen(ofile);

            System.out.println(errbuf);
            PcapHandler<PcapDumper> dumpHandler = new PcapHandler<PcapDumper>() {
                @Override
                public void nextPacket(PcapDumper dumper, long seconds, int useconds,
                        int caplen, int len, ByteBuffer buffer) {

                    dumper.dump(seconds, useconds, caplen, len, buffer);
                }

            };
            pcap.loop(10, dumpHandler, dumper);

            File file = new File(ofile);
            System.out.printf("%s file has %d bytes in it!\n", ofile, file.length());
            dumper.close(); // Won't be able to delete without explicit close
            pcap.close();

            if (file.exists()) {
                file.delete(); // Cleanup
            }
        } catch (Exception e) {
            System.out.println(pcap.getErr());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Entrada;
    public static javax.swing.ButtonGroup Grupo;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
