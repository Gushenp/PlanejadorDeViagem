
package planejamentodeviagem;

import javax.swing.JOptionPane;
import java.time.LocalDate;


public class PlanejamentoDeViagem {
    
    public static void main(String[] args) {
       planejadorViagem();
    }

    public static void planejadorViagem(){
        _iniciarPrograma();
        
    }

    public static void _iniciarPrograma(){
        String[] opcoes = {"Planejar Viagem", "Sair"};
        int escolha = JOptionPane.showOptionDialog(
                null, 
                "Oque você deseja fazer?", 
                "Bem vindo!", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opcoes, 
                opcoes[0]);
        if (escolha == 0){
            _coletarInformações();
        }else{
            return;
        }
    }
    
    public static void _coletarInformações(){
        
        //=== Variáveis importantes 
        //== Variável de dados
        String nome;
        String dataViagem;
        String qntDias;
        String valorGasto;
        
        //== Variável de controle
        boolean validarInformacoes = true;
        
        // Controle de validação e entrada de dados
        do{
        nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");
        
        dataViagem = JOptionPane.showInputDialog(null, "Data da viagem no formato dd/MM/yyyy ");
        
        String correcaoData = dataViagem.replace("/", "-");
        LocalDate tipodata = LocalDate.parse(correcaoData);
        int ano = tipodata.getYear();
        int mes = tipodata.getMonthValue();
        int dia = tipodata.getDayOfMonth();
        
        qntDias = JOptionPane.showInputDialog(null, "Quantidade de dias de viagem: ");
        
        valorGasto = JOptionPane.showInputDialog(null, "Valor gasto por dia: ");
        
        if (_validaCampos(nome, dataViagem, qntDias, valorGasto) == true && Integer.parseInt(qntDias) > 0 && Double.parseDouble(valorGasto) > 0){
            System.out.println("passou");
        } else {
            JOptionPane.showMessageDialog(null, "Ops! Alguma informação está incorreta ou vazia! \n Vamos tentar novamente!");
            validarInformacoes = false;
        }
        }while(validarInformacoes == false);
    }
    
    public static boolean _validaCampos(String nome, String data, String dias, String valor){
        public static void _validarCamposVazios(){
        
            if(nome.isEmpty()){
                return false;
            } else if (data.isEmpty()){
                return false;
            } else if (dias.isEmpty()){
                return false;
            } else if (valor.isEmpty()){
                return false;
            } else {
                return true;
            }
        }
    }
    
    
}

