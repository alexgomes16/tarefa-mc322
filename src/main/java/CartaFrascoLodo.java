/**
 * Classe que presenta a carta de Frasco de Lodo que é filha da classe Carta
 */
public class CartaFrascoLodo extends Carta {

    public CartaFrascoLodo() {
        super("Frasco de Lodo", "Recupera 5 de vida", 1);
    }

    /**
     * Metodo que usa a carta, onde cura 5 pontos de vida do heroi
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce bebe o Frasco de Lodo e recupera 5 de vida.");
        heroi.curar(5);
    }
}
