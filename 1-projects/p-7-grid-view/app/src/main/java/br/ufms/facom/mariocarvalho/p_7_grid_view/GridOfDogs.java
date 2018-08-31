package br.ufms.facom.mariocarvalho.p_7_grid_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.TextView;

public class GridOfDogs extends AppCompatActivity {
    private TextView tvName;
    private GridView gvDogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_of_dogs);

        tvName = findViewById(R.id.tvName);
        gvDogs =  findViewById(R.id.gvDogs);

        Bundle b = getIntent().getExtras();
        if(b != null){
            tvName.setText(b.getString("key_name"));
        }else{
            Toast.makeText(this, "Erro na passagem de parâmetros!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
        // Array data of dogs
        final int[] mListDogs = new int[]{
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7
        };

        gvDogs.setAdapter(new ImageAdapter(GridOfDogs.this, mListDogs));
        gvDogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GridOfDogs.this, "Posição: " + (position+1),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
