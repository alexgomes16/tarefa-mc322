/**
 * Classe da carta de energia, onde é filha da classe Carta
 */
public class CartaEnergia extends Carta {

    private int energia;

    public CartaEnergia() {
        super("Alimento Energetico", "Ganha 2 de energia", 0);
        this.energia = 2;
    }

    /**
     * Metodo que usa a carta, dando +2 de energia ao heroi
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce consome um alimento energetico!");
        heroi.ganharEnergia(energia);
    }

    /**
     * Metodo que melhora a carta, onde aumenta a energia ganha em 1
     */
    @Override
    public void melhorar() {
        energia += 1;
        descricao = "Ganha " + energia + " de energia";
    }
}
