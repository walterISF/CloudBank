package br.com.cloudbank.inacio.walter.cloudbank;

import org.json.JSONException;

import java.util.List;

/**
 * Created by aluno on 13/12/16.
 */

public interface FaturaCallback {
    public void ftCallback(List<ListaFaturas> listaDeFatura);
}
