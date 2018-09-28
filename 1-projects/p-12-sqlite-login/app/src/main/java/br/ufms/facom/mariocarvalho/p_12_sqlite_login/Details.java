package br.ufms.facom.mariocarvalho.p_12_sqlite_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    private EditText edtUser;
    private Button btnBack;
    private ListView lvContacts;
    private ArrayList<Contact> mListContacts;
    private DBHelper mDBHelper;
    private ArrayAdapter<Contact> mAdapter;
    private Contact mContact;
    private int ID_DELETE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        edtUser = findViewById(R.id.edtUser);
        lvContacts =  findViewById(R.id.lvContacts);
        mContact = new Contact();
        // Registrando  omenu de contexto
        registerForContextMenu(lvContacts);

        Bundle args = getIntent().getExtras();
        String user = args.getString("user_key");
        if(user != null){
            edtUser.setText(user);
        }else{
            Toast.makeText(this, "Erro ao acessar os argumentos!", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Clique simples na Lista
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Details.this, "Click Simples: Nome: "+mListContacts.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Details.this, Update.class);
                intent.putExtra("contact", mListContacts.get(position));
                startActivity(intent);
            }
        });
        //Clique Longo
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Details.this, "Click Longo: Nome: "+mListContacts.get(position).getName(), Toast.LENGTH_SHORT).show();
                mContact = mListContacts.get(position);
                return false;
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem mItemDelete = menu.add(Menu.NONE, ID_DELETE, 1 ,"Deletar");
        mItemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.e("TAG","Clicou excluír!");
                int ret = (int) mDBHelper.delete(mContact);
                if(ret > 0){
                    Toast.makeText(Details.this, "Sucesso ao excluir!", Toast.LENGTH_SHORT).show();
                    fillList();
                }else{
                    Toast.makeText(Details.this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDBHelper = new DBHelper(Details.this);
        fillList();
    }

    public  void fillList(){

        mListContacts = mDBHelper.getAll();

        if(mListContacts != null){
            mAdapter = new ArrayAdapter<>(
                    Details.this,
                    android.R.layout.simple_list_item_1,
                    mListContacts);
            // Setando o adaptador
            lvContacts.setAdapter(mAdapter);
        }
    }
}
