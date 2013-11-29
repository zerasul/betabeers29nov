package es.ual.PR.clienterest.atsk;
/*
 * Clase que realiza una llamada asincrona a un servicio web SOAP
 * 
 * Autor: Victor Suarez garcia
 * 
 * Fecha: 07/11/2012
 */
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import es.ual.PR.clienterest.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
/**
 * Clase que realiza una llamada a un web service SOAP
 * @author victor suarez Garcia
 * @version 0.0. version inicial
 */
public class AsyncSoapCall extends AsyncTask<Integer,Void,Integer> {
	/**
	 * URL del servicio web(SOlo valido desde el emulador de Android)
	 */
	private static final String URL="http://10.0.2.2:8080/ejemplosoap/services/Soapws";
	/**
	 * Namespace del web service
	 */
	private static final String NAMESPACE="http://ejemplosoap.PR.ual.es";
	/**
	 * Metodo a llamar
	 */
	private static final String METHOD_NAME="suma";
	/**
	 * Contexto de la aplicacion
	 */
	private Context contexto;
	/**
	 * Cuadro de dialogo para mostrar mientras se realiza la llamda
	 */
	private ProgressDialog dialogo;
	/**
	 * Constructor de la clase
	 * @param contexto contexto de la aplicacion
	 */
	public AsyncSoapCall(Context contexto) {
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
		Integer result = null;
		//Creamos un objeto para la peticion
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		//Añadimos los parametros
		PropertyInfo param1 = new PropertyInfo();
		param1.setName("a");
		param1.setValue(arg0[0]);
		PropertyInfo param2 = new PropertyInfo();
		param2.setName("b");
		param2.setValue(arg0[1]);
		request.addProperty(param1);
		request.addProperty(param2);
		//Creamos el mensaje a enviar
		SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
		env.setOutputSoapObject(request);
		//Cliente HTTP que usaremos
		HttpTransportSE http = new HttpTransportSE(URL);
		try {
			//LLamada al web service
			http.call(NAMESPACE+METHOD_NAME, env);
			//REcogida del resultado
			SoapPrimitive prim = (SoapPrimitive) env.getResponse();
			result = Integer.valueOf(prim.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
