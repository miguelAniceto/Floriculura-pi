/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.floriculturapi.utils;

/**
 *
 * @author migue
 */
import com.mycompany.floriculturapi.dao.ClienteDAO;
import com.mycompany.floriculturapi.models.Cliente;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author fernando.tfernandes
 */
public class Validador {
    
    public ArrayList<String> mensagensErro = new ArrayList<>();
    
    /**
     * Valida somente campos inteiros
     * @param txt Recebe um objeto do tipo JTextField
     */
    public void validarNumero(JTextField txt){
    
        try{
            
            //Verifico se o campo está vazio
            if(txt.getText().trim().equals("")){
                throw new IllegalArgumentException();
            }
            
            float valorConvertido = Float.parseFloat(txt.getText());
            txt.setBackground(Color.WHITE);
            
        }catch(NumberFormatException e){
        
            this.mensagensErro.add("Digite Apenas numeros");
            txt.setBackground(Color.red);
        }catch(IllegalArgumentException e){
            this.mensagensErro.add("Não são permitidos valores nulos");
            txt.setBackground(Color.red);
        }
    
    }
    
    public void validarCPF(JFormattedTextField cpf){
        if("   .   .   -  ".equals(cpf.getText())){
             this.mensagensErro.add("Digite um CPF");
             cpf.setBackground(Color.red);
        }
        else{
            String cpfConsulta = cpf.getText();
            ArrayList<Cliente> lista = ClienteDAO.pesquisa(cpfConsulta);
        
            if(lista != null){
                this.mensagensErro.add("Erro: CPF já cadastrado");
                cpf.setBackground(Color.red);
            }
        }
        
        
    }
    /**
     * Valida somente campos texto
     * @param txt Recebe um objeto do tipo JTextField
     */
    
    public void validarTelefone(JFormattedTextField tel){
         if("   .   .   -  ".equals(tel.getText())){
             this.mensagensErro.add("Digite um telefone");
             tel.setBackground(Color.red);
        }
    }
     public void validarTexto(JTextField txt){
     
         try{
            
            //Verifico se o campo está vazio
            if(txt.getText().trim().equals("")){
                throw new IllegalArgumentException();
            }
            
            txt.setBackground(Color.white);
            
        }catch(IllegalArgumentException e){
            this.mensagensErro.add("Não são permitidos valores nulos");
            txt.setBackground(Color.red);
        }
     
     }
     
     public void validarBotao(ButtonGroup bg){
         
         try{
             
             if(bg.getSelection() == null){
                 throw new IllegalArgumentException();
             }
         }
         catch(IllegalArgumentException e){
             this.mensagensErro.add("Não são permitidos valores nulos");
         }
     }
     
     
     public void limparMensagens(){
     
         this.mensagensErro.clear();
     }
     
     /**@deprecated substituida por {@link #getMensagensErro()}
      * Método para exibir mensagens de erro na tela com JOptionPane
      */
     public void ExibirMensagensErro(){
         
        String errosFormulario = "";
        for (String msg : this.mensagensErro) {
            errosFormulario += msg + "\n";
        }
        
        if(!errosFormulario.equals("")){
            JOptionPane.showMessageDialog(null, errosFormulario);
            this.limparMensagens();
        }     

     }
     
     /**
      * Resgata todos os erros gerados em uma única String
      * @return 
      */
     public String getMensagensErro(){
         
        String errosFormulario = "";
        
        //Percorro o arrayList e concateno na variável errosFormulario
        for (String msg : this.mensagensErro) {
            errosFormulario += msg + "\n";
        }
        
        if(!errosFormulario.equals("")){
            this.limparMensagens();
        }     
        
        return errosFormulario;
        
     }
     
     public boolean hasErro(){
     
         if(this.mensagensErro.size()>0){
            return true;    
         }else{
             return false;
         }
     }

    public void validarData(JDateChooser data) {
        try{
             
             if(data.getDate() == null){
                 throw new IllegalArgumentException();
             }
         }
         catch(IllegalArgumentException e){
             this.mensagensErro.add("Insira uma data");
         } // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
