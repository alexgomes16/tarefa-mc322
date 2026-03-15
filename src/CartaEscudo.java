// Classe da carta de escudo, onde é filha da classe Carta

public class CartaEscudo extends Carta {
    private final int escudo;

    public CartaEscudo() {
        super("Escudo", "Concede 5 de escudo ao heroi", 1);
        this.escudo = 5;
    }

    public int getEscudo() {
        return escudo;
    }

    // Ao usar, imprime a mensagem que usou a carta, e da o escudo para o heroi (chama esse metodo da classe heroi)
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce usa " + nome + " e ganha " + escudo + " de escudo!");
        heroi.ganharEscudo(escudo);
    }
}
