package com.kms.mobilemousepad.network.wifi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Broadcaster {
	private final float BROADCAST_TERM_SEC = 1f;
	private final int MAX_WAITING_CNT = 10;
	
	private int broadcastPort;
	private DatagramSocket broadcastSocket;
	private final DatagramPacket PACKET = new DatagramPacket(new byte[4], 4);
	
	public void setPort(int port) {
		this.broadcastPort = port;
		PACKET.setPort(port);
	}
	
	public void broadcast() throws Exception {
		List<InetAddress> broadcastAddresses = createBroadcastAddresses();
		if(broadcastAddresses.isEmpty())	throw new Exception();
		
		broadcastSocket = new DatagramSocket(broadcastPort+1);
		broadcastSocket.setBroadcast(true);
		broadcastSocket.setSoTimeout((int)(BROADCAST_TERM_SEC * 1000));
		
		for(int cnt=0; cnt<MAX_WAITING_CNT; cnt++) {
			for(InetAddress broadcastAddr : broadcastAddresses) {
				PACKET.setAddress(broadcastAddr);
				broadcastSocket.send(PACKET);
			}
			try {
				broadcastSocket.receive(PACKET);
				closeBroadcastSocket();
				return;
			} catch(SocketTimeoutException e) {}
		}
		
		closeBroadcastSocket();
		throw new Exception();
	}
	
	public void closeBroadcastSocket() {
		if(broadcastSocket!=null && !broadcastSocket.isClosed())	broadcastSocket.close();
		broadcastSocket = null;
	}
	
	private List<InetAddress> createBroadcastAddresses() throws Exception {
		List<InetAddress> broadcastAddresses = new ArrayList<>();
		InetAddress broadcastAddr = null;
		
		try {
			broadcastAddr = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getInterfaceAddresses().get(0).getBroadcast();
		} catch(UnknownHostException e) { broadcastAddr = null; }
		
		if(broadcastAddr != null && !broadcastAddr.isLoopbackAddress())	broadcastAddresses.add(broadcastAddr);
		else {
			int prefixLength = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getInterfaceAddresses().get(0).getNetworkPrefixLength();
			
			byte[] addr = InetAddress.getLocalHost().getAddress();
			byte[] addingAddr = calculateNextAddr(createPrefixAddr(addr, prefixLength));
			int packetCnt = (int)Math.pow(2, addingAddr.length*8-prefixLength) - 2;
			
			for(int i=1; i<=packetCnt; i++) {
				addingAddr = calculateNextAddr(addingAddr);
				broadcastAddresses.add(InetAddress.getByAddress(addingAddr));
			}	
		}
		
		return broadcastAddresses;
	}
	
	private byte[] createPrefixAddr(byte[] addr, int prefixLength) {
		byte[] prefixAddr = new byte[addr.length];
		byte[] subnetMask = new byte[addr.length];
		
		for(int i=0; i<addr.length; i++) {
			subnetMask[i] = 0;
			for(int j=1; j<=8; j++) {
				if(i*8+j <= prefixLength) {
					subnetMask[i] <<= 1;
					subnetMask[i] += 1;
				} else	subnetMask[i] <<= 1;
			}
		}
		
		for(int i=0; i<addr.length; i++)	prefixAddr[i] = (byte)(addr[i] & subnetMask[i]);
		
		return prefixAddr;
	}
	
	private byte[] calculateNextAddr(byte[] before) {
		byte[] nextAddr = new byte[before.length];
		nextAddr[before.length-1] = (byte)(before[before.length-1] + 1);
		boolean flag = nextAddr[before.length-1] == 0;
		for(int i=before.length-2; i>=0; i--) {
			nextAddr[i] = flag ? (byte)(before[i]+1) : before[i];
			flag = nextAddr[i] == 0;
		}
		
		return nextAddr;
	}
}
