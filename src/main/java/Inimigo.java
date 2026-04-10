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
        // Aqui notifica o efeito antes do ataque
        App.publisher.notificar();
    
        int danoFinal = this.ataque;
    
        // Imprime a mensagem do ataque do inimigo e depois da o dano no heroi
        System.out.println(this.nome + " ataca e causa " + danoFinal + " de dano.");
        heroi.receberDano(danoFinal);
    }

    // Inimigo não começa atordoado
    protected boolean atordoado = false;

    /**
     * Metodo que coloca se o inimigo está atordoado ou não
     * 
     * @param valor true ou false (se está ou não atordoado)
     */
    public void setAtordoado(boolean valor) {
        this.atordoado = valor;
    }
}
