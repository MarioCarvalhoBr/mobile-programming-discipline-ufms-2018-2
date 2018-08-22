package br.com.mariodearaujocarvalho.p_3_constaint_layout;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtValor1;
    private EditText edtValor2;
    private EditText edtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValor1 = findViewById(R.id.edtValor1);
        edtValor2 = findViewById(R.id.edtValor2);
        edtResultado = findViewById(R.id.edtResultado);
    }

    public void check(){
    }

    public void onClickCalcula(android.view.View view){
        String v1 = edtValor1.getText().toString();
        String v2 = edtValor2.getText().toString();
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        int id = view.getId();

        if(v1.trim().isEmpty() || v2.trim().isEmpty()){
            alerta.setMessage("Insira os valores corretamente!");
            alerta.setNegativeButton("Ok", null);
            alerta.show();
        }else if(id == R.id.btnSoma){
            double valor1 = 0, valor2 = 0, resultado = 0;
            valor1 = Double.parseDouble(v1);
            valor2 = Double.parseDouble(v2);
            resultado = valor1 + valor2;
            edtResultado.setText(""+resultado);
        }else if(id == R.id.btnSubtrai){
            double valor1 = 0, valor2 = 0, resultado = 0;
            valor1 = Double.parseDouble(v1);
            valor2 = Double.parseDouble(v2);
            resultado = valor1 - valor2;
            edtResultado.setText(""+resultado);
        }
    }
    public void onClickSoma(android.view.View view){
        
    }
}
