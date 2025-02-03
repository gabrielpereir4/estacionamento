/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.time.LocalDateTime;

/**
 *  Representa o fluxo do veículo no estacionamento, com horário de entrada e saída e valor do serviço
 *
 * @author gabri
 */
public class Movimentacao {
    private Veiculo veiculo; // Agregação
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valor;

    // Ao iniciar a Movimentação, não se utiliza a hora de saída nem o valor
    public Movimentacao(Veiculo veiculo, LocalDateTime entrada) {
        this.veiculo = veiculo;
        this.entrada = entrada;
    }
    

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public double getValor() {
        return valor;
    }
}
