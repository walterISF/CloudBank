package br.com.cloudbank.inacio.walter.cloudbank;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kelly on 11/12/2016.
 */
public interface LoginCallback {
    public void lgCallback(JSONObject json) throws JSONException;
}
