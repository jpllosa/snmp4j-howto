package example;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * 
 * @author Joel Patrick Llosa
 *
 */
public class SnmpTrapSender {

	public static void main(String[] args) throws Exception {
		TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
		transport.listen();

		CommunityTarget target = new CommunityTarget();
		target.setVersion(SnmpConstants.version1);
		target.setAddress(new UdpAddress("localhost/162"));

		PDUv1 pdu = new PDUv1();
		pdu.setType(PDU.V1TRAP);
		pdu.setGenericTrap(PDUv1.LINKDOWN);

		Snmp snmp = new Snmp(transport);
		snmp.send(pdu, target);
		System.out.println("trap sent...");
	}

}
