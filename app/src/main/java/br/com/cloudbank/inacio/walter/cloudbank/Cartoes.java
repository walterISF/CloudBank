package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cartoes extends AppCompatActivity {


    private List<ListaCartoes> cards = new ArrayList<ListaCartoes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes);

        final RelativeLayout rlCartao = (RelativeLayout) findViewById(R.id.conta) ;

        rlCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Lista = new Intent();
                startActivity(Lista);
                finish();
            }
        });

        createFakeCars();

        final RecyclerView rvCartoes = (RecyclerView) findViewById(R.id.minhaLista);
        rvCartoes.setLayoutManager(new LinearLayoutManager(this));
        rvCartoes.setItemAnimator(new DefaultItemAnimator());
        rvCartoes.setHasFixedSize(true);

        ListaAdapter adapter;
        adapter = new ListaAdapter(this,cards);
        rvCartoes.setAdapter(adapter);

    }

    private void createFakeCars() {
        for(int i = 0; i < 2; i ++) {
            ListaCartoes sampleCar = new ListaCartoes();
            sampleCar.setNome("Nome " + i);
            sampleCar.setConta("Conta: " + i);
            cards.add(sampleCar);
        }
    }


}
