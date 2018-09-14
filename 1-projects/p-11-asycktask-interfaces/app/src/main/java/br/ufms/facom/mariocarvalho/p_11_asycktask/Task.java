package br.ufms.facom.mariocarvalho.p_11_asycktask;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task extends AsyncTask<String, Void, Bitmap>{
    private Context mContext;
    private ITask mInterfaceTask;
    private ProgressDialog mDialog;

    public Task(Context mContext, ITask mInterfaceTask, ProgressDialog mDialog) {
        this.mContext = mContext;
        this.mInterfaceTask = mInterfaceTask;
        this.mDialog = mDialog;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        Log.i("onPreExecute", "Iniciando a Thread: " +
                Thread.currentThread().getName());
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        try{
            Log.i("doInBackground", "Download da imagem na Thread: " +
                    Thread.currentThread().getName());
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            InputStream stream = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(stream);
        }catch (IOException e){
            Log.i("doInBackground", e.getMessage());
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        if(bitmap!=null) {
            Log.i("onPostExecute", "Passando o Bitmap da Thread para a Activity: " +
                    Thread.currentThread().getName());
            mInterfaceTask.afterDownload(bitmap);
        }else{
            Log.i("onPostExecute", "Erro durante o Download da imagem: " +
                    Thread.currentThread().getName());
            mInterfaceTask.afterDownload(null);
        }
        Log.i("onPostExecute", "Destruindo o ProgressDialog da Thread/Activity: " +
                Thread.currentThread().getName());
        mDialog.dismiss();
    }
}