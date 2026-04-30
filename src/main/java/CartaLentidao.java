/**
 * Classe da carta de lentidão, que é filha da classe Carta
 */
public class CartaLentidao extends Carta {

    private int reducao;

    public CartaLentidao() {
        super("Ritual de Lentidao", "Reduz em 1 o proximo ataque do inimigo", 1);
        this.reducao = 1;
    }

    /**
     * Metodo que usa a Carta de lentidão, usando o efeito de lentidão (reduz 1 de dano no ataque do inimigo)
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce amaldicoa o inimigo com lentidao!");
        inimigo.aplicarLentidao(reducao);
    }

    /**
     * Metodo que melhora a carta, aumentando a redução de dano no ataque do inimigo em 1
     */
    @Override
    public void melhorar() {
        reducao += 1;
        descricao = "Reduz em " + reducao + " o proximo ataque do inimigo";
    }
}
