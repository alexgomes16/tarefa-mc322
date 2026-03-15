// Classe heroi que é filho da classe Entidade

public class Heroi extends Entidade {
    private final int vidaBase;
    private final int escudoBase;

    public Heroi(int vidaInicial, int escudoInicial) {
        super("Investigador", vidaInicial, escudoInicial);
        this.vidaBase = vidaInicial;
        this.escudoBase = escudoInicial;
    }

    // Esse metodo é para entre as batalhas o heroi recuperar sua vida e seu escudo inicial
    public void restaurarBase() {
        this.vida = vidaBase;
        this.escudo = escudoBase;
        System.out.println("Investigador restaura seus status para vida: " + vida + " | escudo: " + escudo);
    }
}
