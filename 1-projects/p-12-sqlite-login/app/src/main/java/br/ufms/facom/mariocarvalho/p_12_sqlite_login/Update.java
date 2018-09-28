package br.ufms.facom.mariocarvalho.p_12_sqlite_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtUser;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnCalcel;
    private Button btnSave;
    private DBHelper mDBHelper;
    private Contact mContact = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mDBHelper = new DBHelper(Update.this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnCalcel = findViewById(R.id.btnCalcel);

        Bundle args = getIntent().getExtras();
        mContact = (Contact) args.getSerializable("contact");
        if(mContact != null){
            fillFilds();
        }else{
            Toast.makeText(this, "Erro ao acessar os argumentos!", Toast.LENGTH_SHORT).show();
            finish();
        }
        btnCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContact = new Contact();
                mContact.setName(edtName.getText().toString());
                mContact.setEmail(edtEmail.getText().toString());
                mContact.setUser(edtUser.getText().toString());
                mContact.setPassword(edtPassword.getText().toString());
                String verifyPassword = edtConfirmPassword.getText().toString();
                if(verifyPassword.equals(mContact.getPassword())){
                    // Add on database
                    int verify = (int) mDBHelper.update(mContact);
                    if(verify > 0){
                        Toast.makeText(Update.this, "Sucesso ao atualizar!\nLimpando os campos...", Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(Update.this, "Erro ao atualizar!\nE-mail ou usuário já existem...", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Update.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void fillFilds() {
        edtName.setText(mContact.getName());
        edtEmail.setText(mContact.getEmail());
        edtUser.setText(mContact.getUser());
        edtPassword.setText(mContact.getPassword());
    }
}
