
package planejamentodeviagem;

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
        }else{
            return;
        }
    }
    
    public static void _coletarInformações(){
        
        //=== Variáveis importantes 
        //== Variável de dados
        String nome, dataViagem, qntDias, valorGasto;
        
        //== Variável de controle
        boolean validarInformacoes = true;
        
        // Controle de validação e entrada de dados
        do{
            
            nome = JOptionPane.showInputDialog(null, "Digite seu nome: ");

            dataViagem = JOptionPane.showInputDialog(null, "Data da viagem no formato dd/MM/AAAA ");
            LocalDate FdataViagem = _formatarParaLocalDate(dataViagem);

            qntDias = JOptionPane.showInputDialog(null, "Quantidade de dias de viagem: ");
            int FqntDias = _formatarParaInt(qntDias);

            valorGasto = JOptionPane.showInputDialog(null, "Valor gasto por dia: ");
            double FvalorGasto = _formatarParaDouble(valorGasto);

            if (_validaCamposVazios(nome, dataViagem, qntDias, valorGasto) && _verificarValoresNegativos(FqntDias) && _verificarValoresNegativos(FvalorGasto)){
                _processarDados(FqntDias, FvalorGasto, FdataViagem, nome);
            } else {
                JOptionPane.showMessageDialog(null, "Ops! Alguma informação está incorreta ou vazia! \n Vamos tentar novamente!");
                validarInformacoes = false;
            }
        
        }while(validarInformacoes == false);
    }
    
    public static void _processarDados(int qntDias, double valorGasto, LocalDate dataUsuario, String nome){
        double valorTotal = qntDias * valorGasto;
        String mensagemRetorno;
        
        LocalDate dataHoje = LocalDate.now();
        if (dataUsuario.equals(dataHoje)){
            mensagemRetorno = ", sua viagem é hoje!";
        } if (dataUsuario.isBefore(dataHoje)){
            mensagemRetorno = (", sua data já passou!\nEra em: " + dataUsuario);
        } else {
            long faltamDias = ChronoUnit.DAYS.between(dataHoje, dataUsuario);
            mensagemRetorno = (", sua viagem será em " + dataUsuario.getDayOfMonth() + "/" + dataUsuario.getMonthValue() + "/" + dataUsuario.getYear() + "\nFaltam: " + faltamDias + " dias.");
        }
        
        JOptionPane.showMessageDialog(null, (nome + mensagemRetorno + "\nTotal estimado: R$" + valorTotal));
    }
    
    public static LocalDate _formatarParaLocalDate(String valor){
        if (valor.isEmpty()){
            return LocalDate.of(0, 0, 0);
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
    
    public static boolean _verificarValoresNegativos(int valor){
        if (valor > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean _verificarValoresNegativos(double valor){
        if (valor > 0){
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean _validaCamposVazios(String nome, String data, String dias, String valor){
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