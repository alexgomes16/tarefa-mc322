/**
 * Classe que representa a carta de sangramento do jogo, que é filha da classe Carta
 */
public class CartaSangramento extends Carta {

    public CartaSangramento() {
        super("Sangramento", "Aplica sangramento (1)", 1);
    }

    /**
     * Metodo em que aplica o efeito sangramento e imprime a mensagem avisando o jogador
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {

        System.out.println("Voce causa sangramento no " + inimigo.getNome() + "!");

        Efeito efeito = new EfeitoSangramento(inimigo, App.publisher, 1);
        inimigo.aplicarEfeito(efeito);
    }
}
