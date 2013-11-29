package es.ual.PR.tweetxample;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class ConectaraTwitter extends Activity {
	public static Twitter twitter;
	private AccessToken token;
	private RequestToken rtoken;
	private static final String secrettoken="PZwgfiBIIoCkm3z2O8V3iUfPziZUCJitKan1lyZWw8";
	private static final String consumertoken="s599f0pJ0nCyJ7uOCJedg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conectartwitter);
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumertoken , secrettoken);
		rtoken=null;
		try{
		rtoken = twitter.getOAuthRequestToken();
		}catch (Exception e) {
			Log.e("ERROR DE LOGIN",e.getMessage());
		}
		WebView wview = (WebView)findViewById(R.id.wviewtwitt);
		wview.loadUrl(rtoken.getAuthenticationURL());
		Button btnentrar = (Button)findViewById(R.id.btnentrar);
		btnentrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText texto = (EditText)ConectaraTwitter.this.findViewById(R.id.edtpin);
				try{
				token= twitter.getOAuthAccessToken(rtoken, texto.getText().toString());
				
				}catch(Exception e){
					Log.e("ERROR EN CONEXION", e.getMessage());
				}
				Intent inte = new Intent(ConectaraTwitter.this, ListarTweets.class);
				startActivity(inte);
			}
		});
		
	}

	
}
