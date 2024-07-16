package com.mycompany.floriculturapi.models;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String tipoProduto;
    private int qtdProduto;
    private float precoProduto;

    public Produto() {
    }

    public Produto(String nomeProduto, String tipoProduto, int qtdProduto, float precoProduto) {
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.qtdProduto = qtdProduto;
        this.precoProduto = precoProduto;
    }

    public Produto(int idProduto, String nomeProduto, String tipoProduto, int qtdProduto, float precoProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.qtdProduto = qtdProduto;
        this.precoProduto = precoProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }
    
}
