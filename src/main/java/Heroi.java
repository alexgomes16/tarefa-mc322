import java.util.ArrayList;

/**
 * Está é a classe de Heroi que herda a classe Entidade
 */
public class Heroi extends Entidade {

    private final int vidaMaxima;

    private int energia;
    private int energiaMaximaBatalha = 3;

    private int energiaProximaBatalha = 3;
    private int escudoInicialProximaBatalha = 0;
    private int bonusPrimeiroTiroProximaBatalha = 0;
    private int bonusSangramentoProximaBatalha = 0;
    private int redutorSangramentoProximaBatalha = 0;
    private int redutorDanoProximaBatalha = 0;

    private int bonusPrimeiroTiroAtivo = 0;
    private int bonusSangramentoAtivo = 0;
    private int redutorSangramentoAtivo = 0;
    private int redutorDanoAtivo = 0;

    public Heroi(int vida, int escudo) {
        super("Investigador", vida, escudo);
        this.vidaMaxima = vida;
    }

    /**
     * Metodo que inicia uma nova batalha aplicando os efeitos de itens da proxima batalha
     */
    public void iniciarBatalha() {
        energiaMaximaBatalha = energiaProximaBatalha;
        energia = energiaMaximaBatalha;
        escudo = escudoInicialProximaBatalha;

        bonusPrimeiroTiroAtivo = bonusPrimeiroTiroProximaBatalha;
        bonusSangramentoAtivo = bonusSangramentoProximaBatalha;
        redutorSangramentoAtivo = redutorSangramentoProximaBatalha;
        redutorDanoAtivo = redutorDanoProximaBatalha;

        energiaProximaBatalha = 3;
        escudoInicialProximaBatalha = 0;
        bonusPrimeiroTiroProximaBatalha = 0;
        bonusSangramentoProximaBatalha = 0;
        redutorSangramentoProximaBatalha = 0;
        redutorDanoProximaBatalha = 0;
    }

    /**
     * Metodo que reinicia a energia no começo de cada turno da batalha atual
     */
    public void iniciarTurno() {
        energia = energiaMaximaBatalha;
    }

    /**
     * Metodo que finaliza os bonus temporarios da batalha atual, usado para quando acabar a batalha
     */
    public void finalizarBatalha() {
        bonusPrimeiroTiroAtivo = 0;
        bonusSangramentoAtivo = 0;
        redutorSangramentoAtivo = 0;
        redutorDanoAtivo = 0;
        energia = 0;
        energiaMaximaBatalha = 3;
    }

    /**
     * Retorna a energia do heroi
     * 
     * @return energia do heroi
     */
    public int getEnergia() {
        return energia;
    }

    /**
     * Metodo que gasta a energia do heroi, quando usa uma carta tira esse custo da carta da energia do heroi (se ele tiver energia para usar essa carta)
     * 
     * @param custo Custo da carta usada
     */
    public boolean gastarEnergia(int custo) {
        if (energia >= custo) {
            energia -= custo;
            return true;
        }
        return false;
    }

    /**
     * Metodo que faz o heroi ganhar energia
     * 
     * @param valor quantidade que aumenta a energia
     */
    public void ganharEnergia(int valor) {
        energia += valor;
        System.out.println("Energia aumentada em +" + valor + "!");
    }

    /**
     * Metodo que cura o heroi
     * 
     * @param valor quantidade que o heroi cura
     */
    public void curar(int valor) {
        int vidaAnterior = vida;
        vida = Math.min(vidaMaxima, vida + valor);
        int recuperado = vida - vidaAnterior;
        System.out.println("Investigador recupera " + recuperado + " de vida.");
    }

    /**
     * Metodo que cura o heroi, sem a mensagem de cura (isso é para antes da batalha final, onde se cura sem a mensagem tradicional de cura)
     * 
     * @param valor quantidade que o heroi cura
     */
    public void curarSemMensagem(int valor) {
        vida = Math.min(vidaMaxima, vida + valor);
    }

    /**
     * Metodo que consome o bonus do primeiro tiro da batalha (bala amaldiçoada e colar da sorte)
     */
    public int consumirBonusPrimeiroTiro() {
        int total = bonusPrimeiroTiroAtivo;
        bonusPrimeiroTiroAtivo = 0;

        for (Efeito efeito : new ArrayList<>(efeitos)) {
            if (efeito instanceof EfeitoBalaAmaldicoada) {
                total += ((EfeitoBalaAmaldicoada) efeito).consumirBonus();
                break;
            }
        }

        return total;
    }

    public int getRedutorSangramentoRecebido() {
        return redutorSangramentoAtivo;
    }

    public int getBonusSangramentoCausado() {
        return bonusSangramentoAtivo;
    }

    public int getRedutorDanoRecebido() {
        return redutorDanoAtivo;
    }

    public void setEnergiaProximaBatalha(int valor) {
        energiaProximaBatalha = valor;
    }

    public void setEscudoInicialProximaBatalha(int valor) {
        escudoInicialProximaBatalha = valor;
    }

    public void setBonusPrimeiroTiroProximaBatalha(int valor) {
        bonusPrimeiroTiroProximaBatalha = valor;
    }

    public void setBonusSangramentoProximaBatalha(int valor) {
        bonusSangramentoProximaBatalha = valor;
    }

    public void setRedutorSangramentoProximaBatalha(int valor) {
        redutorSangramentoProximaBatalha = valor;
    }

    public void setRedutorDanoProximaBatalha(int valor) {
        redutorDanoProximaBatalha = valor;
    }

    public void restaurarBase() {
        this.vida = vidaMaxima;
        this.escudo = 1;
    }

    /**
     * Metodo que consome os bonus da bala amaldiçoada
     */
    public int consumirBonusDano() {
        return consumirBonusPrimeiroTiro();
    }
}