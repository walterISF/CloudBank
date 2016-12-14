package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class DetalheFatura extends AppCompatActivity implements OnMapReadyCallback {

    private String local;
    private Double latitude,longitude;
    private Float valor;
    Bundle compras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_fatura);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent dados = getIntent();
        compras = dados.getExtras();
        latitude = Double.parseDouble(compras.getString("latitude"));
        longitude = Double.parseDouble(compras.getString("longitude"));
        local = compras.getString("local");
        valor = Float.parseFloat(compras.getString("valor"));
        DecimalFormat df = new DecimalFormat("0.00");
        String ValorCompra = "R$ " + df.format(valor);

        TextView tvValorCompra = (TextView)findViewById(R.id.estabelecimentoCompra);
        tvValorCompra.setText(ValorCompra);

        TextView tvEstabelecimento = (TextView)findViewById(R.id.precoCompra);
        tvEstabelecimento.setText(local);


        Button denuncia = (Button)findViewById(R.id.btnDenunciar);

        denuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetalheFatura.this, "Sua denuncia será analisada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng localMapa = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(localMapa).title(local));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(localMapa));
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(localMapa)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent telaFatura = new Intent(DetalheFatura.this, Faturas.class);
        Bundle voltaFatura = new Bundle();
        voltaFatura.putString("id", compras.getString("id"));
        voltaFatura.putString("nome", compras.getString("nome"));
        voltaFatura.putString("numero", compras.getString("numero"));
        telaFatura.putExtras(voltaFatura);
        startActivity(telaFatura);
        return super.onOptionsItemSelected(item);
    }
}
