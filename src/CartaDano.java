public class CartaDano {

    private int custo;
    private int dano;

    public CartaDano() {
        this.custo = 2;
        this.dano = 5;
    }

    public int getCusto() {
        return custo;
    }

    public int getDano() {
        return dano;
    }

    public void usar(Inimigo inimigo) {
        System.out.println("Você dispara sua espingarda causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }
}
