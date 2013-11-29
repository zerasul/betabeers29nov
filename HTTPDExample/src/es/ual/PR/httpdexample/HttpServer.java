package es.ual.PR.httpdexample;
/*
 * Clase que inicia un servidor web para mostrar una sencilla pagina web
 * 
 * Autor: Victor suarez garcia
 * 
 * Fecha: 07/11/2012
 */
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
/**
 * Clase que inicia un servidor web. Basado en la libreria Restlet
 * @author victor suarez Garcia
 *
 * @version 0.0. version inicial
 */
public class HttpServer extends ServerResource {
	/**
	 * Servidor web
	 */
	private Server server;
	/**
	 * Inicializa el servidor web en un puerto concreto
	 * @param port puerto a iniciar el servidor web
	 */
	public void init(int port){
		server= new Server(Protocol.HTTP,port, HttpServer.class);
	}
	/**
	 * Inicia el servidor
	 * @throws Exception
	 */
	public void start() throws Exception{
		server.start();
	}
	/**
	 * Para el servidor
	 * @throws Exception
	 */
	public void stop() throws Exception{
		server.stop();
	}
	/**
	 * Metodo que devuelve la informacion a la peticion GET de HTTP
	 * @return mensaje de bienvenida
	 */
	@Get
	public String doget(){
		String html= "Bienvenidos a Betabeers";
		return html;
	}
}
