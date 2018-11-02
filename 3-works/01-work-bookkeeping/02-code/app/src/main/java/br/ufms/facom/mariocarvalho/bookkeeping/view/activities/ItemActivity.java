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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;

import br.ufms.facom.mariocarvalho.bookkeeping.dao.item.ItemDAO;
import br.ufms.facom.mariocarvalho.bookkeeping.model.Item;
import br.ufms.facom.mariocarvalho.bookkeeping.R;

public class ItemActivity extends AppCompatActivity {

    private EditText txtNome, txtDescricao;

    private ItemDAO itemDAO;
    private Item item;

    private int codigo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
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

        codigo = getIntent().getExtras().getInt("codigo", -1);

        itemDAO = new ItemDAO(this);

        initializeFilds();
        fillFilds();

    }


    public void initializeFilds(){
        txtNome = (EditText) findViewById(R.id.txt_nome);
        txtDescricao = (EditText)findViewById(R.id.txt_descricao);
    }

    public void fillFilds(){
        if(codigo != -1){
            itemDAO.open();
            item = itemDAO.getItem(codigo);
            itemDAO.close();

            txtNome.setText(item.getNome());
            txtDescricao.setText(item.getDescricao());
        }else{
            item = new Item();
        }
    }

    public boolean save(){
        item.setId(codigo);

        if(!txtNome.getText().toString().equals("")){
            item.setNome(txtNome.getText().toString());
        }else{
            mostrarAlertaBuilder(getResources().getString(R.string.text_aviso_campo_obrigatorio)+" nome do item!");
            txtNome.requestFocus();
            return false;
        }

        if(!txtDescricao.getText().toString().equals("")){
            item.setDescricao(txtDescricao.getText().toString());
        }else{
            mostrarAlertaBuilder(getResources().getString(R.string.text_aviso_campo_obrigatorio)+" descrição!");
            txtDescricao.requestFocus();
            return false;
        }

        itemDAO.open();
        if(codigo == -1){
            itemDAO.insert(item);
        }else{
            itemDAO.update(item, item.getId());
        }
        itemDAO.close();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_adicionar_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
			overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
        }else if(item.getItemId() == R.id.action_adicionar){
            if(save()){
                finish();
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarAlertaBuilder(String sms) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(sms);
        dialog.setNeutralButton("OK",null);
        dialog.create().show();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit);
    }

}
