package br.ufms.facom.mariocarvalho.p_6_list_view;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfCities extends AppCompatActivity {
    private TextView tvName, tvCity;
    private Button btnSelectDog;
    private ListView lvCities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lis_of_cities);

        tvName = findViewById(R.id.tvName);
        tvCity = findViewById(R.id.tvCity);
        btnSelectDog =  findViewById(R.id.btnSelectDog);
        lvCities =  findViewById(R.id.lvCities);

        Bundle b = getIntent().getExtras();
        if(b != null){
            tvName.setText(b.getString("key_name"));
        }else{
            Toast.makeText(this, "Erro na passagem de parâmetros!!!", Toast.LENGTH_SHORT).show();
        }
        btnSelectDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListOfCities.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        // Criando os recursos, array e adapter
        Resources mResources = getResources();
        final String[] mListCities = mResources.getStringArray(R.array.array_cities);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<>(
                ListOfCities.this,
                android.R.layout.simple_list_item_checked,
                android.R.id.text1,
                mListCities);
        // Setando o adaptador
        lvCities.setAdapter(mAdapter);
        //Clique simples na Lista
        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tvCity.setText(mListCities[position]);
                Toast.makeText(ListOfCities.this, "Click Simples: Cidade: "+mListCities[position], Toast.LENGTH_SHORT).show();
            }
        });
        //Clique Longo
        lvCities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ListOfCities.this, "Click Longo: Cidade: "+mListCities[position], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        


    }
}
