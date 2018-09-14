package br.ufms.facom.mariocarvalho.p_5_venda_ingressos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spShow, spArea;
    private RadioGroup rgType;
    private TextView tvValue;
    private Button btnCalculate;
    private double factor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spShow = findViewById(R.id.spShow);
        spArea = findViewById(R.id.spArea);
        rgType = findViewById(R.id.rgType);
        tvValue = findViewById(R.id.tvValue);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Adapter de Shows
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.shows, android.R.layout.simple_spinner_item);
        spShow.setAdapter(adapter1);
        // Adapter de Áreas
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        spArea.setAdapter(adapter2);

        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Verificar qual botão de opção foi clicado
                switch(checkedId) {
                    case R.id.rbInteger:
                        factor = 1;
                        Toast.makeText(MainActivity.this, "Inteira", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbMiddle:
                        factor = 0.5;
                        Toast.makeText(MainActivity.this, "Média", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = spArea.getSelectedItemPosition();
                double value = 0;
                switch (item){
                    case 0:
                        value = 40 * factor;
                        break;
                    case 1:
                        value = 80 * factor;
                        break;
                    case 2:
                        value = 120 * factor;
                        break;
                }
                tvValue.setText("Valor: " + value);
            }
        });
    }
}
