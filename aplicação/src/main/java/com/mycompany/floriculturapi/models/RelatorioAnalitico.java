package com.mycompany.floriculturapi.models;

/**
 *Classe que registra os dados de um relatório analitico, usando a DAO consegue-se registrar em um banco de dados e fazer sua visualização
 * 
 * @author miguel
 * @see com.mycompany.floriculturapi.dao.RelatorioAnaliticoDAO
 * @see Produto
 * 
 */
public class RelatorioAnalitico {
    
    private String nomeProduto;
    private String tipoProduto;
    private int qtdProduto;
    private float valorUnitario;

    public RelatorioAnalitico(String nomeProduto, String tipoProduto, int qtdProduto, float valorUnitario) {
        this.nomeProduto = nomeProduto;
        this.tipoProduto = tipoProduto;
        this.qtdProduto = qtdProduto;
        this.valorUnitario = valorUnitario;
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

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    
}
