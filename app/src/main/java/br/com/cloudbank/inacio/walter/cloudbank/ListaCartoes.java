package br.com.cloudbank.inacio.walter.cloudbank;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aluno on 24/11/16.
 */
public class ListaCartoes implements Serializable {

    private String numero;
    private String numeroSeguranca;
    private Float limiteTotal;
    private Float limiteUsado;
    private String dataExpiracao;
    private String bandeira;



    //GUETTERS

    public String getNumero() {
        return numero;
    }

    public String getNumeroSeguranca() {
        return numeroSeguranca;
    }

    public Float getLimiteTotal() { return limiteTotal; }

    public Float getLimiteUsado() {  return limiteUsado; }

    public String getDataExpiracao() { return dataExpiracao; }

    public String getBandeira() { return bandeira;  }



    //SETTERS

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNumeroSeguranca(String numeroSeguranca) {
        this.numeroSeguranca = numeroSeguranca;
    }

    public void setLimiteTotal(Float limiteTotal) {
        this.limiteTotal = limiteTotal;
    }

    public void setLimiteUsado(Float limiteUsado) {
        this.limiteUsado = limiteUsado;
    }

    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }


    //Equals
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //To String
    @Override
    public String toString() {
        return super.toString();
    }


    //Hash Code
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
