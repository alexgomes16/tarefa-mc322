public class Heroi {
    private int vida;
    private int escudo;

    public Heroi(int vidaInicial, int escudoInicial) {
        this.vida = vidaInicial;
        this.escudo = escudoInicial;
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

    public void adicionarEscudo(int quantidade) {
        this.escudo += quantidade;
    }

    public void receberDano(int quantidade) {
        System.out.println("Investigador recebe " + quantidade + " de dano (aplicado ao escudo antes da vida).");
        if (escudo >= quantidade) {
            escudo -= quantidade;
            System.out.println("O escudo absorveu todo o dano. Escudo restante: " + escudo);
        } else {
            int restante = quantidade - escudo;
            escudo = 0;
            vida -= restante;
            System.out.println("Escudo quebrou. Investigador perde " + restante + " de vida.");
            if (vida < 0) vida = 0;
        }
    }
}
