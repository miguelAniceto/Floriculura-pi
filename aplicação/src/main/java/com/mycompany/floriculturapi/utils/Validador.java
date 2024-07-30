package com.mycompany.floriculturapi.utils;


import com.mycompany.floriculturapi.dao.ClienteDAO;
import com.mycompany.floriculturapi.models.Cliente;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;


/**
 * Classe que realiza as validações de um JframeForm
 * @author migue
 */
public class Validador {
    //Atributo que recebe todas as mensagens de erro
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
            //tento converter o numero
            float valorConvertido = Float.parseFloat(txt.getText());
            txt.setBackground(Color.WHITE);
            
        }
        //se a conversão não for bem sucedida exibir mensagem de erro
        catch(NumberFormatException e){
        
            this.mensagensErro.add("Digite Apenas numeros");
            txt.setBackground(Color.red);
        }catch(IllegalArgumentException e){
            this.mensagensErro.add("Não são permitidos valores nulos");
            txt.setBackground(Color.red);
        }
    
    }
    
    /**
     * Valida o cpf de um cliente, verifica se há algum cpf digitado ou se este cpf existe no banco de dados
     * @param cpf Recebe um JFormattedTextField que contém o cpf do cleinte
     * 
     * @see com.mycompany.floriculturapi.models.Cliente
     * @see com.mycompany.floriculturapi.dao.ClienteDAO
     */
    public void validarCPF(JFormattedTextField cpf){
        cpf.setBackground(Color.WHITE);
        //verificar se o campo está vazio
        if("   .   .   -  ".equals(cpf.getText())){
             this.mensagensErro.add("Digite um CPF");
             cpf.setBackground(Color.red);
        }
        //se não estiver vazio, verifica se o cliente existe no bd
        else{
            String cpfConsulta = cpf.getText();
            Cliente clientePesquisa = ClienteDAO.pesquisa(cpfConsulta);
            
            //se o Objeto Cliente voltar preenchido, quer dizer que este CPF ja está cadastrado 
            if(clientePesquisa != null){
                this.mensagensErro.add("Erro: CPF já cadastrado");
                cpf.setBackground(Color.red);
            }
        }
        
        
    }
    
    /**
     * Valida o telefone do cliente, verifica um telefone foi digitado
     * @param tel - recebe um JFormattedTextField
     * 
     * @see com.mycompany.floriculturapi.models.Cliente
     * @see Validador#validarCPF(javax.swing.JFormattedTextField) 
     * @see Validador#validarTexto(javax.swing.JTextField) 
     * @see Validador#validarNumero(javax.swing.JTextField) 
     */
    
    public void validarTelefone(JFormattedTextField tel){
        tel.setBackground(Color.WHITE);
        if("(  )     -    ".equals(tel.getText())){
             this.mensagensErro.add("Digite um telefone");
             tel.setBackground(Color.red);
        }
    }
    
    /**
     * Valida um campo de texto, verifica se o campo está vazio
     * @param txt Recebe um JTextField 
     * @see Validador#validarCPF(javax.swing.JFormattedTextField)
     * @see Validador#validarTelefone(javax.swing.JFormattedTextField) 
     * @see Validador#validarNumero(javax.swing.JTextField) 
     */
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
     
     /**
      * Valida se há botões selecionados
      * @param bg - recebe um ButtonGroup
      */
     public void validarBotao(ButtonGroup bg){
         //resgato o numero de botões
         int grupoDeBotoes = bg.getButtonCount();
         
         try{
             //verifico se algum foi selecionado
             if(bg.getSelection() == null){
                 throw new IllegalArgumentException();
             }
         }
         catch(IllegalArgumentException e){
             /*verifico qual informação não foi dada pela quantidade de botões
               presentes no buttonGroup, caso for 3 faltou o sexo(masculino, 
               feminino, outro), senão
               faltou o estado civil (solteiro, casado)
             */ 
             if(grupoDeBotoes == 3){
                 this.mensagensErro.add("Selecione o sexo");
             }
        
             else{
                 this.mensagensErro.add("Selecione o estado Civil");
        
             }
         }
     }
     
     /**
      * Limpa as mensagens do arraylist mensagensDeErro
      * @see Validador#getMensagensErro() 
      * @see Validador#hasErro() 
      */
     public void limparMensagens(){
     
         this.mensagensErro.clear();
     }
     
     
     /**
      * Resgata todos os erros gerados em uma única String
      * @return 
      * @see Validador#hasErro() 
      * @see Validador#limparMensagens() 
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
     
     /**
      * Verifica de todos os campos foram preenchidos corretamente
      * @return boolean True: Há erros | False: Não há erros
      * @see Validador#getMensagensErro() 
      * @see Validador#limparMensagens() 
      */
     public boolean hasErro(){
     
         if(this.mensagensErro.size()>0){
            return true;    
         }else{
             return false;
         }
     }

     /**
      * Valida datas, verifica de uma data foi selecionada
      * @param data - recebe um JDateChooser
      */
    public void validarData(JDateChooser data) {
        try{
             
             if(data.getDate() == null){
                 throw new IllegalArgumentException();
             }
         }
         catch(IllegalArgumentException e){
             this.mensagensErro.add("Insira uma data");
         } 
    }
    
}
