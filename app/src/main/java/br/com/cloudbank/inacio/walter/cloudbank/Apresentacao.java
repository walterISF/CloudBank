package br.com.cloudbank.inacio.walter.cloudbank;

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

        Intent it = new Intent(this, Login.class);
        startActivity(it);
        finish();
    }
}
