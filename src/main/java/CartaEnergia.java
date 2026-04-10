/**
 * Classe da carta de energia, onde é filha da classe Carta
 */
public class CartaEnergia extends Carta {

    public CartaEnergia() {
        super("Alimento Energetico", "Ganha +2 de energia", 0);
    }

    /**
     * Metodo que usa a carta, dando +2 de energia ao heroi
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce consome um alimento energetico!");
        heroi.ganharEnergia(2);
    }
}
