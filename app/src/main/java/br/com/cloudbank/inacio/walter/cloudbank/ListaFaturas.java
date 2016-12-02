package br.com.cloudbank.inacio.walter.cloudbank;

import java.io.Serializable;

/**
 * Created by aluno on 02/12/16.
 */
public class ListaFaturas implements Serializable {

    Long codigo;
    Integer mesReferencia;
    Boolean statusPagamento;
    Integer diaFechamento;
    Integer diaExpiracao;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Boolean getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(Boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Integer getDiaFechamento() {
        return diaFechamento;
    }

    public void setDiaFechamento(Integer diaFechamento) {
        this.diaFechamento = diaFechamento;
    }

    public Integer getDiaExpiracao() {
        return diaExpiracao;
    }

    public void setDiaExpiracao(Integer diaExpiracao) {
        this.diaExpiracao = diaExpiracao;
    }





}
