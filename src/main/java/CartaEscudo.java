// Classe que representa a carta de escudo do jogo, que é filha da classe Carta

public class CartaEscudo extends Carta {

    public CartaEscudo() {
        super("Escudo", "Ganha 5 de escudo", 1);
    }

    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        heroi.ganharEscudo(5);
    }
}
