package br.ufms.facom.mariocarvalho.p_17_googlemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private double latitude = 0.00, longitude = 0.0;
    private EditText edtLat, edtLong;
    private Button btnMap;
    private Button btnGeo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLat = findViewById(R.id.edtLat);
        edtLong = findViewById(R.id.edtLong);
        btnMap = findViewById(R.id.btnMap);
        btnGeo = findViewById(R.id.btnGeo);

        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LastActivity.class);
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude = Double.parseDouble(edtLat.getText().toString());
                longitude = Double.parseDouble(edtLong.getText().toString());

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra(Constants.LAT, latitude);
                intent.putExtra(Constants.LONG, longitude);
                Log.e("TAG","lat: " + latitude);
                Log.e("TAG","long: " + longitude);
                startActivity(intent);
            }
        });
    }
}
