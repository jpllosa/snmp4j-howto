package example;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class SnmpTrapListener {

	public static void main(String[] args) throws Exception {
		Address address = GenericAddress.parse("udp:127.0.0.1/162");
		TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping((UdpAddress) address);
		transport.listen();

		CommandResponder trapPrinter = new CommandResponder() {
			public synchronized void processPdu(CommandResponderEvent e) {
				PDU command = e.getPDU();
				if (command != null) {
					System.out.println("snmp trap received: " + command.toString());
				}
			}
		};

		Snmp snmp = new Snmp(transport);
		snmp.addCommandResponder(trapPrinter);
		while (true) {
			System.out.println("listening... Ctrl-C to stop");
			Thread.sleep(3000);
		}
	}

}
