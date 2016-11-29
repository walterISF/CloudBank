package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cartoes extends AppCompatActivity {


    private List<ListaCartoes> cards = new ArrayList<ListaCartoes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes);

        createFakeCars();



        final RecyclerView rvCartoes = (RecyclerView) findViewById(R.id.minhaLista);
        rvCartoes.setLayoutManager(new LinearLayoutManager(this));
        rvCartoes.setItemAnimator(new DefaultItemAnimator());
        rvCartoes.setHasFixedSize(true);

        ListaAdapter adapter;
        //adapter = new ListaAdapter(this,cards, listener);
        //rvCartoes.setAdapter(adapter);

        rvCartoes.setAdapter(new ListaAdapter(this,cards, new ListaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListaCartoes item) {
                Intent Lista = new Intent(Cartoes.this, ListaFaturas.class);
                startActivity(Lista);
                finish();
            }
        }));

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
