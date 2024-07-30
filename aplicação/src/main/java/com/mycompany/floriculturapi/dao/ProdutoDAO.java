package com.mycompany.floriculturapi.dao;


import com.mycompany.floriculturapi.models.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que faz a conexão de um objeto do tipo Produto com o banco de dados
 * 
 * @author miguel
 * @see Produto
 */

public class ProdutoDAO {
    static String url = "jdbc:mysql://localhost:3306/floriculturapi";
    static String login = "root";
    static String senha = "adminadmin";
    
    /**
     * Método que registra um Objeto da Classe Produto no banco de dados
     * @param obj - Objeto da classe produto
     * @return boolean - True: Sucesso | False: Falha
     * @see ProdutoDAO#alterar(com.mycompany.floriculturapi.models.Produto)
     * @see ProdutoDAO#excluir(int)
     * @see ProdutoDAO#listar()
     * @see ProdutoDAO#pesquisar(java.lang.String) 
     */
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
    
    /**
     * Método que lista todos os produtos salvos no banco de dados
     * @return ArrayList - retorna um ArrayList da classe Produto
     * @see Produto
     * @see ProdutoDAO#alterar(com.mycompany.floriculturapi.models.Produto)
     * @see ProdutoDAO#excluir(int)
     * @see ProdutoDAO#salvar(com.mycompany.floriculturapi.models.Produto) 
     * @see ProdutoDAO#pesquisar(java.lang.String) 
     */
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

    /**
     * Método que realiza uma pesquisa no banco de dados a partir do nome do produto desejado, retorna todos os produtos com o nome parecido com o digitado
     * @param nomePesquisa - nome do produto que deseja achar no banco de dados
     * @return ArrayList - Retorna um ArrayList da classe produto 
     * @see Produto
     * @see ProdutoDAO#alterar(com.mycompany.floriculturapi.models.Produto)
     * @see ProdutoDAO#excluir(int)
     * @see ProdutoDAO#salvar(com.mycompany.floriculturapi.models.Produto) 
     * @see ProdutoDAO#listar() 
     */
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
            instrucaoSQL.setString(1,nomePesquisa + "%");
            
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
    
    /**
     * Método que altera os dados de un produto no banco de dados
     * @param obj - Objeto da classe Produto
     * @return boolean - True: Sucesso | False: Falha
     * @see Produto
     * @see ProdutoDAO#excluir(int)
     * @see ProdutoDAO#salvar(com.mycompany.floriculturapi.models.Produto) 
     * @see ProdutoDAO#pesquisar(java.lang.String)
     * @see ProdutoDAO#listar() 
     */
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        
        return retorno;
    }
     /**
      * Método que exclui um cliente do banco de dados
      * @param idExcluir - número do tipo inteiro que corresponde ao id do cliente que deseja excluir no banco
      * @return boolean - True: Sucesso | False: Falha
      * @see ProdutoDAO#alterar(com.mycompany.floriculturapi.models.Produto) 
      * @see ProdutoDAO#salvar(com.mycompany.floriculturapi.models.Produto) 
      * @see ProdutoDAO#pesquisar(java.lang.String)
      * @see ProdutoDAO#listar() 
      */
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
