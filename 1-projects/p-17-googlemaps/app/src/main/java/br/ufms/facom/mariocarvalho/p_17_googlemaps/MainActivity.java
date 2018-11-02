package br.ufms.facom.mariocarvalho.p_17_googlemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String LAT = "latitude", LONG = "longitude";
    private double latitude = 0.00, longitude = 0.0;
    private EditText edtLat, edtLong;
    private Button btnMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLat = findViewById(R.id.edtLat);
        edtLong = findViewById(R.id.edtLong);
        btnMap = findViewById(R.id.btnMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latitude = Double.parseDouble(edtLat.getText().toString());
                longitude = Double.parseDouble(edtLong.getText().toString());

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra(MainActivity.LAT, latitude);
                intent.putExtra(MainActivity.LONG, longitude);
                Log.e("TAG","lat: " + latitude);
                Log.e("TAG","long: " + longitude);
                startActivity(intent);
            }
        });
    }
}
