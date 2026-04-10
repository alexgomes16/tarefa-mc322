/**
 * Classe que representa um inimigo do jogo, que é filha da classe Entidade
 */
public abstract class Inimigo extends Entidade {
    protected int ataque;
    protected String intencaoDescricao;

    public Inimigo(String nome, int vidaInicial, int escudoInicial, int ataque) {
        super(nome, vidaInicial, escudoInicial);
        this.ataque = ataque;
        this.intencaoDescricao = "";
    }

    /**
     * Retorna o ataque do usuario
     * 
     * @return ataque do inimigo
     */
    public int getAtaque() {
        return ataque;
    }

    public abstract void anunciarIntencao();

    public abstract void executarAcao(Heroi heroi);

    /**
     * Metodo que pega a intensão do inimigo
     * 
     * @return intenção
     */
    public String getIntencaoDescricao() {
        return intencaoDescricao;
    }

    /**
     * Metodo que da dano no heroi, chamando o metodo da classe heroi de dar dano nele
     * 
     * @param heroi Representa o heroi/jogador (investigador)
     */
    protected void atacar(Heroi heroi) {
        System.out.println(this.nome + " ataca e causa " + this.ataque + " de dano.");
        heroi.receberDano(this.ataque);
    }
}
