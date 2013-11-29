package es.ual.PR.clienterest;
/*
 * ACtividad que realiza una llamada usando un web service SOAP
 * 
 * Autor: Victor Suarez Garcia
 * 
 * Fecha:07/11/2012
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import es.ual.PR.clienterest.atsk.AsyncSoapCall;

public class SoapActivity extends Activity {

	/**
	 * Cuadro de texto con el sumando 1
	 */
	private EditText texto1;
	/**
	 * Cuadro de texto con el sumando 2
	 */
	private EditText texto2;
	/**
	 * texto con el restultado
	 */
	private TextView txtresul;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.title_activity_soap);
		setContentView(R.layout.activity_main);
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Button boton = (Button)findViewById(R.id.btnsuma);
	        texto1= (EditText)findViewById(R.id.edtsuma1);
	        texto2= (EditText)findViewById(R.id.edtsuma2);
	        txtresul= (TextView) findViewById(R.id.txtresultado);
	        boton.setOnClickListener(new OnClickListener() {
				//Al hacer click llamamos al servicio web
				@Override
				public void onClick(View arg0) {
					//Creamos la llamada
					AsyncSoapCall call = new AsyncSoapCall(SoapActivity.this);
					//Obtenemos los parametros
					Integer suma1= Integer.valueOf(texto1.getText().toString());
					Integer suma2= Integer.valueOf(texto2.getText().toString());
					//Ejecutamos la llamada
					call.execute(suma1,suma2);
					Integer result=null;
					try {
						//Obtenemos el resultado con un timeout de 60 segundos
						result=call.get(60, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Escribimos el resultado
					txtresul.setText(result.toString());
					txtresul.setVisibility(View.VISIBLE);
				}
			});
	}

}
