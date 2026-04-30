/**
 * Classe que representa a carta de Eletrocussão que é filha da classe Carta
 */
public class CartaEletrocussao extends Carta {

    private int dano;

    public CartaEletrocussao() {
        super("Ritual de Eletrocussao", "Causa 8 de dano", 3);
        this.dano = 8;
    }

    /**
     * Metodo que usa carta, no qual da um dano de 8 pontos no inimigo
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce invoca um ritual de eletrocussao!");
        inimigo.receberDano(dano);
    }

    /**
     * Metodo que melhora a carta, onde aumenta o dano da carta
     */
    @Override
    public void melhorar() {
        dano += 1;
        descricao = "Causa " + dano + " de dano";
    }
}
