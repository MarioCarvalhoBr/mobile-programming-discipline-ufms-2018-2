package br.ufms.facom.mariocarvalho.p_17_googlemaps;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class AddressLocationActivity extends AppCompatActivity {
    private TextView txtPais, txtEstado, txtCidade;
    private TextView txtBairro, txtRua;
    private double lati=0.0, longi=0.0;
    private Address endereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_location);
        Bundle args = getIntent().getExtras();
        lati = args.getDouble(Constants.LAT);
        longi = args.getDouble(Constants.LONG);

        txtPais = findViewById(R.id.txtPais);
        txtEstado = findViewById(R.id.txtEstado);
        txtCidade = findViewById(R.id.txtCidade);
        txtBairro=findViewById(R.id.txtBairro);
        txtRua = findViewById(R.id.txtRua);

        try {
            endereco = buscarEndereco(lati,longi);
            txtPais.setText(endereco.getCountryName());
            txtEstado.setText(endereco.getAdminArea());
            txtCidade.setText(endereco.getLocality());
            txtBairro.setText(endereco.getSubLocality());
            txtRua.setText(endereco.getThoroughfare());

        }catch (IOException e) {
            Log.i("GPS", e.getMessage());
        }
    }
    public Address buscarEndereco(double lati, double longi)
            throws IOException{
        Geocoder geocoder;
        Address address = null;
        List<Address> add;
        geocoder = new Geocoder(getApplicationContext());
        add = geocoder.getFromLocation(lati,longi,1);
        if(add.size()>0){
            address = add.get(0);
        }
        return address;
    }
}

