package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Faturas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FaturaCallback {

    private List<ListaFaturas> faturas = new ArrayList<ListaFaturas>();
    private RecyclerView rvFaturas;
    private String URLListaFaturas;
    private TaskGetListaFaturas getListaFaturas;
    private String numeroCartao, nomeRetorno, idRetorno;
    private HttpHandler httpHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_faturas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {
            numeroCartao = bundle.getString("numero");
            idRetorno = bundle.getString("id");
            nomeRetorno = bundle.getString("nome");
        }
        else {
            numeroCartao = "4055520000335412";
            idRetorno = "1";
            nomeRetorno = "Allan Guerra";
        }

        rvFaturas = (RecyclerView) findViewById(R.id.rvFaturas);
        rvFaturas.setLayoutManager(new LinearLayoutManager(this));
        rvFaturas.setItemAnimator(new DefaultItemAnimator());
        rvFaturas.setHasFixedSize(true);
        URLListaFaturas = "http://172.16.131.6:5050/invoice/get?cardNumber="+numeroCartao+"&refMonth=11";
        getListaFaturas = new TaskGetListaFaturas(URLListaFaturas,this,this);
        getListaFaturas.execute();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista_faturas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deseja Cancelar seu Cartão?");
        builder.setMessage("O seu cartão será cancelado imediatamente após a confirmação.");

        //Funcao de click da mensagem de alerta para bloqueio de cartoes "SIM"
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                httpHandler = new HttpHandler();
                try {
                    String result = httpHandler.doHttpRequest("http://172.16.131.6:5050/card/cancel?cardNumber="+numeroCartao+"&registration=16001000");
                    if(result!=null){


                    }
                    else{
                        throw new Exception("Impossivel capturar resultado.");
                    }
                } catch (Exception e) {
                    Log.i("Erro","TaskGETListaCartoes -- Sem conexão com o servidor.");
                }
            }
        });

        //Funcao de click da mensagem de alerta para bloqueio de cartoes "NAO"
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Faturas.this, "Funcao abortada", Toast.LENGTH_SHORT).show();
            }
        });


        int id = item.getItemId();

        if (id == R.id.nav_bloquear) {

            AlertDialog dialog = builder.create();
            dialog.show();
        }else if (id == R.id.nav_sair){
            System.exit(0);
        }else if(id == R.id.nav_cartao){
            Intent voltar = new Intent(Faturas.this,Cartao.class);
            Bundle numCard = new Bundle();
            numCard.putString("numero",numeroCartao);
            numCard.putString("id",idRetorno);
            numCard.putString("nome",nomeRetorno);
            voltar.putExtras(numCard);
            startActivity(voltar);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void ftCallback(List<ListaFaturas> listaDeFatura) {
        rvFaturas.setAdapter(new AdapterFaturas(listaDeFatura,this, new AdapterFaturas.OnItemClickListener() {
            @Override
            public void onItemClick(ListaFaturas item) {
                Intent Lista = new Intent(Faturas.this, DetalheFatura.class);
                Bundle compras = new Bundle();
                compras.putString("latitude",item.getLatitude().toString());
                compras.putString("longitude",item.getLongitude().toString());
                compras.putString("local",item.getPlace().toString());
                compras.putString("valor",item.getValor().toString());
                compras.putString("id", idRetorno);
                compras.putString("numero", numeroCartao);
                compras.putString("nome", nomeRetorno);
                Lista.putExtras(compras);
                startActivity(Lista);
            }
        }));
    }
}
