/**
 * Aqui eu crio um inimigo "fake" para testar os comportamentos de um inimigo (é uma classe auxiliar de teste)
 */
public class TesteInimigo extends Inimigo {

    public TesteInimigo(String nome, int vidaInicial, int escudoInicial, int ataque) {
        super(nome, vidaInicial, escudoInicial, ataque);
    }

    @Override
    public void anunciarIntencao() {
        this.intencaoDescricao = "Atacar (" + this.ataque + ")";
    }

    @Override
    public void executarAcao(Heroi heroi) {
        atacar(heroi);
    }

    public void atacarDireto(Heroi heroi) {
        atacar(heroi);
    }
}
