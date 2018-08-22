package com.example.a201719040800.projetomediaaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome, edtCurso, edtDisciplina;
    private Button btnAvancar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtCurso = findViewById(R.id.edtCurso);
        edtDisciplina = findViewById(R.id.edtDisciplina);

        btnAvancar = findViewById(R.id.btnAvancar);
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaTela.class);
                intent.putExtra("nome", edtNome.getText().toString());
                intent.putExtra("curso", edtCurso.getText().toString());
                intent.putExtra("disciplina", edtDisciplina.getText().toString());
                startActivity(intent);
            }
        });
    }
}
