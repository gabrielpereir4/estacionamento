/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import negocio.Movimentacao;
import negocio.Tarifario;

/**
 * Classe de apoio para cálculos e validações
 *
 * @author gabri
 */
public class EstacionamentoUtil {
    
    
    /**
     * Valida placas com padrão nacional
     * @param placa placa do veículo
     * @return true se padrão, false caso contrário
     */
    public static boolean validarPlaca(String placa){
        String padrao = "^[A-Z]{3}[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
        Pattern p = Pattern.compile(padrao);
        Matcher m = p.matcher(placa);
        
        return m.matches();
    }
    
    /**
     * Calcula valor total da estadia de um veículo
     * Não retorna valor, altera a própria instância do parâmetro
     * @param movimentacao a movimentação a ser calculada
     */
    public static void calcularPagamento(Movimentacao movimentacao){
        LocalDateTime entrada = movimentacao.getEntrada();
        LocalDateTime saida = movimentacao.getSaida();
        
        double valor = 0;
        
        // Cálculo da Primeira Hora
        long diff_horas = entrada.until(saida, ChronoUnit.HOURS);
        if (diff_horas > 0){
            valor += Tarifario.VALOR_HORA;
            saida = saida.minus(1, ChronoUnit.HOURS);
        }
        
        // Cálculo do Restante dos minutos proporcionais
        long diff_minutos = entrada.until(saida, ChronoUnit.MINUTES);
        valor += (diff_minutos/Tarifario.INCREMENTO_MINUTOS) * Tarifario.VALOR_INCREMENTAL;
        
        movimentacao.setValor(valor);
    }
    
    /**
    * Recupera informações de um arquivo de configuração
    * (configuration.txt)
    * @param propriedade o nome do comando/propriedade a ser recuperada de config.txt
    * @return uma string com as respectivas informações/comandos, ou null, se não encontrado
    */
    public static String get(String propriedade){
        Properties properties = new Properties();
        try {
            properties.load(EstacionamentoUtil.class.getResourceAsStream("/config.txt"));
            String valor = properties.getProperty(propriedade);
            return valor;
        }
        catch (Exception e){
            assert false : "Configuração não carregada";
        }
        return null;
    }
    
    /**
    * Retorna uma string a partir de uma data
    * @param entrada a data a ser transformada
    * @return String respectiva do LocalDateTime
    */
    public static String getDataAsString(LocalDateTime entrada) {
        return entrada.toString();
    }
    
    /**
    * Retorna uma data a partir de uma string
    * @param string data em formato de string
    * @return LocalDateTime respectivo da string
    */
    public static LocalDateTime getData(String string){
        // Padrão MySQL
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getDisplayData(LocalDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("dd//MM/yyyy HH:mm"));
    }
    
    /**
    * Gera texto de faturamento total do Mês e Ano para a Tela de Relatório
    * @param data o Mês e Ano do relatório
    * @param movimentacoes a lista de movimentações recuperadas no período
    *@return String com o texto a ser exibido
    */
    public static String gerarTextoFaturamento(LocalDateTime data, List<Movimentacao> movimentacoes) {
        double totalFaturado = 0;
        String textoFaturado = "";
        for(Movimentacao movimentacao : movimentacoes){
            totalFaturado += movimentacao.getValor();
        }
        String sano = ""+ data.getYear();
        String smes = ""+ data.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        
        textoFaturado = "Faturamento do Mês de " + smes;
        textoFaturado += " de " + sano + " foi de R$ " + totalFaturado;
        
        return textoFaturado;
    }
}
