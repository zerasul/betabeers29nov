package es.ual.PR.tweetxample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vertweets extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertweets);
        Button entrar = (Button) findViewById(R.id.btnentrar);
        entrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent inte = new Intent(Vertweets.this, ConectaraTwitter.class);
				startActivity(inte);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_vertweets, menu);
        return true;
    }
}
