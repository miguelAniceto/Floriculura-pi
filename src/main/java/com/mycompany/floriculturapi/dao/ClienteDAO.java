
package com.mycompany.floriculturapi.dao;

import com.mycompany.floriculturapi.models.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    
    static String url = "jdbc:mysql://localhost:3306/floriculturapi";
    static String login = "root";
    static String senha = "adminadmin";
    
    
    public static boolean salvar(Cliente obj){
        boolean retorno = false;
        Connection conexao = null ;
        
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "INSERT INTO cliente(nomeCliente, CPF, emailCliente, enderecoCliente, telefoneCliente, dataNasc, sexoCliente) VALUES(?,?,?,?,?,?,?);");
            
            instrucaoSQL.setString(1, obj.getNomeCliente());
            instrucaoSQL.setString(2,obj.getCPF());
            instrucaoSQL.setString(3, obj.getEmailCliente());
            instrucaoSQL.setString(4, obj.getEnderecoCliente());
            instrucaoSQL.setString(5, obj.getTelefoneCliente());
            instrucaoSQL.setDate(6, (Date) obj.getDataNasc());
            instrucaoSQL.setString(7, obj.getSexoCliente());
            
            //executar o comando
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas > 0)
                retorno = true;
            
        } catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }  
        
        catch(Exception e){
            System.out.println(e.getMessage());
        
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
    
    public static ArrayList<Cliente> listar(){
        ArrayList<Cliente> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "SELECT * FROM Cliente");
            
            //executar comando
            rs = instrucaoSQL.executeQuery();
            if(rs != null){
                while(rs.next()){
                    
                    
                    int id = rs.getInt("idCliente");
                    String nome = rs.getString("nomeCliente");
                    String cpf = rs.getString("CPF");
                    String telefone = rs.getString("telefoneCliente");
                    String endereco = rs.getString("enderecoCliente");
                    String email = rs.getString("emailCliente");
                    String sexo = rs.getString("sexoCliente");
                    Date dataNasc = rs.getDate("dataNasc");
                    
                    Cliente item = new Cliente(id, cpf, nome, email, telefone, endereco,dataNasc,  sexo);
                    listaRetorno.add(item);
                    
                    
                }
            }
        } 
        catch(Exception e){
            listaRetorno = null;
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     
        return listaRetorno;  
    
    }
    
    public static ArrayList<Cliente> pesquisa(String cpfPesquisa){
        
        ArrayList<Cliente> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "SELECT * FROM Cliente WHERE CPF =?");
            
            instrucaoSQL.setString(1,cpfPesquisa);
            
            //executar comando
            rs = instrucaoSQL.executeQuery();
            if(rs != null){
                while(rs.next()){
                    
                    
                    int id = rs.getInt("idCliente");
                    String nome = rs.getString("nomeCliente");
                    String cpf = rs.getString("CPF");
                    String telefone = rs.getString("telefoneCliente");
                    String endereco = rs.getString("enderecoCliente");
                    String email = rs.getString("emailCliente");
                    String sexo = rs.getString("sexoCliente");
                    Date dataNasc = rs.getDate("dataNasc");
                    
                    Cliente item = new Cliente(id, cpf, nome, email, telefone, endereco,dataNasc,  sexo);
                    listaRetorno.add(item);
                    
                    
                }
            }
        } 
        catch(Exception e){
            listaRetorno = null;
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     
        return listaRetorno;  
    
    }

     public static boolean alterar(Cliente obj){
        boolean retorno = false;
        Connection conexao = null ;
        
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "UPDATE cliente SET nomeCliente=?, emailCliente=?, enderecoCliente=?, telefoneCliente=?, dataNasc=?, sexoCliente=? WHERE idCliente = ?;");
            
            instrucaoSQL.setString(1, obj.getNomeCliente());
            instrucaoSQL.setString(2, obj.getEmailCliente());
            instrucaoSQL.setString(3, obj.getEnderecoCliente());
            instrucaoSQL.setString(4, obj.getTelefoneCliente());
            instrucaoSQL.setDate(5, (Date) obj.getDataNasc());
            instrucaoSQL.setString(6, obj.getSexoCliente());
            instrucaoSQL.setInt(7, obj.getIdCliente());
            
            //executar o comando
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas > 0)
                retorno = true;
            
        } catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }  
        
        catch(Exception e){
            System.out.println(e.getMessage());
        
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
     
     public static boolean excluir(int idExcluir){
        boolean retorno = false;
        Connection conexao = null ;
        
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "DELETE FROM Cliente WHERE idCliente = ?;");
            
            
            instrucaoSQL.setInt(1, idExcluir);
            
            //executar o comando
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas > 0)
                retorno = true;
            
        } catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }  
        
        catch(Exception e){
            System.out.println(e.getMessage());
        
        } finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
}
