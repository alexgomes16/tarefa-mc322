public class CartaEscudo {

    private int custo;
    private int escudo;

    public CartaEscudo() {
        this.custo = 1;
        this.escudo = 5;
    }

    public int getCusto() {
        return custo;
    }

    public int getEscudo() {
        return escudo;
    }

    public void usar(Heroi heroi) {
        System.out.println("Você conjura uma barreira paranormal e ganha " + escudo + " de escudo!");
        heroi.adicionarEscudo(escudo);
    }
}