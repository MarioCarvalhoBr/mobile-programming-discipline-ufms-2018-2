package br.ufms.facom.mariocarvalho.bookkeeping.view.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import br.ufms.facom.mariocarvalho.bookkeeping.R;
import br.ufms.facom.mariocarvalho.bookkeeping.dao.item.ItemDAO;
import br.ufms.facom.mariocarvalho.bookkeeping.model.Item;
import br.ufms.facom.mariocarvalho.bookkeeping.util.Util;

public class DetalhesItemActivity extends AppCompatActivity {

    private ItemDAO mItemDAO;
    private Item mItem;

    private TextView mTxtNome, mTxtDescricao;

    private FloatingActionButton fab;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_item);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.title_activity_details_item));
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_seta_esquerda_white);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        setSupportActionBar(mToolbar);

        mTxtNome = (TextView) findViewById(R.id.txt_nome);
        mTxtDescricao = (TextView) findViewById(R.id.txt_descricao);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilhar(mItem);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Instancia os objetos utilizados
        mItemDAO = new ItemDAO(DetalhesItemActivity.this);
        mItem = new Item();
        //Recebe o código pelo Dundle
        Bundle mDados = getIntent().getExtras();
        String id = mDados.getString("codigo");
        //Carrega os dados
        carrgarDados(id);
    }

    public void carrgarDados(String id) {
        mItemDAO.open();
        mItem = mItemDAO.getItem(Integer.valueOf(id));
        mItemDAO.close();

        mTxtNome.setText(mItem.getNome());
        mTxtDescricao.setText(mItem.getDescricao());
    }


    public void compartilhar(Item item){
        String texto = "Olá Pessoal,\n" +
                "Gostaria de Comparlihar esse item: \n" +
                "\nNome: "+ item.getNome()+
                "\nDescrição: "+ item.getDescricao()+
                "\n\n\n--\nEnviado de Fast Framework";

        Util.compartilhar(DetalhesItemActivity.this, texto);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_itens_detalhes, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_menu_deletar:
                deletar();
                break;
            case R.id.item_menu_editar:
                Util.startIntentParseOneValue(DetalhesItemActivity.this, new ItemActivity(),"codigo",(mItem.getId()));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
        return true;
    }

    private void deletar() {
        AlertDialog.Builder janela = new AlertDialog.Builder(DetalhesItemActivity.this);
        janela.setTitle(getResources().getString(R.string.text_aviso_title));
        janela.setMessage(getResources().getString(R.string.text_aviso_excluir_item));
        janela.setNeutralButton(getResources().getString(R.string.text_aviso_opcao_nao),null);
        janela.setPositiveButton(getResources().getString(R.string.text_aviso_opcao_sim),new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ItemDAO mItemDAO = new ItemDAO(DetalhesItemActivity.this);
                mItemDAO.open();
                mItemDAO.delete(mItem.getId());
                mItemDAO.close();

                dialog.dismiss();
                finish();
            }
        });
        janela.create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
