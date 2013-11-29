package es.ual.PR.clienterest;
/*
 * ACtividad principal del programa: Realiza la llamada a un servicio web REST
 * 
 * Autor: Victor Suarez Garcia
 * 
 * 
 * Fecha: 07/11/2012
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import es.ual.PR.clienterest.atsk.AsyncCall;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Actividad que realiza una llamada a un servicio web REST
 * @author victor suarez Garcia
 *
 */
public class MainActivity extends Activity {
	/**
	 * Cuadro de texto con el sumando 1
	 */
	private EditText texto1;
	/**
	 * Cuadro de texto con el sumando 2
	 */
	private EditText texto2;
	/**
	 * Etiqueta con el resultado
	 */
	private TextView txtresul;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button)findViewById(R.id.btnsuma);
        texto1= (EditText)findViewById(R.id.edtsuma1);
        texto2= (EditText)findViewById(R.id.edtsuma2);
        txtresul= (TextView) findViewById(R.id.txtresultado);
        boton.setOnClickListener(new OnClickListener() {
			//Cuando se pulse el boton se realizara la llamada
			@Override
			public void onClick(View arg0) {
				//creamos un nuevo objeto para realizar la llamada asincrona
				AsyncCall call = new AsyncCall(MainActivity.this);
				//Obtenermos los sumandos
				Integer suma1= Integer.valueOf(texto1.getText().toString());
				Integer suma2= Integer.valueOf(texto2.getText().toString());
				//Ejecutamso la llamada
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
