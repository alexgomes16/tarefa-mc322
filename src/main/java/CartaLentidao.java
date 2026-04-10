/**
 * Classe da carta de lentidão, que é filha da classe Carta
 */
public class CartaLentidao extends Carta {

    public CartaLentidao() {
        super("Lentidao", "Reduz dano do inimigo em 1", 1);
    }

    /**
     * Metodo que usa a Carta de lentidão, usando o efeito de lentidão
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {

        System.out.println("Voce realiza o ritual deixando o inimigo com lentidao!");

        inimigo.aplicarEfeito(new EfeitoLentidao(inimigo, App.publisher, 1));
    }
}
