import java.util.Random;

/**
 * Classe da carta de aprisionamento (atordoado), que é filha da classe Carta
 */
public class CartaAprisionamento extends Carta {

    private Random rng = new Random();

    public CartaAprisionamento() {
        super("Aprisionamento", "40% de chance de impedir o inimigo", 2);
    }

    /**
     * Metodo de usa a carta, no qual tem 40% de chance de impedir que o inimigo faça o ataque no fim do turno
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {

        System.out.println("Voce tenta aprisionar o inimigo...");

        // Verifica se o numero aleatório (rng), e menor de 40 (para ser 40% de chance), e se for imprime a mensagem e deixa o inimigo atordoado
        if (rng.nextInt(100) < 40) {
            System.out.println("O inimigo foi aprisionado e nao ira agir!");
            inimigo.setAtordoado(true);
        } else {
            System.out.println("O ritual falhou!");
        }
    }
}
