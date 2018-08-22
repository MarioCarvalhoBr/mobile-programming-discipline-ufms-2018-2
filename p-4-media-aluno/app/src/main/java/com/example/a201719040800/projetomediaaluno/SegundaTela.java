package com.example.a201719040800.projetomediaaluno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaTela extends AppCompatActivity {
    private EditText edtNome, edtCurso, edtFaltas, edtProva1, edtProva2, edtTrabalho, edtSituacao, edtMedia;
    private Button btnResultado, btnSiscad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        edtNome = findViewById(R.id.edtNome);
        edtCurso = findViewById(R.id.edtCurso);

        edtFaltas  = findViewById(R.id.edtFaltas);
        edtProva1 = findViewById(R.id.edtProva1);
        edtProva2 = findViewById(R.id.edtProva2);
        edtTrabalho = findViewById(R.id.edtTrabalho);

        edtSituacao =  findViewById(R.id.edtSituacao);
        edtMedia = findViewById(R.id.edtMedia);

        btnResultado = findViewById(R.id.btnResultado);
        btnSiscad = findViewById(R.id.btnSiscad);

        Bundle b = getIntent().getExtras();

        if(b != null){
            edtNome.setText(b.getString("nome"));
            edtCurso.setText(b.getString("curso"));
        }else{
            Toast.makeText(this, "Erro na passagem de parâmetros!!!", Toast.LENGTH_SHORT).show();
        }

        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empty();
                if (Double.parseDouble(edtFaltas.getText().toString()) > 17) {
                    edtSituacao.setText("Reprovado por falta!!");
                }else{
                    double p1  = Double.parseDouble(edtProva1.getText().toString());
                    double p2  = Double.parseDouble(edtProva2.getText().toString());
                    double t  = Double.parseDouble(edtTrabalho.getText().toString());

                    double media = (p1 + p2 + t) / 3;
                    if(media >= 7){
                        edtMedia.setText("" + media);
                        edtSituacao.setText("Reprovado por nota!!!");
                    }else{
                        edtMedia.setText("" + media);
                        edtSituacao.setText("Aprovado!!!");
                    }
                }
            }
        });
    }

    private void empty() {
        edtMedia.setText("");
        edtSituacao.setText("");
    }
}
