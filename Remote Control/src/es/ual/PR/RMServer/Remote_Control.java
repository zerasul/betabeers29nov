package es.ual.PR.RMServer;
/*
 * Actividad que realiza la interaccion con el usuario y llama a la clase de comunicacion
 * 
 * Autor: Victor Suarez garcia
 * 
 * Fecha: 07/11/2012
 */
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import es.ual.PR.RMServer.comm.CommunicationClass;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Actividad que realiza la interaccion con el usuario y llama a la clase de
 * comunicacion para enviar los datos
 * @author victor suarez garcia
 * @version 0.0. version inicial
 *
 */
public class Remote_Control extends Activity {
	/**
	 * Clase de comunicacion
	 */
	private CommunicationClass comm;
	/**
	 * Campo de texto para la IP
	 */
	private EditText ip;
	/**
	 * Puerto por defecto
	 */
	private static final int defaultport = 1234;
	/**
	 * Mensaje de siguiente pagina
	 */
	private static final int AVPAG = 0;
	/**
	 * Mensaje de pagina anterior
	 */
	private static final int REPAG = 1;
	/**
	 * Mensaje de izquierda
	 */
	private static final int LEFT = 2;
	/**
	 * Mensaje de derecha
	 */
	private static final int RIGHT = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote__control);
        comm=(CommunicationClass) getLastNonConfigurationInstance();
        ip= (EditText) findViewById(R.id.txtip);
        Button btnconectar = (Button) findViewById(R.id.btnconectar);
        btnconectar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					//INiciamos la clase de comunicacion con la ip puesta en el cuadro de texto
					comm=new CommunicationClass(ip.getText().toString(),defaultport);
				} catch (SocketException e) {
					Log.e("ERROR", e.getMessage());
					Toast.makeText(Remote_Control.this, R.string.errorconexion, Toast.LENGTH_SHORT).show();
				} catch (UnknownHostException e) {
					Log.e("Error", e.getMessage());
					Toast.makeText(Remote_Control.this, R.string.hostnoencontrado, Toast.LENGTH_SHORT).show();
				}
				
			}
		});
        Button btnavpag = (Button)findViewById(R.id.btnavpag);
        btnavpag.setOnClickListener(new btnlistener(AVPAG));
        Button btnrepag = (Button)findViewById(R.id.btnrepag);
        btnrepag.setOnClickListener(new btnlistener(REPAG));
        Button btnleft = (Button)findViewById(R.id.btnleft);
        btnleft.setOnClickListener(new btnlistener(LEFT));
        Button btnright = (Button)findViewById(R.id.btnrigth);
        btnright.setOnClickListener(new btnlistener(RIGHT));
    }

    @Override
	public Object getLastNonConfigurationInstance() {
		return super.getLastNonConfigurationInstance();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		
		return comm;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_remote__control, menu);
        return true;
    }
    private class btnlistener implements OnClickListener{

    	private int msg;
    	public btnlistener(int msg) {
    		this.msg=msg;
    	}
    	@Override
    	public void onClick(View v) {
    		//Si no esta creada la comunicacion mostraremos un error
    		if(Remote_Control.this.comm==null){
    			Toast.makeText(Remote_Control.this, R.string.errrornoconectado, Toast.LENGTH_SHORT).show();
    		}else{
    			try {
    				//Mandamos el mensaje al servidor
					comm.send(msg);
				} catch (IOException e) {
					Log.e("IOERROR", e.getMessage());
					Toast.makeText(Remote_Control.this, R.string.errorconexion, Toast.LENGTH_SHORT).show();
				}
    		}
    		
    	}
    	
    }
}

