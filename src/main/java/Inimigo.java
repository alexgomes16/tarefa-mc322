/**
 * Classe que representa um inimigo do jogo, que é filha da classe Entidade
 */
public abstract class Inimigo extends Entidade {

    protected int ataque;
    protected String intencaoDescricao;
    protected boolean atordoado = false;
    protected int reducaoProximoAtaque = 0;

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

    /**
     * Metodo que pega a intensão do inimigo
     * 
     * @return intenção
     */
    public String getIntencaoDescricao() {
        return intencaoDescricao;
    }

    public abstract void anunciarIntencao();

    public abstract void executarAcao(Heroi heroi);

    /**
     * Metodo que da dano no heroi, chamando o metodo da classe heroi de dar dano nele
     * 
     * @param heroi Representa o heroi/jogador (investigador)
     */
    protected void atacar(Heroi heroi) {
        // Calcula o dano com a redução do efeito de lentidão
        int danoFinal = Math.max(0, this.ataque - reducaoProximoAtaque);

        // Verifica se o inimigo está sob o efeito de lentidão
        if (reducaoProximoAtaque > 0) {
            System.out.println("A lentidao reduz o dano do ataque em " + reducaoProximoAtaque + ".");
        }

        reducaoProximoAtaque = 0;

        // Imprime a mensagem do ataque do inimigo e depois da o dano no heroi
        System.out.println(this.nome + " ataca e causa " + danoFinal + " de dano.");
        heroi.receberDano(danoFinal);
    }

    /**
     * Metodo que coloca se o inimigo está atordoado ou não
     * 
     * @param valor true ou false (se está ou não atordoado)
     */
    public void setAtordoado(boolean valor) {
        this.atordoado = valor;
    }

    /**
     * Metodo que aplica o efeito de lentidão no inimigo
     * 
     * @param valor da lentidão
     */
    public void aplicarLentidao(int valor) {
        reducaoProximoAtaque += valor;
        System.out.println(nome + " fica mais lento e seu proximo ataque sera reduzido em " + valor + ".");
    }
}
