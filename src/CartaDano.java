// Classe da carta de dano que é filha da classe Carta

public class CartaDano extends Carta {
    private final int dano;

    public CartaDano() {
        super("Tiro", "Causa 5 de dano ao inimigo", 2);
        this.dano = 5;
    }

    public int getDano() {
        return dano;
    }

    // Ao usar, imprime a mensagem que voce usou a carta e da o dano no iminigo (chamando o metodo)
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce usa " + nome + " e causa " + dano + " de dano ao " + inimigo.getNome() + "!");
        inimigo.receberDano(dano);
    }
}
