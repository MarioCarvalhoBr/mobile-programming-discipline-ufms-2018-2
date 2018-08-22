package br.com.mariodearaujocarvalho.project_1_calculadora;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtValor1;
    private EditText edtValor2;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValor1 = (EditText) findViewById(R.id.edtValor1);
        edtValor2 = (EditText) findViewById(R.id.edtValor2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String v1 = edtValor1.getText().toString();
        String v2 = edtValor2.getText().toString();
        String sms = "";

        if(v1.trim().isEmpty() || v2.trim().isEmpty()){
            sms = "Campos vazio!";
        }else{
            double valor1 = Double.parseDouble(edtValor1.getText().toString());
            double valor2 = Double.parseDouble(edtValor2.getText().toString());
            double resultado = (valor1  + valor2);
            sms = "O resultado é: " + resultado;
        }

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage("O resultado é: " + sms);
        dlg.setNegativeButton("Ok", null);
        dlg.create().show();
    }
}
