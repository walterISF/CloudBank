package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cartao extends AppCompatActivity implements CartaoCallback {

    private List<ListaCartoes> listaArray = new ArrayList<ListaCartoes>();
    private final String URLListaCartoes = "http://172.16.131.6:5050/card/get";
    private TaskGetListaCartoes getListaCartoes;
    private RecyclerView rvCartoes;
    private String nomeUsuario;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            id = bundle.getString("id");
            nomeUsuario = bundle.getString("nome");
            rvCartoes = (RecyclerView) findViewById(R.id.minhaLista);
            rvCartoes.setLayoutManager(new LinearLayoutManager(this));
            rvCartoes.setItemAnimator(new DefaultItemAnimator());
            rvCartoes.setHasFixedSize(true);
        }
        else{
            id = "1";
            nomeUsuario = "Allan Guerra";
        }


        getListaCartoes = new TaskGetListaCartoes(this,this,URLListaCartoes+"?clientId="+id);
        getListaCartoes.execute();

    }


    @Override
    public void cartaoCallback(final List<ListaCartoes> cartoesList) {

        rvCartoes.setAdapter(new AdapterCartoes(this,cartoesList, new AdapterCartoes.OnItemClickListener() {
            @Override
            public void onItemClick(ListaCartoes item) {
                Intent Lista = new Intent(Cartao.this, Faturas.class);
                final String numero;
                Bundle bundle = new Bundle();
                numero = item.getNumero();
                bundle.putString("numero",numero);
                Lista.putExtras(bundle);
                startActivity(Lista);
                finish();
            }
        },nomeUsuario));
    }
}
