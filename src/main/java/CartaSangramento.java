/**
 * Classe que representa a carta de sangramento do jogo, que é filha da classe Carta
 */
public class CartaSangramento extends Carta {

    private int sangramento;

    public CartaSangramento() {
        super("Sangramento", "Aplica 1 de sangramento", 1);
        this.sangramento = 1;
    }

    /**
     * Metodo em que aplica o efeito sangramento e imprime a mensagem avisando o jogador
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        int sangramentoFinal = sangramento + heroi.getBonusSangramentoCausado();
        System.out.println("Voce causa sangramento no " + inimigo.getNome() + "!");
        if (heroi.getBonusSangramentoCausado() > 0) {
            System.out.println("A Faca Afiada aumenta o sangramento em +" + heroi.getBonusSangramentoCausado() + "!");
        }
        inimigo.aplicarEfeito(new EfeitoSangramento(inimigo, App.publisher, sangramentoFinal));
    }

    /**
     * Metodo que melhora a carta, onde aumenta o dano de sangramento em +1
     */
    @Override
    public void melhorar() {
        sangramento += 1;
        descricao = "Aplica " + sangramento + " de sangramento";
    }
}
