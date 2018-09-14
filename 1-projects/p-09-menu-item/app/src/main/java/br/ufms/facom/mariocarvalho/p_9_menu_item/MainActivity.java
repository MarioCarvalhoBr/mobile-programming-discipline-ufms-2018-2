package br.ufms.facom.mariocarvalho.p_9_menu_item;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Global var's
    private TextView tvWellcome;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvWellcome = findViewById(R.id.tvWellcome);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mensagem fixa do tipo Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Você clicou na ação da mensagem snackbar!", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Verificando a opção clicada
        switch (id){
            case R.id.action_home:
                tvWellcome.setText("Seja bem-vindo(a)!");
                tvWellcome.setTextColor(getResources().getColor(R.color.colorBlack));
                Toast.makeText(this, "Olá, você já está na Home!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                tvWellcome.setTextColor(getResources().getColor(R.color.colorBlack));
                break;
            case R.id.action_color:
                tvWellcome.setTextColor(getResources().getColor(R.color.colorText));
                break;
            case R.id.action_text:
                tvWellcome.setText("De smartphones e relógios\n" +
                        "a carros e TVs, personalize\n" +
                        "sua vida digital com o Android.");
                break;
            case R.id.action_quit:
                finish();
                break;
            default:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
