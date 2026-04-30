/**
 * Classe que representa a carta de dano do jogo, que é filha da classe Carta
 */
public class CartaDano extends Carta {

    private int dano = 5;

    public CartaDano() {
        super("Tiro", "Causa 5 de dano", 2);
    }

    /**
     * Metodo que da o dano no inimigo (com os bonus, se tiver), e imprime uma mensagem para o jogador
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        // Pega o bonus da bala amaldiçoada e aumenta no dano do heroi e imprime a mensagem do ataque do heroi (além do bonus do item de colar da sorte que aumenta em 2 o dano do primeiro tiro)
        int bonus = heroi.consumirBonusPrimeiroTiro();
        int danoFinal = dano + bonus;
        System.out.println("Voce usa " + nome + " e causa " + danoFinal + " de dano!");

        // Imprime o bonus da bala amaldiçoada para o jogador
        if (bonus > 0) {
            System.out.println("A bala amaldicoada aumenta o dano em +" + bonus + "!");
        }

        // Então da o dano no inimigo
        inimigo.receberDano(danoFinal);
    }

    /**
     * Metodo que melhora a carta, aumentando o dano em 1
     */
    @Override
    public void melhorar() {
        dano += 1;
        descricao = "Causa " + dano + " de dano";
    }
}
