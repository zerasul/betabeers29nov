package es.ual.PR.tweetxample;

import java.util.concurrent.TimeUnit;

import es.ual.PR.tweetxample.atsk.asynctweet;
import twitter4j.Twitter;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
public class ListarTweets extends  Activity{

	private Twitter twitter;
	private Tweetadapter adapter;
	ListView listado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listartweets);
		twitter = ConectaraTwitter.twitter;
		listado = (ListView)findViewById(R.id.listView1);
		try{
		adapter =new Tweetadapter(this,twitter.getHomeTimeline());
		}catch (Exception e) {
			Log.e("adapter error", e.getMessage());
		}
		listado.setAdapter(adapter);
		Button boton = (Button)findViewById(R.id.button1);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				asynctweet tweet = new asynctweet(twitter, ListarTweets.this);
				EditText edit = (EditText)findViewById(R.id.editText1);
				tweet.execute(edit.getText().toString());
				
				Boolean res=false;
				try {
					res=tweet.get(30, TimeUnit.SECONDS);
				} catch (Exception e) {
					Log.e("ERROR TWEET", e.getMessage());
				}
				try{
				if(res){
					adapter.actualizar(twitter.getHomeTimeline());
					adapter.notifyDataSetChanged();
				}
				}catch (Exception e) {
					Log.e("ERROR ACTUALIZANDO",e.getMessage());
				}
					
			}
		});
	}

	
}
