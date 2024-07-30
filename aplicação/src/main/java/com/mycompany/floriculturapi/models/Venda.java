package com.mycompany.floriculturapi.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *Classe que registra os dados de uma venda, usando a DAO é possivel gravar os dados no banco de dados e gerar relatórios das vendas, ataualizar e consultar o estoque
 * 
 * @author miguel
 * @see Cliente
 * @see Produto
 * @see com.mycompany.floriculturapi.dao.VendaDAO
 * @see RelatorioAnalitico
 * @see RelatorioSintetico
 * @see ItemVenda
 */
public class Venda {
    //atributos
    private int idVenda;
    private float valorVenda;
    private Date dataVenda;
    private int idCliente;
    
    //atributo "especial" arraylist que contém os itens de uma venda 
    private ArrayList<ItemVenda> listaItens = new ArrayList<>();

    public Venda() {
    }

    //construtores
    public Venda(float valorVenda, Date dataVenda, int idCliente, ArrayList<ItemVenda> listaItens) {
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
        this.idCliente = idCliente;
        this.listaItens = listaItens;
    }

    public Venda(int idVenda, float valorVenda, Date dataVenda, int idCliente, ArrayList<ItemVenda> listaItens) {
        this.idVenda = idVenda;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
        this.idCliente = idCliente;
        this.listaItens = listaItens;
    }
    
    //getters e setters
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<ItemVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(ArrayList<ItemVenda> listaItens) {
        this.listaItens = listaItens;
    }
    
    
}
