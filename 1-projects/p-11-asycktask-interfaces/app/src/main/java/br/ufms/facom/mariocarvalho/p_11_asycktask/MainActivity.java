package br.ufms.facom.mariocarvalho.p_11_asycktask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ITask{
    private Task mTask;
    private ImageView ivImage;
    private Button btnDownload;
    private ProgressDialog mDialog;
    private String mAdressImage = "https://developer.android.com/about/versions/oreo/images/o-hero.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivImage = findViewById(R.id.ivImage);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.txt_title_download), getResources().getString(R.string.txt_text_download));
                mTask = new Task(MainActivity.this, MainActivity.this, mDialog);
                mTask.execute(mAdressImage);
            }
        });
    }
    @Override
    public void afterDownload(Bitmap mBitmap) {
        ivImage.setImageBitmap(mBitmap);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Destruindo objetos criado
        if(mDialog != null){
            mDialog.dismiss();
            mDialog = null;
        }
        if (mTask != null){
            mTask.cancel(true);
        }
    }
}
