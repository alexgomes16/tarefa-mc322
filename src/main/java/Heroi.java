/**
 * Está é a classe de Heroi que herda a classe Entidade
 */
public class Heroi extends Entidade {

    private final int vidaMaxima;
    private int energia;

    public Heroi(int vida, int escudo) {
        super("Investigador", vida, escudo);
        this.vidaMaxima = vida;
    }

    /**
     * Metodo que no inicio dos turnos retomam a energia do heroi
     */
    public void iniciarTurno() {
        energia = 3;
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
        this.vida = vidaMaxima;
        this.escudo = 1;
    }

    /**
     * Metodo que cura o heroi, sem a mensagem de cura (isso é para antes da batalha final, onde se cura sem a mensagem tradicional de cura)
     * 
     * @param valor quantidade que o heroi cura
     */
    public void curarSemMensagem(int valor) {
        vida = Math.min(vidaMaxima, vida + valor);
    }
}
