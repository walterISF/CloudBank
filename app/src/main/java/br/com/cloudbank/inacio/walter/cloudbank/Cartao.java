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

public class Cartao extends AppCompatActivity implements CartaoCallback {

    private List<ListaCartoes> listaArray = new ArrayList<ListaCartoes>();
    private final String URLListaCartoes = "http://192.168.43.25:5050/card/get";
    private TaskGetListaCartoes getListaCartoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");


        final RecyclerView rvCartoes = (RecyclerView) findViewById(R.id.minhaLista);
        rvCartoes.setLayoutManager(new LinearLayoutManager(this));
        rvCartoes.setItemAnimator(new DefaultItemAnimator());
        rvCartoes.setHasFixedSize(true);

        rvCartoes.setAdapter(new AdapterCartoes(this,listaArray, new AdapterCartoes.OnItemClickListener() {
            @Override
            public void onItemClick(ListaCartoes item) {
                Intent Lista = new Intent(Cartao.this, Faturas.class);
                startActivity(Lista);
                finish();
            }
        }));

        getListaCartoes = new TaskGetListaCartoes(this,this,URLListaCartoes+"?clientId="+id);
        getListaCartoes.execute();

    }


    @Override
    public void cartaoCallback(List<ListaCartoes> cartoesList) {
        Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
    }
}
