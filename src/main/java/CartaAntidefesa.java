/**
 * Classe da carta de Antidefesa, que é filha da classe Carta
 */
public class CartaAntidefesa extends Carta {

    private int reducao;

    public CartaAntidefesa() {
        super("Ritual de Antidefesa", "Tira 2 de escudo do inimigo", 1);
        this.reducao = 2;
    }

    /**
     * Metodo que usa a carta, retirando 2 pontos de escudo do inimigo
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce enfraquece a defesa do inimigo!");
        inimigo.reduzirEscudo(reducao);
    }

    /**
     * Metodo que melhora essa carta, onde aumenta a redução no escudo do inimigo
     */
    @Override
    public void melhorar() {
        reducao += 1;
        descricao = "Tira " + reducao + " de escudo do inimigo";
    }
}
