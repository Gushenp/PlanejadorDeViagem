
package planejamentodeviagem;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
        
        dataViagem = JOptionPane.showInputDialog(null, "Data da viagem no formato dd/MM/AAAA ");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataUsuario = LocalDate.parse(dataViagem, formatter);

        qntDias = JOptionPane.showInputDialog(null, "Quantidade de dias de viagem: ");
        int IqntDias = Integer.parseInt(qntDias);
        
        valorGasto = JOptionPane.showInputDialog(null, "Valor gasto por dia: ");
        double dvalorGasto = Double.parseDouble(valorGasto);
        
        if (_validaCampos(nome, dataViagem, qntDias, valorGasto) == true && Integer.parseInt(qntDias) > 0 && Double.parseDouble(valorGasto) > 0){
            _processarDados(IqntDias, dvalorGasto, dataUsuario, nome);
        } else {
            JOptionPane.showMessageDialog(null, "Ops! Alguma informação está incorreta ou vazia! \n Vamos tentar novamente!");
            validarInformacoes = false;
        }
        }while(validarInformacoes == false);
    }
    
    public static void _processarDados(int qntDias, double valorGasto, LocalDate dataUsuario, String nome){
        double valorTotal = qntDias * valorGasto;
        String mensagem = "";
        
        LocalDate dataHoje = LocalDate.now();
        System.out.println(dataHoje);
        System.out.println(dataUsuario);
        if (dataUsuario.equals(dataHoje)){
            mensagem = ", sua viagem é hoje!";
        } if (dataUsuario.isBefore(dataHoje)){
            mensagem = (", sua data já passou!\n Era em: " + dataUsuario);
        } else {
            long faltamDias = ChronoUnit.DAYS.between(dataHoje, dataUsuario);
            System.out.println(faltamDias);
            mensagem = (", sua viagem será em" + dataUsuario + "\nFaltam: " + faltamDias + " dias.");
        }
        
        JOptionPane.showMessageDialog(null, (nome + mensagem + "\nTotal estimado: R$" + valorTotal));
        
    }
    
    public static boolean _validaCampos(String nome, String data, String dias, String valor){
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