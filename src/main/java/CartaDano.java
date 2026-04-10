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
        // Pega o bonus da bala amaldiçoada e aumenta no dano do heroi e imprime a mensagem do ataque do heroi
        int bonus = heroi.consumirBonusDano();
        int danoFinal = 5 + bonus;
        System.out.println("Voce usa " + nome + " e causa " + danoFinal + " de dano!");

        // Imprime o bonus da bala amaldiçoada para o jogador
        if (bonus > 0) {
            System.out.println("A bala amaldiçoada aumenta o dano em +" + bonus + "!");
        }

        // Então da o dano no inimigo
        inimigo.receberDano(danoFinal);
    }
}
