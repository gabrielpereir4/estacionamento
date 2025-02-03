/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import java.time.LocalDateTime;
import java.util.List;
import negocio.Movimentacao;
import negocio.Vaga;
import negocio.Veiculo;
import persistencia.EstacionamentoDAO;
import utilitario.EstacionamentoUtil;

/**
 *
 * @author gabri
 * 
 * Classe da Camada de Controle
 */
public class EstacionamentoController {
    
    /**
    * Registra a movimentação de entrada de um veículo no estacionamento, conforme informado pelo usuário
    * @throws EstacionamentoException quando estacionamento estiver lotado
    * @throws VeiculoException quando placa for inválida
    */
    public void processarEntrada(String placa, String marca, String modelo, String cor) throws EstacionamentoException,
            VeiculoException{
        // Verifica se o estacionamento está lotado
        if (!Vaga.vagaLivre()){
            throw new EstacionamentoException("Estacionamento lotado.");
        }
        // Verifica Padrão da Placa
        if (!EstacionamentoUtil.validarPlaca(placa)){
            throw new VeiculoException("Placa inválida.");
        }
        // Cria uma instância de veículo, com o momento de entrada
        Veiculo veiculo = new Veiculo(placa, marca, modelo, cor);
        
        Movimentacao movimentacao = new Movimentacao(veiculo, LocalDateTime.now());
        
        // Registar no Banco de Dados
        EstacionamentoDAO dao = new EstacionamentoDAO();
        dao.criar(movimentacao);
        
        // Atualizar número de vagas ocupadas
        Vaga.registrarEntrada();
    }
    
    /**
    * Processa a saída de um veículo do estacionamento.
    *
    * @throws VeiculoException se a placa informada tiver um padrão inválido.
    * @throws EstacionamentoException se a placa informada não for encontrada no estacionamento.
    * @return A movimentação com as informações atualizadas.
    */
    public Movimentacao processarSaida(String placa) throws VeiculoException, EstacionamentoException{
        // Validar Placa
        if(!EstacionamentoUtil.validarPlaca(placa)){
            throw new VeiculoException("Placa inválida");
        }
        // Buscar Movimentação Existente
        EstacionamentoDAO dao = new EstacionamentoDAO();
        Movimentacao movimentacao = dao.buscarMovimentacaoPorPlaca(placa);
        if (movimentacao == null){
            throw new EstacionamentoException("Veículo não encontrado.");
        }
        movimentacao.setSaida(LocalDateTime.now());
        // Calcular Valor a ser Pago
        EstacionamentoUtil.calcularPagamento(movimentacao);
        // Atualizar os Dados da Movimentação no BD
        dao.atualizar(movimentacao);
        // Atualizar as Vagas Ocupadas
        Vaga.registrarSaida();
        return movimentacao;
    }
    /**
    * Emite relatório conforme data inicial informada, até o final do mês
    * @param data a data inicial do mês
    * @return uma lista com todas as movimentações encontradas
    */
    public List<Movimentacao> emitirRelatorio(LocalDateTime data){
        EstacionamentoDAO dao = new EstacionamentoDAO();
        return dao.consultarMovimentacao(data);
    }
    
    /**
    * Inicializa o número de vagas ocupadas conforme registrado no BD
    * @return o número de vagas ocupadas
    */
    public int inicializarOcupadas() {
        EstacionamentoDAO dao = new EstacionamentoDAO();
        return dao.getOcupadas();
    }
}
