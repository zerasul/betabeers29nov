package es.ual.PR.httpdexample;

import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import jBittorrentAPI.Constants;
import jBittorrentAPI.DownloadManager;
import jBittorrentAPI.TorrentFile;
import jBittorrentAPI.TorrentProcessor;
import jBittorrentAPI.Utils;

public class TorrentService extends Service{

	private DownloadManager torrentmanager;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		String path=intent.getStringExtra("torrentpath");
		TorrentProcessor pr = new TorrentProcessor();
		TorrentFile torrent = pr.getTorrentFile(pr.parseTorrent(new File(path)));
		torrent.saveAs=Environment.getExternalStorageDirectory()+"/prueba.mp4";
		Constants.SAVEPATH=Environment.getExternalStorageDirectory().getAbsolutePath()+"/life-online/";
		torrentmanager= new DownloadManager(torrent, Utils.generateID());
		torrentmanager.startListening(6881, 6889);
		torrentmanager.startTrackerUpdate();
		torrentmanager.blockUntilCompletion();
		
	}
	
}
