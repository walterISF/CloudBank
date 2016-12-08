package br.com.cloudbank.inacio.walter.cloudbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aluno on 07/12/16.
 */
public class TaskGetListaCartoes extends AsyncTask<Void, Void,List<Cartoes>> {

    private final String URLResquest;
    private final Context context;
    private final CartoesCallback callback;
    private ProgressDialog progressDialog;
    private HttpHandler httpHandler;

    public TaskGetListaCartoes(String urlResquest, Context context, CartoesCallback callback) {
        this.URLResquest = urlResquest;
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde","Carregando Cartões");
    }

    @Override
    protected List<Cartoes> doInBackground(Void... params) {

        httpHandler = new HttpHandler();
        Cartoes cartao = new Cartoes();
        Object retorno = null;
        try {
            String result = httpHandler.doHttpsRequest(URLResquest);
            if(result!=null){
                Gson gson = new Gson();
                retorno = gson.fromJson(result, (Type) cartao);
            }
            else{
                throw new Exception("Impossivel capturar resultado.");
            }
        } catch (Exception e) {
            Log.i("Erro","TaskGETListaCartoes -- Sem conexão com o servidor.");
        }

        return (List<Cartoes>) retorno;
    }

    @Override
    protected void onPostExecute(List<Cartoes> cartoes) {
        progressDialog.dismiss();
        this.callback.callback(cartoes);
    }
}
