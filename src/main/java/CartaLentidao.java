/**
 * Classe da carta de lentidão, que é filha da classe Carta
 */
public class CartaLentidao extends Carta {

    public CartaLentidao() {
        super("Ritual de Lentidao", "Reduz em 1 o proximo ataque do inimigo", 1);
    }

    /**
     * Metodo que usa a Carta de lentidão, usando o efeito de lentidão (reduz 1 de dano no ataque do inimigo)
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce enfraquece os movimentos do " + inimigo.getNome() + "!");
        inimigo.aplicarLentidao(1);
    }
}
