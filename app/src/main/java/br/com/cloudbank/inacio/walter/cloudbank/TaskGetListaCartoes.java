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
 * Created by aluno on 07/12/16.
 */
public class TaskGetListaCartoes extends AsyncTask<Void, Void,List<ListaCartoes>> {

    private final String URLResquest;
    private final Context context;
    private final CartaoCallback callback;
    private ProgressDialog progressDialog;
    private HttpHandler httpHandler;
    private List<ListaCartoes> listaArray = new ArrayList<ListaCartoes>();

    public TaskGetListaCartoes(Context context, CartaoCallback callback, String urlResquest) {
        this.URLResquest = urlResquest;
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde","Carregando Cartões");
    }

    @Override
    protected List<ListaCartoes> doInBackground(Void... params) {

        httpHandler = new HttpHandler();
        try {
            String result = httpHandler.doHttpRequest(URLResquest);
            if(result!=null){
                JSONArray json = new JSONArray(result);
                return createListCartoes(json);

            }
            else{
                throw new Exception("Impossivel capturar resultado.");
            }
        } catch (Exception e) {
            Log.i("Erro","TaskGETListaCartoes -- Sem conexão com o servidor.");
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<ListaCartoes> cartoes) {
        progressDialog.dismiss();
        this.callback.cartaoCallback(cartoes);
    }

    private List<ListaCartoes> createListCartoes(JSONArray itensCartao) throws JSONException {
        ListaCartoes cartao = new ListaCartoes();
        for(int i = 0; i < itensCartao.length(); i ++) {
            JSONObject row = itensCartao.getJSONObject(i);
            cartao.setNumero(row.getString("numero"));
            cartao.setNumeroSeguranca(row.getString("securityNumber"));
            cartao.setLimiteTotal(Float.parseFloat(row.getString("totalLim")));
            cartao.setLimiteUsado(Float.parseFloat(row.getString("usedLim")));
            cartao.setDataExpiracao(row.getString("expiration"));
            cartao.setBandeira(row.getString("brand"));
            listaArray.add(cartao);
        }
        return listaArray;
    }
}
