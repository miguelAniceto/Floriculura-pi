package com.mycompany.floriculturapi.models;

import java.sql.Date;

public class Cliente {
    private int idCliente;
    private String CPF;
    private String nomeCliente;    
    private String emailCliente;
    private String telefoneCliente;
    private String enderecoCliente;
    private Date dataNasc;
    private String sexoCliente;

    public Cliente() {
    }

    public Cliente(int idCliente, String CPF, String nomeCliente, String emailCliente, String telefoneCliente, String enderecoCliente, Date dataNasc, String sexoCliente) {
        
        this.idCliente = idCliente;
        this.CPF = CPF;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.telefoneCliente = telefoneCliente;
        this.enderecoCliente = enderecoCliente;
        this.dataNasc = dataNasc;
        this.sexoCliente = sexoCliente;
    }

    public Cliente(String CPF, String nomeCliente, String emailCliente, String telefoneCliente, String enderecoCliente, Date dataNasc, String sexoCliente) {
        this.CPF = CPF;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.telefoneCliente = telefoneCliente;
        this.enderecoCliente = enderecoCliente;
        this.dataNasc = dataNasc;
        this.sexoCliente = sexoCliente;
    }
    
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }
    
}
