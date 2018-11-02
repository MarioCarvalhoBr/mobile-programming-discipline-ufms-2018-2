/*
 * Informações sobre a criação do arquivo.
 * Autor: Mário de Araújo Carvalho
 * E-mail: mariodearaujocarvalho@gmail.com
 * GitHub: https://github.com/MarioDeAraujoCarvalho
 * Ano: 13/5/2017
 * Entrar em contado para maiores informações.
 */

package br.ufms.facom.mariocarvalho.bookkeeping.view.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import br.ufms.facom.mariocarvalho.bookkeeping.util.Util;
import br.ufms.facom.mariocarvalho.bookkeeping.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_seta_esquerda_white);
        
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            w.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary, getTheme()));
        }else{
            //noinspection deprecation
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opcoes, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
				
                break;
            case R.id.nav_avalie_play_store:
                String url = getResources().getString(R.string.link_download_app_or_download_play_store);
                Util.abrirURL(SobreActivity.this,url);
                break;
            case R.id.nav_share:
                String texto = getResources().getString(R.string.text_share_app);

                Util.compartilhar(SobreActivity.this, texto);
                break;
            case R.id.nav_feedback:
                Util.enviarEmail(SobreActivity.this,getResources().getString(R.string.text_email_contact),getResources().getString(R.string.text_send_email_subject),"");
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
    }
}