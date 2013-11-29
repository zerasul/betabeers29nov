package es.ual.PR.tweetxample;

import java.util.List;

import twitter4j.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Tweetadapter extends BaseAdapter {

	private List<Status> timeline;
	private Context contexto;
	public Tweetadapter(Context contexto,List<Status> timeline) {
		this.timeline=timeline;
		this.contexto=contexto;
	}
	public void actualizar(List<Status> timeline){
		this.timeline=timeline;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return timeline.size();
	}

	@Override
	public Object getItem(int arg0) {
		
		return timeline.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return timeline.get(arg0).getId();
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater inf = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Status status = timeline.get(arg0);
		if(arg1==null){
			arg1= inf.inflate(R.layout.tweet, null);
		}
		TextView txtautor =(TextView)arg1.findViewById(R.id.txtautor);
		txtautor.setText(status.getUser().getName());
		TextView txtmensaje = (TextView)arg1.findViewById(R.id.txttexto);
		txtmensaje.setText(status.getText());
		return arg1;
	}

}
