/**
 * Classe que representa a carta de dano do jogo, que é filha da classe Carta
 */
public class CartaDano extends Carta {

    public CartaDano() {
        super("Tiro", "Causa 5 de dano", 2);
    }

    /**
     * Metodo que da o dano no inimigo (com os bonus, se tiver), e imprime uma mensagem para o jogador
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {

        int dano = 5 + heroi.consumirBonusDano();

        System.out.println("Voce dispara e causa " + dano + " de dano!");
        inimigo.receberDano(dano);
    }
}
