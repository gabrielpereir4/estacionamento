package negocio;

import controle.EstacionamentoController;

/** Representa informações sobre quantidade de vagas no estacionamento e sua disponibilidade
 *
 * @author gabri
 */
public class Vaga {
    public static int TOTAL_VAGAS = 100;
    private static int vagas_ocupadas = inicializarOcupadas();

    public static void registrarSaida() {
        Vaga.vagas_ocupadas -= 1;    
    }
    
    private Vaga(){}
    
    /**
     * Verifica se existe disponibilidade de vagas no estacionamento
     * @return true, se houver vaga; e false, se não.
     */
    public static boolean vagaLivre(){
        return (vagas_ocupadas < TOTAL_VAGAS);
    }
    
    /**
     * Buscar o status atual das vagas do sistema no controller
     * @return o número de vagas ocupadas conforme BD
     */
    public static int inicializarOcupadas(){
        EstacionamentoController controller = new EstacionamentoController();
        return controller.inicializarOcupadas();
    }

    public static int getVagasOcupadas() {
        return vagas_ocupadas;
    }
    
    /**
     * Busca o número de vagas livres, conforme ocupadas e total
     * @return o número de vagas livres, conforme cálculo
     */
    public static int getVagasLivres(){
        return TOTAL_VAGAS - Vaga.vagas_ocupadas;
    }
    
    /**
     * Atualiza o número de vagas ocupadas
     */
    public static void registrarEntrada(){
        Vaga.vagas_ocupadas += 1;
    }
}
