import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Aqui representa um nó da arvore (que é o mapa do jogo), onde pode ser uma batalha ou um evento (escada)
 */
public class NoMapa {

    private final String nome;
    private final String descricao;
    private final Evento evento;
    private final boolean finalDoMapa;
    private final List<NoMapa> filhos;
    private boolean visitado;

    public NoMapa(String nome, String descricao, Evento evento, boolean finalDoMapa) {
        this.nome = nome;
        this.descricao = descricao;
        this.evento = evento;
        this.finalDoMapa = finalDoMapa;
        this.filhos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isBatalha() {
        return evento instanceof Batalha;
    }

    public boolean isEvento() {
        return !(evento instanceof Batalha);
    }

    public boolean isFinalDoMapa() {
        return finalDoMapa;
    }

    public Inimigo getInimigo() {
        if (evento instanceof Batalha) {
            return ((Batalha) evento).getInimigo();
        }
        return null;
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

    public boolean iniciar(Jogo jogo, Scanner entrada) {
        return evento.iniciar(jogo, entrada);
    }
}
