// Classe que representa a carta de bala amaldiçoada do jogo, que é a filha da classe Carta

public class CartaBalaAmaldicoada extends Carta {

    public CartaBalaAmaldicoada() {
        super("Bala Amaldicoada", "Proximo ataque recebe +1", 1);
    }

    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {

        System.out.println("Voce amaldicoada sua arma com um ritual!");

        Efeito efeito = new EfeitoBalaAmaldicoada(heroi, App.publisher, 1);
        heroi.aplicarEfeito(efeito);
    }
}
