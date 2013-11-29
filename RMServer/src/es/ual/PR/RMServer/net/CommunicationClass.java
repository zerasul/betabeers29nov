package es.ual.PR.RMServer.net;
/*
 * Clase de comunicacion usando UDP. Permite mandar y recibir 1 entero que será el identificador de la accion a realizar.
 * 
 * Autor: Victor Suarez Garcia
 * 
 * Fecha: 06/11/2012
 * 
 * 
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
/**
 * Clase que permite la comunicacion usando el protocolo UDP
 * @author victor suarez garcia
 * @version 0.0. version inicial
 */
public class CommunicationClass {
	/**
	 * Socket que se utilizará para comunicarse
	 */
	private DatagramSocket socket;
	/**
	 * Puerto por defecto
	 */
	private static final int DEFAULTPORT=1234;
	/**
	 * Constructor de la clase
	 * @throws SocketException salta esta excepcion si el socket de comunicacion
	 * no se ha podido abrir
	 */
	public CommunicationClass() throws SocketException {
		socket=new DatagramSocket(DEFAULTPORT);
	}
	/**
	 * COnstructor de la clase.
	 * @param port puerto a usar en la conexion
	 * @throws SocketExceptionsalta esta excepcion si el socket de comunicacion
	 * 
	 */
	public CommunicationClass(int port) throws SocketException{
		socket= new DatagramSocket(port);
	}
	/**
	 * ENvia un mensaje por el socket
	 * @param MSG mensaje a enviar
	 * @throws IOException Salta esta excepcion si hay un error en la comunicacion
	 */
	public void send(int MSG) throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE);
		buffer.putInt(MSG);
		DatagramPacket paquete = new DatagramPacket(buffer.array(), buffer.array().length);
		socket.send(paquete);
	}
	/**
	 * Metodo que recibe un mensaje. Este metodo es bloqueante por lo que el proceso
	 *  se queda esperando hasta que recibe la informacion
	 * @return Mensaje recibido
	 * @throws IOException Salta esta excepcion si hay un error en la comunicacion
	 */
	public int recieve() throws IOException{
		DatagramPacket paquete = new DatagramPacket(new byte[Integer.SIZE], Integer.SIZE);
		socket.receive(paquete);
		ByteBuffer buffer = ByteBuffer.wrap(paquete.getData());
		return buffer.getInt();
	}
}
