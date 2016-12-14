package br.com.cloudbank.inacio.walter.cloudbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 13/12/16.
 */

public class TaskGetListaFaturas extends AsyncTask<Void, Void, List<ListaFaturas>> {

    private String URLResquest;
    private final Context context;
    private ProgressDialog progressDialog;
    private final FaturaCallback callback;
    HttpHandler httpHandler;
    private List<ListaFaturas> listaDeFaturas = null;

    public TaskGetListaFaturas(String urlResquest, Context context, FaturaCallback callback){
        URLResquest = urlResquest;
        this.context = context;
        this.callback = callback;
        this.URLResquest = urlResquest;

    }

    @Override
    protected List<ListaFaturas> doInBackground(Void... params) {

        JSONArray novaLIsta = null;
        httpHandler = new HttpHandler();
        try {
            String result = httpHandler.doHttpRequest(URLResquest);
            if(result!=null){
                novaLIsta = new JSONArray(result);
                this.listaDeFaturas = createListCartoes(novaLIsta);

            }
            else{
                throw new Exception("Impossivel capturar resultado.");
            }
        } catch (Exception e) {
            Log.i("Erro","TaskGETListaCartoes -- Sem conexão com o servidor.");
        }
        return listaDeFaturas;
    }

    @Override
    protected void onPostExecute(List<ListaFaturas> listaFaturas) {
        progressDialog.dismiss();
        this.callback.ftCallback(listaFaturas);
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde","Carregando Faturas");
    }

    private List<ListaFaturas> createListCartoes(JSONArray itensCartaoFatura) throws JSONException {
        List<ListaFaturas> listaArray = new ArrayList<ListaFaturas>();
        ListaFaturas fatura;
        for(int i = 0; i < itensCartaoFatura.length(); i ++) {
            fatura = new ListaFaturas();
            JSONObject row = itensCartaoFatura.getJSONObject(i);
            fatura.setValor(Float.parseFloat(row.getString("valor")));
            fatura.setInstallmentValue(Float.parseFloat(row.getString("installmentValue")));
            fatura.setPlace(row.getString("place"));
            fatura.setLatitude(Float.parseFloat(row.getString("latitude")));
            fatura.setLongitude(Float.parseFloat(row.getString("longitude")));
            fatura.setItems(row.getString("items"));
            fatura.setPaidInstallment(Integer.parseInt(row.getString("paidInstallment")));
            fatura.setTotalInstallment(Integer.parseInt(row.getString("totalInstallment")));
            listaArray.add(fatura);
        }
        return listaArray;
    }
}
