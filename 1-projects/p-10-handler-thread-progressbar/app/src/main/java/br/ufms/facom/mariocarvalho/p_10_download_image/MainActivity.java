package br.ufms.facom.mariocarvalho.p_10_download_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView ivImage;
    private Button btnDownload;
    private ProgressBar pbProgress;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = findViewById(R.id.ivImage);
        btnDownload = findViewById(R.id.btnDownload);
        pbProgress = findViewById(R.id.pbProgress);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    int value;
                    public void run(){
                        try
                        {
                            Log.i("onPostExecute", "Iniciando o Download da imagem...");
                            URL mUrl = new URL("https://developer.android.com/static/images/home/android-p-clear-bg-with-shadow-@1x.png");
                            HttpURLConnection mConnection;
                            mConnection = (HttpURLConnection) mUrl.openConnection();
                            mConnection.setDoInput(true);
                            mConnection.connect();

                            InputStream mStream = mConnection.getInputStream();
                            final Bitmap mBitmap = BitmapFactory.decodeStream(mStream);
                            for (int i = 5; i <= 100; i = i + 5){
                                value = i;
                                Thread.sleep(100);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbProgress.setProgress(value);
                                    }
                                });
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ivImage.setImageBitmap(mBitmap);
                                }
                            });
                            mConnection.disconnect();
                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            });
    }
}
