package es.ual.PR.tweetxample.atsk;
/*
 * Permite mandar un tweet asyncronamente.
 * 
 * Autor: Victor Suarez Garcia
 * 
 * Fecha: 08/11/2012
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import twitter4j.Twitter;

/**
 * Tarea asincrona que permite mandar un tweet asyncronamente
 * @author victor suarez garcia
 * 
 * @version 0.0
 *
 */
public class asynctweet extends android.os.AsyncTask<String,Void,Boolean> {
	/**
	 * Objeto twitter para poder utilizarlo
	 */
	private Twitter twitter;
	/**
	 * Contexto de la aplicacion
	 */
	private Context contexto;
	/**
	 * Cuadro de dialogo para mostrar
	 */
	private ProgressDialog dialogo;
	public asynctweet(Twitter twitter,Context contexto) {
		this.contexto=contexto;
		this.twitter=twitter;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		dialogo.hide();
	}

	@Override
	protected void onPreExecute() {
		dialogo = ProgressDialog.show(contexto, "Cargando", "Cargando...");
		dialogo.show();
	}

	@Override
	protected Boolean doInBackground(String... arg0) {
		//REcibimos el mensaje por parametro
		String mensaje = arg0[0];
		Boolean res=true;
		try{
			//mandamos el mensaje a twitter
		twitter.updateStatus(mensaje);
		}catch (Exception e) {
			Log.e("ERROR TWITT", e.getMessage());
			res=false;
		}
		return res;
	}

}
