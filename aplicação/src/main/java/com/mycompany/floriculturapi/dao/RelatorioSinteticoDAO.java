package com.mycompany.floriculturapi.dao;

import com.mycompany.floriculturapi.models.RelatorioSintetico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *Classe que faz a conexão de um objeto do tipo RelatorioSintetico com o banco de dados
 * 
 * @author migue
 * @see RelatorioSintetico
 */
public class RelatorioSinteticoDAO {
    
    static String url = "jdbc:mysql://localhost:3306/floriculturapi";
    static String login = "root";
    static String senha = "adminadmin";

    /**
     * Método que lista todos os relatórios sintéticos com base em um período
     * @param dataInicio - Tipo Date - Data de incio do período desejado
     * @param dataTermino - Tipo Date - Data de termino do período desejado
     * @return ArrayList - Retorna um ArrayList da clasee RelatorioSintetico contendo todos os relatórios sintéticos entre o período informado 
     * @see RelatorioSintetico
     */
    public static ArrayList<RelatorioSintetico> listarPorPeriodo(Date dataInicio, Date dataTermino){
        
        Connection conexao = null;
        ResultSet rs = null;
        
        ArrayList<RelatorioSintetico> listaRetorno = new ArrayList();

        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url,login,senha);
            
            String sql = "SELECT Venda.idVenda, Venda.idCliente, Cliente.nomeCliente, Venda.dataVenda, Venda.valorVenda FROM Venda "
                    + "   INNER JOIN Cliente ON Venda.idCliente = Cliente.idCliente"
                    + "   WHERE dataVenda BETWEEN ? AND ?";
            
            PreparedStatement comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setDate(1, new java.sql.Date(dataInicio.getTime()));
            comandoSQL.setDate(2, new java.sql.Date(dataTermino.getTime()));
            
            
            rs = comandoSQL.executeQuery();
            
            if(rs != null){
                
                while(rs.next()){
                    RelatorioSintetico item = null;
                
                    int idVenda = rs.getInt("idVenda");
                    int idCliente = rs.getInt("idCliente");
                    String nomeCliente = rs.getString("nomeCliente");
                    Date dataVenda = rs.getDate("dataVenda");
                    float valorVenda = rs.getFloat("valorVenda");
                
                    item = new RelatorioSintetico(idVenda, dataVenda, idCliente, nomeCliente, valorVenda);
                
                    listaRetorno.add(item);
                }
                
                
            }
        }catch(Exception e){
            listaRetorno = null;
        }
        return listaRetorno;
    }
}
