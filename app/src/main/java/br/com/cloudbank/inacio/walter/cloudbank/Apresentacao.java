package br.com.cloudbank.inacio.walter.cloudbank;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Apresentacao extends AppCompatActivity implements Runnable {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        handler = new Handler();
        handler.postDelayed(this, 3000);

    }

    @Override
    public void run() {

        //Lista de permissoes necessárias
        String permissions[] = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
        };


        //VALIDA LISTA DE PERMISSOES
        boolean ok = PermissionUtils.validate(this, 0, permissions);
        if(ok) {
            Intent it = new Intent(this, Login.class);
            startActivity(it);
            finish();
        }
    }
}
