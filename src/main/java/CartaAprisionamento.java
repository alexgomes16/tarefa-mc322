import java.util.Random;

/**
 * Classe da carta de aprisionamento (atordoado), que é filha da classe Carta
 */
public class CartaAprisionamento extends Carta {

    private final Random rng = new Random();
    private int chance;

    public CartaAprisionamento() {
        super("Ritual de Aprisionamento", "40% de chance de impedir a acao do inimigo", 2);
        this.chance = 40;
    }

    /**
     * Metodo de usa a carta, no qual tem 40% de chance de impedir que o inimigo faça a ação no fim do turno
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce tenta aprisionar a criatura...");

        // Verifica se o numero aleatório (rng), e menor que a chance (essa chance pode aumentar, caso o jogador melhore essa carta), e se for imprime a mensagem e deixa o inimigo atordoado
        if (rng.nextInt(100) < chance) {
            System.out.println("O inimigo foi aprisionado e nao ira agir!");
            inimigo.setAtordoado(true);
        } else {
            System.out.println("O ritual falhou!");
        }
    }

    /**
     * Metodo que melhora a carta, onde aumenta 5% de chance do aprisionamento funcionar
     */
    @Override
    public void melhorar() {
        chance += 5;
        descricao = chance + "% de chance de impedir a acao do inimigo";
    }
}
