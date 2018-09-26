package br.ufms.facom.mariocarvalho.p_12_sqlite_login;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ListView lvCities;
    private ArrayList<Contact> mListContacts;
    private DBHelper mDBHelper;
    private ArrayAdapter<Contact> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        edtUser = findViewById(R.id.edtUser);
        lvCities =  findViewById(R.id.lvContacts);

        Bundle args = getIntent().getExtras();
        String user = args.getString("user_key");
        if(user != null){
            edtUser.setText(user);
        }else{
            Toast.makeText(this, "Erro ao acessar os argumentos!", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Clique simples na Lista
        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Details.this, "Click Simples: Nome: "+mListContacts.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //Clique Longo
        lvCities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Details.this, "Click Longo: Nome: "+mListContacts.get(position).getName(), Toast.LENGTH_SHORT).show();
                return true;
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
    protected void onResume() {
        super.onResume();
        fillList();
    }

    public  void fillList(){
        mDBHelper = new DBHelper(Details.this);
        mListContacts = mDBHelper.getAll();

        if(mListContacts != null){
            mAdapter = new ArrayAdapter<>(
                    Details.this,
                    android.R.layout.simple_list_item_checked,
                    android.R.id.text1,
                    mListContacts);
            // Setando o adaptador
            lvCities.setAdapter(mAdapter);
        }
    }
}
