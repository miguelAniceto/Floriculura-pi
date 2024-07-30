package com.mycompany.floriculturapi.dao;

import com.mycompany.floriculturapi.models.Cliente;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que faz a conexão de um objeto do tipo Cliente com o banco de dados
 * 
 * @author miguel
 * @see Cliente
 * 
 */
public class ClienteDAO {
    //atributos para fazer a conexão com o Bd
    static String url = "jdbc:mysql://localhost:3306/floriculturapi";
    static String login = "root";
    static String senha = "adminadmin";
    
    /**
     * Este método registra um cliente no banco de dados
     * @param obj - objeto do tipo Cliente
     * @return boolean true: sucesso | false: falha
     * @see Cliente
     * @see ClienteDAO#alterar(com.mycompany.floriculturapi.models.Cliente)
     * @see ClienteDAO#excluir(int)
     * @see ClienteDAO#pesquisa(java.lang.String)
     * @see ClienteDAO#listar()
     * 
     */
    
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
            "INSERT INTO cliente(nomeCliente, CPF, emailCliente, enderecoCliente, telefoneCliente, dataNasc, sexoCliente, estadoCivil) VALUES(?,?,?,?,?,?,?,?);");
            
            instrucaoSQL.setString(1, obj.getNomeCliente());
            instrucaoSQL.setString(2,obj.getCPF());
            instrucaoSQL.setString(3, obj.getEmailCliente());
            instrucaoSQL.setString(4, obj.getEnderecoCliente());
            instrucaoSQL.setString(5, obj.getTelefoneCliente());
            instrucaoSQL.setDate(6, new java.sql.Date( obj.getDataNasc().getTime())); 
            instrucaoSQL.setString(7, obj.getSexoCliente());
            instrucaoSQL.setString(8, obj.getEstadoCivil());
            
            //executar o comando
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            //se deu certo retorno = true
            if(linhasAfetadas > 0)
                retorno = true;
            
        } 
        //se deu errado exibir menssagem
        catch(ClassNotFoundException e){
            System.out.println("Driver não encontrado");
        }  
        //se deu errado exibir menssagem
        catch(Exception e){
            System.out.println(e.getMessage());
        
        } 
        //encerrar conexão
        finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
        }
        
        //retornar valor
        return retorno;
    }
    
    /**
     * Método que lista todos os clientes registrados no banco de dados
     * 
     * @return ArrayList - Retorna um ArrayList de clientes contendo todos os clientes do banco de dados
     * @see Cliente
     * @see ClienteDAO#alterar(com.mycompany.floriculturapi.models.Cliente)
     * @see ClienteDAO#excluir(int)
     * @see ClienteDAO#pesquisa(java.lang.String)
     * @see ClienteDAO#salvar(com.mycompany.floriculturapi.models.Cliente)
     */
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
                    String estadoCivil = rs.getString("estadoCivil");
                    
                    Cliente item = new Cliente(id, cpf, nome, email, telefone, endereco,dataNasc,  sexo, estadoCivil);
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
    
    /**
     * Método que realiza uma consulta no banco de dados para localizar um cliente
     * @param cpfPesquisa
     * @return Cliente - Retorna um objeto do tipo Cliente, <b>Caso não exista nenhum cliente com o cpf digitado, o retorno terá o valor null</b>
     * @see Cliente
     * @see ClienteDAO#alterar(com.mycompany.floriculturapi.models.Cliente)
     * @see ClienteDAO#excluir(int)
     * @see ClienteDAO#listar() 
     * @see ClienteDAO#salvar(com.mycompany.floriculturapi.models.Cliente)
     */
    public static Cliente pesquisa(String cpfPesquisa){
        
        Cliente clienteRetorno = null;
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
                    String estadoCivil = rs.getString("estadoCivil");
                    clienteRetorno = new Cliente(id, cpf, nome, email, telefone, endereco,dataNasc,  sexo, estadoCivil);
                    
                    
                }
            }
        } 
        catch(Exception e){
            clienteRetorno = null;
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
     
        return clienteRetorno;  
    
    }

    /**
     * Método que altera os dados de um cliente no banco de dados
     * @param obj - Objeto do tipo cliente
     * @return boolean - True: Sucesso | False: Falha
     * @see Cliente
     * @see ClienteDAO#pesquisa(java.lang.String) 
     * @see ClienteDAO#excluir(int)
     * @see ClienteDAO#listar() 
     * @see ClienteDAO#salvar(com.mycompany.floriculturapi.models.Cliente)
     * 
     */
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
            "UPDATE cliente SET nomeCliente=?, emailCliente=?, enderecoCliente=?, telefoneCliente=?, dataNasc=?, sexoCliente=?, estadoCivil=? WHERE idCliente = ?;");
            
            instrucaoSQL.setString(1, obj.getNomeCliente());
            instrucaoSQL.setString(2, obj.getEmailCliente());
            instrucaoSQL.setString(3, obj.getEnderecoCliente());
            instrucaoSQL.setString(4, obj.getTelefoneCliente());
            instrucaoSQL.setDate(5,  new java.sql.Date(obj.getDataNasc().getTime()));
            instrucaoSQL.setString(6, obj.getSexoCliente());
            instrucaoSQL.setString(7, obj.getEstadoCivil());
            instrucaoSQL.setInt(8, obj.getIdCliente());
            
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
     
     /**
      * Método que exclui um cliente do banco de dados
      * @param idExcluir - Número inteiro que corresponde ao id do cliente no banco de dados
      * @return boolean - True: Sucesso | False: Falha
      * @see Cliente
      * @see ClienteDAO#pesquisa(java.lang.String) 
      * @see ClienteDAO#alterar(com.mycompany.floriculturapi.models.Cliente) 
      * @see ClienteDAO#listar() 
      * @see ClienteDAO#salvar(com.mycompany.floriculturapi.models.Cliente)
      * 
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
