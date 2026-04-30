/**
 * Classe que representa a carta de escudo do jogo, que é filha da classe Carta
 */
public class CartaEscudo extends Carta {

    private int escudo;

    public CartaEscudo() {
        super("Escudo", "Ganha 5 de escudo", 1);
        this.escudo = 5;
    }

    /**
     * Metodo que usa a carta de escudo mesmo, fazendo o investigador ganhar 5 de escudo
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        heroi.ganharEscudo(escudo);
    }

    /**
     * Metodo que aumenta o escudo ganho em 1
     */
    @Override
    public void melhorar() {
        escudo += 1;
        descricao = "Ganha " + escudo + " de escudo";
    }
}
