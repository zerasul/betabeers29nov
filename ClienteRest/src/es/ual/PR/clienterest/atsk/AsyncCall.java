package es.ual.PR.clienterest.atsk;
/*
 * Clase que realiza una tarea asincrona:
 * Permite hacer una llamada a un web service usando REST.
 * 
 * autor: Victor Suarez garcia
 * 
 * FEcha: 07/11/2012
 */
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import es.ual.PR.clienterest.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
/**
 * Permite hacer una llamada a un web service con REST
 * @author victor suarez Garcia
 * @version 0.0. version inicial
 *
 */
public class AsyncCall extends AsyncTask<Integer,Void,Integer> {
	/**
	 * Contexto a usar para mostrar los mensajes
	 */
	private Context contexto;
	/**
	 * Cuadro de dialogo para mostrar mientras se comunica con el servidor
	 */
	private ProgressDialog dialogo;
	/**
	 * Constructor de la clase
	 * @param contexto contexto de la aplicacion
	 */
	public AsyncCall(Context contexto) {
		this.contexto=contexto;
	}
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Integer result) {
		
		dialogo.hide();
	}
	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		
		dialogo=ProgressDialog.show(contexto, contexto.getString(R.string.cargando), contexto.getString(R.string.cargando));
	}

	@Override
	protected Integer doInBackground(Integer... arg0) {
		Integer resultado=null;
		Integer sumando1= arg0[0];
		Integer sumando2= arg0[1];
		//URL del servicio
		String url="http://10.0.2.2:8080/ejemplorest/rest/suma/"+sumando1+"/"+sumando2+"/";
		//Cliente HTTP
		HttpClient cliente = new DefaultHttpClient();
		//Peticion http
		HttpGet request = new HttpGet(url);
		ResponseHandler<String> handler= new BasicResponseHandler();
		try {
			//LLamada al servicio web
			resultado = Integer.valueOf(cliente.execute(request,handler));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Cierre de la conexion
		cliente.getConnectionManager().shutdown();
		return resultado;
	}

}
