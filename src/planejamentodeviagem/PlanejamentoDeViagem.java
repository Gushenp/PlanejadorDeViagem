
package planejamentodeviagem;

import java.time.Clock;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PlanejamentoDeViagem {
    
    public static void main(String[] args) {
       planejadorViagem();
    }

    public static void planejadorViagem(){
        _menuInicial();
    }

    public static void _menuInicial(){
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
        }
    }
    
    public static void _coletarInformações(){
        
        //=== Variáveis importantes 
        //== Variável de dados
        String nome, dataViagem, qntDias, valorGasto;
        int FqntDias = 0;
        double FvalorGasto = 0.0;
        LocalDate FdataViagem = null;
      
        // Controle de validação e entrada de dados
        do {
            nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");
            if (nome.isBlank()){
                JOptionPane.showMessageDialog(null, "O campo NOME não pode estar vazio! \nVamos tentar novamente.");
            }
        }while(nome.isBlank());
        
        do {
            dataViagem = JOptionPane.showInputDialog(null, "Data da viagem no formato dd/MM/AAAA ");
            if (dataViagem.isBlank()){
                JOptionPane.showMessageDialog(null, "O campo DATA não pode estar vazio! \nVamos tentar novamente.");
            } else {
                FdataViagem = _formatarParaLocalDate(dataViagem);
            }
        }while(dataViagem.isBlank());

        do{
            qntDias = JOptionPane.showInputDialog(null, "Quantidade de dias de viagem: ");
            if (qntDias.isBlank()){
                JOptionPane.showMessageDialog(null, "O campo DIAS não pode estar vazio \nVamos tentar novamente.");
            } else {
                FqntDias = _formatarParaInt(qntDias);
                if (FqntDias < 0){
                    JOptionPane.showMessageDialog(null, "Os dias não podem ser negativos! \nVamos tentar novamente.");
                }
            }
        }while(qntDias.isBlank() || FqntDias < 0);    

        do{
            valorGasto = JOptionPane.showInputDialog(null, "Valor gasto por dia: ");
            if (valorGasto.isBlank()){
                JOptionPane.showMessageDialog(null, "O campo VALOR não pode estar vazio! \nVamos tentar novamente.");
            } else {
                FvalorGasto = _formatarParaDouble(valorGasto);
            }   
        }while(valorGasto.isBlank());       

        _processarDados(FqntDias, FvalorGasto, FdataViagem, nome);
    }
    
    public static void _processarDados(int qntDias, double valorGasto, LocalDate dataUsuario, String nome){
        double valorTotal = qntDias * valorGasto;
        String mensagemRetorno;
        
        LocalDate dataHoje = LocalDate.now();
        if (dataUsuario.equals(dataHoje)){
            mensagemRetorno = ", sua viagem é hoje!";
        } else if (dataUsuario.isBefore(dataHoje)){
            mensagemRetorno = (", sua data já passou!\nEra em: " + dataUsuario);
        } else {
            long faltamDias = ChronoUnit.DAYS.between(dataHoje, dataUsuario);
            mensagemRetorno = (", sua viagem será em " + dataUsuario.getDayOfMonth() + "/" + dataUsuario.getMonthValue() + "/" + dataUsuario.getYear() + "\nFaltam: " + faltamDias + " dias.");
        }
        
        JOptionPane.showMessageDialog(null, (nome + mensagemRetorno + "\nTotal estimado: R$" + valorTotal));
    }
    
    public static LocalDate _formatarParaLocalDate(String valor){
        if (valor.isEmpty()){
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(valor, formatter);
        }
    }
    
    public static int _formatarParaInt(String valor){
        return Integer.parseInt(valor);
    }
    
    public static double _formatarParaDouble(String valor){
        return Double.parseDouble(valor);
    }
}