/**
 * Classe que presenta a carta de Frasco de Lodo que é filha da classe Carta
 */
public class CartaFrascoLodo extends Carta {

    private int cura;

    public CartaFrascoLodo() {
        super("Frasco de Lodo", "Recupera 5 de vida", 1);
        this.cura = 5;
    }

    /**
     * Metodo que usa a carta, onde cura 5 pontos de vida do heroi
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce bebe o Frasco de Lodo e recupera " + cura + " de vida.");
        heroi.curar(cura);
    }

    /**
     * Metodo que melhora a carta, aumentando a recuperação de vida em 1
     */
    @Override
    public void melhorar() {
        cura += 1;
        descricao = "Recupera " + cura + " de vida";
    }
}
