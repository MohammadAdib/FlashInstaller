package genius.mohammad.flashinstaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class FlashInstaller extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		ImageView iv = (ImageView) findViewById(R.id.imageView);
		iv.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		installHoloWeb();
		installFlash();
		//Finish
		finish();
	}
	
	public void installFlash() {
		InputStream in = getResources().openRawResource(R.raw.flashplayer);

		File sdcard = Environment.getExternalStorageDirectory();
		File dir = new File(sdcard.getAbsolutePath() + "/flash_installer");
		dir.mkdirs();
		
		File f = new File(dir.getAbsolutePath() + "/flashplayer.apk");

		try {
			FileOutputStream out = new FileOutputStream(f);
			byte[] buff = new byte[1024];
			int read = 0;
			while ((read = in.read(buff)) > 0) {
				out.write(buff, 0, read);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			Log.d("flashinstaller", "error: "+ e.getMessage());

			Log.d("flashinstaller", "flashdir=" + dir.getAbsolutePath());
		}
		
		//Install it
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	public void installHoloWeb() {
		InputStream in = getResources().openRawResource(R.raw.holoweb);

		File sdcard = Environment.getExternalStorageDirectory();
		File dir = new File(sdcard.getAbsolutePath() + "/flash_installer");
		dir.mkdirs();
		
		File f = new File(dir.getAbsolutePath() + "/holoweb.apk");

		try {
			FileOutputStream out = new FileOutputStream(f);
			byte[] buff = new byte[1024];
			int read = 0;
			while ((read = in.read(buff)) > 0) {
				out.write(buff, 0, read);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			Log.d("flashinstaller", "error: "+ e.getMessage());

			Log.d("flashinstaller", "holodir=" + dir.getAbsolutePath());
		}
		
		//Install it
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
