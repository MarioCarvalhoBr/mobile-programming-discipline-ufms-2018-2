package br.com.mariodearaujocarvalho.p_4_calculo_imc;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SegundaActivity extends AppCompatActivity {

    private EditText edtPeso;
    private EditText edtAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
    }

    public void onClickCalcula(View view) {
        String v1 = edtPeso.getText().toString();
        String v2 = edtAltura.getText().toString();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        if(v1.isEmpty() || v2.isEmpty()){
            alert.setMessage("Está vazio!");
        }else{
            double peso = 0, altura = 0, resultado = 0;

            peso = Double.parseDouble(v1);
            altura = Double.parseDouble(v2);
            resultado = peso / (Math.pow(altura, 2));

            alert.setMessage("O seu IMC é: " + resultado);

        }
        alert.setNegativeButton("OK", null);
        alert.show();
    }

    public void onClickSair(View view) {
        finish();
    }
}
