package com.mycompany.floriculturapi.dao;


import com.mycompany.floriculturapi.models.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {
    static String url = "jdbc:mysql://localhost:3306/floriculturapi";
    static String login = "root";
    static String senha = "adminadmin";
    
    
    public static boolean salvar(Produto obj){
        boolean retorno = false;
        Connection conexao = null ;
        
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "INSERT INTO Produto(nomeProduto, tipoProduto, qtdProduto, valorProduto) VALUES(?,?,?,?);");
            
            instrucaoSQL.setString(1, obj.getNomeProduto());
            instrucaoSQL.setString(2,obj.getTipoProduto());
            instrucaoSQL.setInt(3, obj.getQtdProduto());
            instrucaoSQL.setFloat(4, obj.getPrecoProduto());
            
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
    
    public static ArrayList<Produto> listar(){
        ArrayList<Produto> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "SELECT * FROM Produto");
            
            //executar comando
            rs = instrucaoSQL.executeQuery();
            if(rs != null){
                while(rs.next()){
                    
                    
                    int id = rs.getInt("idProduto");
                    String nome = rs.getString("nomeProduto");
                    String tipo = rs.getString("tipoProduto");
                    int qtd = rs.getInt("qtdProduto");
                    float valor = rs.getFloat("valorProduto");
                    
                    Produto item = new Produto(id, nome, tipo, qtd, valor);
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     
        return listaRetorno;  
    
    }

    public static ArrayList<Produto> pesquisar(String nomePesquisa){
        
        ArrayList<Produto> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "SELECT * FROM Produto WHERE nomeProduto LIKE ? ");
            instrucaoSQL.setString(1,nomePesquisa);
            
            //executar comando
            rs = instrucaoSQL.executeQuery();
            if(rs != null){
                while(rs.next()){
                    
                    
                    int id = rs.getInt("idProduto");
                    String nome = rs.getString("nomeProduto");
                    String tipo = rs.getString("tipoProduto");
                    int qtd = rs.getInt("qtdProduto");
                    float valor = rs.getFloat("valorProduto");
                    
                    Produto item = new Produto(id, nome, tipo, qtd, valor);
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
     
        return listaRetorno;  
    
        
        
    }
    
    
     public static boolean alterar(Produto obj){
        boolean retorno = false;
        Connection conexao = null ;
        
        try{
            //carregar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //fazer conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            //preparar comando sql
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
            "UPDATE Produto SET nomeProduto=?, tipoProduto=?, qtdProduto=?, valorProduto=? WHERE idProduto = ?;");
            
            instrucaoSQL.setString(1, obj.getNomeProduto());
            instrucaoSQL.setString(2,obj.getTipoProduto());
            instrucaoSQL.setInt(3, obj.getQtdProduto());
            instrucaoSQL.setFloat(4, obj.getPrecoProduto());
            instrucaoSQL.setInt(5, obj.getIdProduto());
            
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
            "DELETE FROM Produto WHERE idProduto = ?;");
            
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
}
