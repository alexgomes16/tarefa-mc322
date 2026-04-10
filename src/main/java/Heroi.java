/**
 * Está é a classe de Heroi que herda a classe Entidade
 */
public class Heroi extends Entidade {
    private int energia;

    public Heroi(int vida, int escudo) {
        super("Investigador", vida, escudo);
    }

    /**
     * Metodo que no inicio dos turnos retomam a energia do heroi e zeram o escudo ganho
     */
    public void iniciarTurno() {
        energia = 3;
        zerarEscudo();
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
     * Metodo que consome os bonus da bala amaldiçoada
     * 
     * @return efeito reduzido (consumido)
     */
    public int consumirBonusDano() {
        for (Efeito efeito : efeitos) {
            if (efeito instanceof EfeitoBalaAmaldicoada) {
                return ((EfeitoBalaAmaldicoada) efeito).consumirBonus();
            }
        }
        return 0;
    }

    /**
     * Metodo que restaura os status iniciais do heroi
     */
    public void restaurarBase() {
        this.vida = 20;
        this.escudo = 1;
    }
}
