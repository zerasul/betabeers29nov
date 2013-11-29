package es.ual.PR.RMServer;
/*
 * Clase principal del programa, es la encargada de iniciar el servidor, y 
 * realizar las operaciones entre el servidor y la accion
 *  Autor: Victor Suarez garcia
 *  
 *  Fecha:07/11/2012
 */
import java.awt.AWTException;
import java.io.IOException;
import java.net.SocketException;

import es.ual.PR.RMServer.net.CommunicationClass;
import es.ual.PR.RMServer.robot.ActionClass;
/**
 * Clase servidor; inicia el servidor y obtiene las acciones
 * @author victor suarez garcia
 * 
 * @version 0.0. version inciial
 *
 */
public class Server {

	public static void main(String[] args){
		System.out.println("iniciando servidor...");
		
		try {
			CommunicationClass comm = new CommunicationClass();
			System.out.println("Iniciando robot");
			ActionClass act = new ActionClass();
			while(true){
				Integer msg = comm.recieve();
				act.makeAction(msg);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
