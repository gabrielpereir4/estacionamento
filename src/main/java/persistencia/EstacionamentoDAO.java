/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import controle.EstacionamentoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import negocio.Movimentacao;
import negocio.Vaga;
import negocio.Veiculo;
import utilitario.EstacionamentoUtil;

/**
 *
 * @author gabri
 */
public class EstacionamentoDAO {
    
    /**
    * Cria a movimentação no banco de dados, atualizando também o número de vagas ocupadas
    * @param movimentacao a movimentação a ser criada no BD
    * @throws EstacionamentoException se houver erro ao atualizar o banco de dados
    */
    public void criar(Movimentacao movimentacao) throws EstacionamentoException{
        String cmd1 = EstacionamentoUtil.get("insertMov"); // Comando de inserir movimentação
        String cmd2 = EstacionamentoUtil.get("atualizaVaga"); // Comando de atualizar as vagas
        
        Connection conexao = null;
        try {
            conexao = getConnection();

            conexao.setAutoCommit(false); // Marca controle das transações SQL como "manual"

            PreparedStatement stmt = conexao.prepareStatement(cmd1);
            stmt.setString(1, movimentacao.getVeiculo().getPlaca());
            stmt.setString(2, movimentacao.getVeiculo().getMarca());
            stmt.setString(3, movimentacao.getVeiculo().getModelo());
            stmt.setString(4, movimentacao.getVeiculo().getCor());
            stmt.setString(5, EstacionamentoUtil.getDataAsString(movimentacao.getEntrada()));

            stmt.execute();

            stmt = conexao.prepareStatement(cmd2);
            stmt.setInt(1, Vaga.getVagasOcupadas() + 1);

            stmt.execute();

            conexao.commit();
        }
        catch (SQLException e){
            try {
                conexao.rollback();
                throw new EstacionamentoException("Erro ao registrar veículo");
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
    
    /**
    * Atualiza dados de saída e do valor de uma movimentação que está sendo encerrada
    * @param  movimentacao movimentacao com os dados atualizados
    * @throws EstacionamentoException se houver erro ao atualizar o banco de dados
    */
    public void atualizar(Movimentacao movimentacao) throws EstacionamentoException{
        String cmd = EstacionamentoUtil.get("updateMov");
        String cmd2 = EstacionamentoUtil.get("atualizaVaga"); // Comando de atualizar as vagas
        Connection conexao = null;
        try {
            conexao = getConnection();

            conexao.setAutoCommit(false); // Marca controle das transações SQL como "manual"

            PreparedStatement stmt = conexao.prepareStatement(cmd);
            stmt.setDouble(1, movimentacao.getValor());
            stmt.setString(2, EstacionamentoUtil.getDataAsString(movimentacao.getSaida()));
            stmt.setString(3, movimentacao.getVeiculo().getPlaca());

            stmt.execute();
            
            stmt = conexao.prepareStatement(cmd2);
            stmt.setInt(1, Vaga.getVagasOcupadas() - 1);

            stmt.execute();

            conexao.commit();
        }
        catch (SQLException e){
            try {
                conexao.rollback();
                throw new EstacionamentoException("Erro ao registrar veículo");
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
    
    /**
    * Busca uma movimentação já aberta, pela placa do veículo
    * @param placa a placa a ser buscada
    * @return movimentação conforme busca, ou null, caso não encontrado
    */
    public Movimentacao buscarMovimentacaoPorPlaca(String placa){
        String cmd = EstacionamentoUtil.get("getMovAberta");
        Movimentacao movimentacao = null;
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement ps = conexao.prepareStatement(cmd);
            ps.setString(1, placa);
            
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()){
                String rplaca = resultado.getString("placa");
                String data_entrada = resultado.getString("data_entrada");
                Veiculo veiculo = new Veiculo(rplaca);
                movimentacao = new Movimentacao(veiculo, EstacionamentoUtil.getData(data_entrada));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnection(conexao);
        }
        return movimentacao;
    }
    
    /**
    * Retorna todas as movimentações do mês informado
    * @param data data inicial do mês
    * @return uma lista com as movimentações encontradas
    */
    public List<Movimentacao> consultarMovimentacao(LocalDateTime data){
        Connection conexao = null;
        List<Movimentacao> movimentacoes = new ArrayList<>();

        try {
            conexao = getConnection();
            String cmd = EstacionamentoUtil.get("selectMovRelatorio");
            PreparedStatement ps = conexao.prepareStatement(cmd);
            ps.setString(1, data.toString());
           
            ps.setString(2, data.with(TemporalAdjusters.lastDayOfMonth()).toString());
            
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                String placa = resultado.getString("placa");
                LocalDateTime entrada = EstacionamentoUtil.getData(resultado.getString("data_entrada"));
                LocalDateTime saida = EstacionamentoUtil.getData(resultado.getString("data_saida"));
                double valor = resultado.getDouble("valor");
                Veiculo veiculo = new Veiculo(placa);
                Movimentacao movimentacao = new Movimentacao(veiculo, entrada);
                movimentacao.setSaida(saida);
                movimentacao.setValor(valor);
                
                movimentacoes.add(movimentacao);
            }
        } 
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnection(conexao);
        }
        return movimentacoes;
    }
    
    /**
    * Cria uma conexão com o banco de dados
    * @return o objeto da conexão
    */
    public static Connection getConnection(){
        
            String url = EstacionamentoUtil.get("url");
            String user = EstacionamentoUtil.get("user");
            String senha = EstacionamentoUtil.get("senha");
        try {    
            Connection conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
    * Fecha conexão com o banco de dados
    * @param conexao a conexão a ser encerrada
    */
    public static void closeConnection(Connection conexao){
        if (conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
    * Retorna o número de vagas ocupadas, conforme banco de dados
    * @return inteiro com o número de vagas ocupadas registradas no BD
    */
    public int getOcupadas() {
        int ocupadas = 0;
        String cmd = EstacionamentoUtil.get("ocupadas");
        Connection conexao = null;
        try {
            conexao = getConnection();
            PreparedStatement ps = conexao.prepareStatement(cmd);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                ocupadas = resultado.getInt("ocupadas");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnection(conexao);
        }
        return ocupadas;
    }
}
