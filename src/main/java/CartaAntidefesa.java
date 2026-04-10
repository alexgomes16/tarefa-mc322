/**
 * Classe da carta de Antidefesa, que é filha da classe Carta
 */
public class CartaAntidefesa extends Carta {

    public CartaAntidefesa() {
        super("Antidefesa", "Remove 2 de escudo do inimigo", 1);
    }

    /**
     * Metodo que usa a carta, retirando 2 pontos de escudo do inimigo
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        // Imprime a mensagem que usou a carta para o usuario e pega o escudo do inimigo
        System.out.println("Voce enfraquece a defesa do inimigo!");
        int escudo = inimigo.getEscudo();

        // Verifica se o inimigo tem escudo, e se tiver faz essa redução
        if (escudo > 0) {
            int removido = Math.min(2, escudo);
            inimigo.escudo -= removido;

            System.out.println("Escudo reduzido em " + removido + "!");
        } else {
            System.out.println("O inimigo nao possui escudo.");
        }
    }
}
