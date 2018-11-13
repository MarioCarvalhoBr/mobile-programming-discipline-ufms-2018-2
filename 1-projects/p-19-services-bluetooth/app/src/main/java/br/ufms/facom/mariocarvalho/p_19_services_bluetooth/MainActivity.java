package br.ufms.facom.mariocarvalho.p_19_services_bluetooth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnServices, btnBluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnServices = findViewById(R.id.btnServices);
        btnBluetooth = findViewById(R.id.btnBluetooth);
        // Services
        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Services!", Toast.LENGTH_SHORT).show();
            }
        });
        // Bluetooth
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Bluetooth!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainBluetoothActivity.class));
            }
        });
    }
}
