package es.ual.PR.httpdexample;
/*
 * Servicio android, que inicia un servidor web
 * 
 * Autor: Victor Suarez Garcia
 * 
 * Fecha: 07/11/2012
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * Servicio que inicia un servidor web
 * @author victor suarez garcia
 * @version 0.0
 */
public class HttpdService extends Service {

	/**
	 * Servidor web
	 */
	private HttpServer server;
	/**
	 * Puerto por defecto 3454
	 */
	private static final int DEFAULTPORT=3454;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		//Creamos el servidor
		server=new HttpServer();
		//Iniciamos el servidor en un puerto concreto
		server.init(DEFAULTPORT);
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		try {
			//Paramos el servidor
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onDestroy();
	}

}
