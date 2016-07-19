# SNMP4J: How to send and receive traps
How to use the SNMP4J API for send and receiving traps by Joel Patrick Llosa

Developed in Windows 7 but should work on other OSs.

To use:
1. Open a command prompt and start the `SnmpTrapListener` to start listening for
   SNMP traps.  Prints out the trap details when received.

2. Open another command prompt and run `SnmpTrapSender` to send traps.
