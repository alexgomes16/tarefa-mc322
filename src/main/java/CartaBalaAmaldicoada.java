/**
 * Classe que representa a carta de bala amaldiçoada do jogo, que é a filha da classe Carta
 */
public class CartaBalaAmaldicoada extends Carta {

    private int bonus;

    public CartaBalaAmaldicoada() {
        super("Bala Amaldicoada", "Proximo tiro recebe +1", 1);
        this.bonus = 1;
    }

    /**
     * Metodo que usa a carta, aplicando o efeito e imprimindo a mensagem para o usuario
     */
    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
        System.out.println("Voce prepara uma bala amaldicoada...");
        heroi.aplicarEfeito(new EfeitoBalaAmaldicoada(heroi, App.publisher, bonus));
    }

    /**
     * Metodo que melhora a carta, aumentando o bonus no tiro
     */
    @Override
    public void melhorar() {
        bonus += 1;
        descricao = "Proximo tiro recebe +" + bonus;
    }
}