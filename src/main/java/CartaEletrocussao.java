/**
 * Classe que representa a carta de Eletrocussão que é filha da classe Carta
 */
public class CartaEletrocussao extends Carta {

    public CartaEletrocussao() {
        super("Eletrocussao", "Causa 8 de dano", 3);
    }

    /**
     * Metodo que usa carta, no qual da um dano de 8 pontos no inimigo
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce invoca um ritual de eletrocussao!");
        inimigo.receberDano(8);
    }
}