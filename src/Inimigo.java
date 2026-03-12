public class Inimigo {
    private int vida;
    private int escudo;
    private int ataque;

    public Inimigo(int vidaInicial, int escudoInicial, int ataque) {
        this.vida = vidaInicial;
        this.escudo = escudoInicial;
        this.ataque = ataque;
    }

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void receberDano(int quantidade) {
        System.out.println("Fantasma recebe " + quantidade + " de dano (aplicado ao escudo antes da vida).");
        if (escudo >= quantidade) {
            escudo -= quantidade;
            System.out.println("O defesa imaterial do fantasma absorveu todo o dano. Escudo restante: " + escudo);
        } else {
            int restante = quantidade - escudo;
            escudo = 0;
            vida -= restante;
            System.out.println("Defesa do fantasma quebra. Fantasma perde " + restante + " de vida.");
            if (vida < 0) vida = 0;
        }
    }

    public void atacar(Heroi heroi) {
        System.out.println("Fantasma ataca e causa " + ataque + " de dano.");
        heroi.receberDano(ataque);
    }
}
