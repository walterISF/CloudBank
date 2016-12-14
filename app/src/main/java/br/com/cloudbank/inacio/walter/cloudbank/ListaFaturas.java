package br.com.cloudbank.inacio.walter.cloudbank;

import java.io.Serializable;

/**
 * Created by aluno on 02/12/16.
 */
public class ListaFaturas implements Serializable {

    private Float valor;
    private String items;
    private String place;
    private Float installmentValue;
    private Float latitude;
    private Float longitude;
    private Integer totalInstallment;
    private Integer paidInstallment;

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Float getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Float installmentValue) {
        this.installmentValue = installmentValue;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(Integer totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getPaidInstallment() {
        return paidInstallment;
    }

    public void setPaidInstallment(Integer paidInstallment) {
        this.paidInstallment = paidInstallment;
    }



}
