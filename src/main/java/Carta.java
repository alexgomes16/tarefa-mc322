/**
 * Classe que representa qualquer carta do jogo
 */
public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;

    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getNome() { 
        return nome; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    
    public int getCusto() { 
        return custo; 
    }

    /**
     * metodo que usa a carta, ou seja, cada carta vai usar esse metodo fazendo o que a carta faz
     * 
     * @param heroi representa o jogador
     * @param inimigo representa o inimigo 
     */
    public abstract void usar(Heroi heroi, Inimigo inimigo);

    /**
     * Metodo que melhora a carta, isso é possivel depois de uma batalha, caso o jogador queira fazer essa ação
     */
    public abstract void melhorar();
}
