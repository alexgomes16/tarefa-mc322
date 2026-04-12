import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Aqui representa um nó da arvore (que é o mapa do jogo), onde pode ser uma batalha ou um evento
 */
public class NoMapa {

    private final String nome;
    private final String descricao;
    private final Inimigo inimigo;
    private final AcaoNoMapa acao;
    private final boolean finalDoMapa;
    private final List<NoMapa> filhos;
    private boolean visitado;

    public NoMapa(String nome, String descricao, Inimigo inimigo, boolean finalDoMapa) {
        this.nome = nome;
        this.descricao = descricao;
        this.inimigo = inimigo;
        this.acao = null;
        this.finalDoMapa = finalDoMapa;
        this.filhos = new ArrayList<>();
    }

    public NoMapa(String nome, String descricao, AcaoNoMapa acao) {
        this.nome = nome;
        this.descricao = descricao;
        this.inimigo = null;
        this.acao = acao;
        this.finalDoMapa = false;
        this.filhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public boolean isBatalha() {
        return inimigo != null;
    }

    public boolean isEvento() {
        return acao != null;
    }

    public boolean isFinalDoMapa() {
        return finalDoMapa;
    }

    public void adicionarFilho(NoMapa filho) {
        filhos.add(filho);
    }

    public List<NoMapa> getFilhosNaoVisitados() {
        List<NoMapa> disponiveis = new ArrayList<>();
        for (NoMapa filho : filhos) {
            if (!filho.visitado) {
                disponiveis.add(filho);
            }
        }
        return disponiveis;
    }

    public void marcarVisitado() {
        visitado = true;
    }

    public boolean executarAcao(Scanner entrada, Heroi heroi) {
        if (acao == null) {
            return true;
        }
        return acao.executar(entrada, heroi);
    }
}
