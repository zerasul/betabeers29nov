package es.ual.PR.RMServer.robot;
/*
 * Clase que permite realizar el control remoto del equipo; permite realizar algunas
 * acciones.
 * 
 * Autor: Victor Suarez Garcia
 * 
 * Fecha: 06/11/2012
 */
import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.Robot;
import java.awt.event.KeyEvent;
/**
 * Clase que realiza el control remoto permitiendo hacer algunas acciones
 * @author victor suarez Garcia
 * @version 0.0. version inicial
 */
public class ActionClass {

	
	/**
	 * Objeto robot que nos permitira controlar el equipo remotamente
	 */
	private Robot robot;
	public ActionClass() throws AWTException {
	 robot = new Robot();
	}
	/**
	 * realiza una accion en funcion del parametro pasado
	 * @param accion accion a realizar; los valores son los siguientes: <ul>
	 * 
	 * <li>0.- AVPAG</li>
	 * <li>1.- REPAG </li>
	 * <li>2.- LEFT </li>
	 * <li>3.- RIGHT</li> 
	 */
	public void makeAction(Integer accion){
		switch (accion) {
		case 0:
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			System.out.println("AVPAG pulsado");
			break;
		case 1:
			robot.keyPress(KeyEvent.VK_PAGE_UP);
			System.out.println("REPAG pulsado");
			break;
		case 2:
			robot.keyPress(KeyEvent.VK_LEFT);
			System.out.println("LEFT pulsado");
			break;
		case 3:
			robot.keyPress(KeyEvent.VK_RIGHT);
			System.out.println("RIGHT pulsado");
			break;
		default:
			System.out.println("Error");
		break;
		}
	}
}
