// Classe que representa um efeito do jogo, no qual é filha do Subscriber

public abstract class Efeito implements Subscriber {
    
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected Publisher publisher;

    public Efeito(String nome, Entidade dono, Publisher publisher, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.publisher = publisher;
        this.acumulos = acumulos;
    }

    public void adicionarAcumulo(int valor) {
        acumulos += valor;
    }

    public int getAcumulos() {
        return acumulos;
    }

    public String getNome() {
        return nome;
    }

    public String getString() {
        return nome + " (" + acumulos + ")";
    }
}
