// Aqui é o primeiro inimigo do jogo, com status mais baixos, onde herda da classe Inimigo

public class Fantasma extends Inimigo {
    public Fantasma(int vidaInicial, int escudoInicial, int ataque) {
        super("Fantasma", vidaInicial, escudoInicial, ataque);
    }

    //No caso do desse inimigo, ele apenas ataca ao final dos turnos, portanto a parte de anunciar sua intensão e sua ação em si é apenas dar dano
    @Override
    public void anunciarIntencao() {
        this.intencaoDescricao = "Atacar (" + this.ataque + ")";
    }

    @Override
    public void executarAcao(Heroi heroi) {
        atacar(heroi);
    }
}
