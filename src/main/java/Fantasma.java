 /**
 * Aqui é o primeiro inimigo do jogo, com status mais baixos, onde herda da classe Inimigo
 */
public class Fantasma extends Inimigo {
    public Fantasma(int vidaInicial, int escudoInicial, int ataque) {
        super("Fantasma", vidaInicial, escudoInicial, ataque);
    }

    /**
     * Metodo que anuncia a intenção do fantasma
     * No caso do desse inimigo, ele apenas ataca ao final dos turnos, portanto a parte de anunciar sua intensão e sua ação em si é apenas dar dano
     */
    @Override
    public void anunciarIntencao() {
        this.intencaoDescricao = "Atacar (" + this.ataque + ")";
    }

    /**
     * Metodo que executa a ação, que no caso é o ataque, pois o fantasma apenas ataca
     */
    @Override
    public void executarAcao(Heroi heroi) {
        atacar(heroi);
    }
}
