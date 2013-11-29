package es.ual.PR.RMServer.comm;
/*
 * Clase que se encarga de la comunicacion con el servidor
 * Autor: Victor Suarez garcia
 * Fecha: 07/11/2012
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
/**
 * Clase encargada de la comunicacion con el servidor
 * @author victor suarez Garcia
 * @version 0.0. version inicial
 */
public class CommunicationClass {
	/**
	 * Socket de comunicacion
	 */
	private DatagramSocket socket;
	/**
	 * Direccion a comunicarse
	 */
	private String ip;
	/**
	 * Puerto por defecto
	 */
	private static final int DEFAULTPORT=1234;
	/**
	 * Constructor de la clase
	 * @param address direccion a conectarse
	 * @param port puerto a conectarse
	 * @throws SocketException excepcion que salta si hay un error en la comunicacion
	 * @throws UnknownHostException excepcion que salta si no se puede conectar al host
	 */
	public CommunicationClass(String address,int port) throws SocketException, UnknownHostException {
		socket= new DatagramSocket(port);
		this.ip=address;
		
	}
	/**
	 * Envia un mensaje al servidor
	 * @param msg mensaje a enviar
	 * @throws IOException sala esta excepcion si hay un error con la comunicacion.
	 */
	public void send(int msg) throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE);
		buffer.putInt(msg);
		DatagramPacket paquete = new DatagramPacket(buffer.array(), buffer.array().length);
		paquete.setAddress(InetAddress.getByName(ip));
		paquete.setPort(DEFAULTPORT);
		socket.send(paquete);
	}
}
