/**
 * Classe que representa um item (item que aparece nas recompensas pós batalha)
 */
public abstract class Item {
    protected String nome;
    protected String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
