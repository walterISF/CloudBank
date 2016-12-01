package br.com.cloudbank.inacio.walter.cloudbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaFaturas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ListaCartoes> cards = new ArrayList<ListaCartoes>();

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

        createFakeCars();



        final RecyclerView rvFaturas = (RecyclerView) findViewById(R.id.rvFaturas);
        rvFaturas.setLayoutManager(new LinearLayoutManager(this));
        rvFaturas.setItemAnimator(new DefaultItemAnimator());
        rvFaturas.setHasFixedSize(true);

        ListaAdapter adapter;
        //adapter = new ListaAdapter(this,cards, listener);
        //rvCartoes.setAdapter(adapter);

        rvFaturas.setAdapter(new AdapterFaturas(cards,this, new AdapterFaturas.OnItemClickListener() {
            @Override
            public void onItemClick(ListaCartoes item) {
                Intent Lista = new Intent(ListaFaturas.this, DetalheFatura.class);
                startActivity(Lista);
            }
        }));
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
        builder.setTitle("Deseja Bloquear seu Cartão?");
        builder.setMessage("O seu cartão será bloqueado imediatamente após a confirmação.");

        //Funcao de click da mensagem de alerta para bloqueio de cartoes "SIM"
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ListaFaturas.this, "Seu cartão foi bloueado", Toast.LENGTH_SHORT).show();
            }
        });

        //Funcao de click da mensagem de alerta para bloqueio de cartoes "NAO"
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ListaFaturas.this, "Funcao abortada", Toast.LENGTH_SHORT).show();
            }
        });


        int id = item.getItemId();

        if (id == R.id.nav_minhaConta) {
            Intent minhaConta = new Intent(ListaFaturas.this, MinhaConta.class);
            startActivity(minhaConta);

        } else if (id == R.id.nav_bloquear) {

            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (id == R.id.nav_cartoes) {
            Intent retorno = new Intent(ListaFaturas.this, Cartoes.class);
            startActivity(retorno);
            this.finish();
        } else if (id == R.id.nav_sair){
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
