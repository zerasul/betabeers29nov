package es.ual.PR.httpdexample;

/*
 * Actividad principal del programa: En este caso, se trata de la actividad que permite iniciar
 *  o parar el servidor web.
 *  
 *  Autor: Victor Suarez garcia
 *  
 *  Fecha: 07/11/2012
 */
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Actividad que permite iniciar o parar el servicio del servidor web
 * @author victor suarez garcia
 *
 * @version 0.0.
 */
public class MainActivity extends Activity {
	/**
	 * Indica si el servidor esta iniciado o no
	 */
	private Boolean iniciado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciado=(Boolean) getLastNonConfigurationInstance();
        if(iniciado==null){
        	iniciado=false;
        }
        Button iniciar = (Button) findViewById(R.id.btniniciar);
        iniciar.setOnClickListener(new btnlistener());
        Button torrent = (Button)findViewById(R.id.btntorrent);
        torrent.setOnClickListener(new btnlistenertorrent());
    }

    @Override
	public Object getLastNonConfigurationInstance() {
		return super.getLastNonConfigurationInstance();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		return iniciado;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    private class btnlistener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent inte = new Intent(MainActivity.this, HttpdService.class);
			//Si el servicio esta parado se inicia si no se para
			if(!iniciado){
			startService(inte);
			iniciado=true;
			Button boton= (Button)v;
			boton.setText(MainActivity.this.getString(R.string.stop));
			}
			else{
			stopService(inte);
			iniciado=false;
			Button boton= (Button)v;
			boton.setText(MainActivity.this.getString(R.string.start));
			}
		}
    	
    }
    private class btnlistenertorrent implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent inte = new Intent(MainActivity.this, TorrentService.class);
			inte.putExtra("torrentpath", Environment.getExternalStorageDirectory().getAbsolutePath()+"/mytorrent2.torrent");
			startService(inte);
		}
    	
    }
}
