package br.com.cloudbank.inacio.walter.cloudbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kelly on 11/12/2016.
 */

public class TaskGetLogin extends AsyncTask<Void, Void, JSONObject> {

    private final String URLResquest;
    private final Context context;
    private final LoginCallback callback;
    private ProgressDialog progressDialog;
    private HttpHandler httpHandler;

    public TaskGetLogin(String urlResquest, Context context, LoginCallback callback) {
        this.URLResquest = urlResquest;
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        httpHandler = new HttpHandler();
        try {
            String result = httpHandler.doHttpRequest(URLResquest);
            if(result!=null && result!=""){
                JSONObject json = new JSONObject(result);
                return json;
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
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Aguarde","Validando usuario e senha");
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            this.callback.lgCallback(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
