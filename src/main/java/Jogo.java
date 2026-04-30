import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe que guarda o estado persistente do jogo
 */
public class Jogo {

    private final Heroi heroi;
    private final List<Carta> pilhaCompra;
    private final List<Carta> mao;
    private final List<Carta> descarte;
    private final List<ItemConsumivel> inventarioConsumiveis;
    private final Map<Class<? extends Carta>, Integer> melhoriasPorTipo;

    public Jogo(Heroi heroi) {
        this(heroi, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Jogo(Heroi heroi, List<Carta> pilhaCompra, List<Carta> mao, List<Carta> descarte) {
        this.heroi = heroi;
        this.pilhaCompra = pilhaCompra;
        this.mao = mao;
        this.descarte = descarte;
        this.inventarioConsumiveis = new ArrayList<>();
        this.melhoriasPorTipo = new LinkedHashMap<>();
    }

    public Heroi getHeroi() {
        return heroi;
    }

    public List<Carta> getPilhaCompra() {
        return pilhaCompra;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public List<Carta> getDescarte() {
        return descarte;
    }

    public List<ItemConsumivel> getInventarioConsumiveis() {
        return inventarioConsumiveis;
    }

    /**
     * Metodo que compra cartas da pilha de compra (e se a pilha de compras não tiver cartas, vai pegar da pilha de descarte, embaralhar e encher a pilha de compras)
     * 
     * @param quantidade de cartas para pegar
     */
    public void comprarCartas(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (pilhaCompra.isEmpty()) {
                if (descarte.isEmpty()) {
                    System.out.println("Sem cartas para comprar.");
                    return;
                }

                pilhaCompra.addAll(descarte);
                descarte.clear();
                Collections.shuffle(pilhaCompra);
                System.out.println("Baralho reembaralhado.");
            }

            mao.add(pilhaCompra.remove(0));
        }
    }

    /**
     * Metodo que descarta a mão atual (todas as cartas) do jogador
     */
    public void descartarMao() {
        if (!mao.isEmpty()) {
            descarte.addAll(mao);
            mao.clear();
        }
    }

    /**
     * Metodo que lista todas as cartas do jogo
     * 
     * @return todas as cartas
     */
    public List<Carta> listarTodasAsCartas() {
        List<Carta> todas = new ArrayList<>();
        todas.addAll(pilhaCompra);
        todas.addAll(mao);
        todas.addAll(descarte);
        return todas;
    }

    /**
     * Metodo que retorna uma lista com apenas uma carta por tipo (usado para a melhoria, descarte e adição de cartas, uma das recompensas após batalha)
     */
    public List<Carta> listarCartasUnicas() {
        Map<Class<? extends Carta>, Carta> unicas = new LinkedHashMap<>();

        for (Carta carta : listarTodasAsCartas()) {
            unicas.putIfAbsent(carta.getClass(), carta);
        }

        return new ArrayList<>(unicas.values());
    }

    public void adicionarCartaAoBaralho(Carta cartaModelo) {
        Carta nova = CartaFactory.criarMesmaClasse(cartaModelo);

        int melhorias = melhoriasPorTipo.getOrDefault(cartaModelo.getClass(), 0);
        for (int i = 0; i < melhorias; i++) {
            nova.melhorar();
        }

        pilhaCompra.add(nova);
        Collections.shuffle(pilhaCompra);
    }

    /**
     * Metodo que vai melhorar as cartas da Carta que o jogador quer (exemplo, vai melhorar todas as cartas de tiro do jogo)
     */
    public void melhorarCartasDoTipo(Carta cartaModelo) {
        Class<? extends Carta> tipo = cartaModelo.getClass();

        for (Carta carta : pilhaCompra) {
            if (carta.getClass().equals(tipo)) {
                carta.melhorar();
            }
        }
        for (Carta carta : mao) {
            if (carta.getClass().equals(tipo)) {
                carta.melhorar();
            }
        }
        for (Carta carta : descarte) {
            if (carta.getClass().equals(tipo)) {
                carta.melhorar();
            }
        }

        melhoriasPorTipo.put(tipo, melhoriasPorTipo.getOrDefault(tipo, 0) + 1);
    }

    /**
     * Metodo que remove uma carta do baralho, do tipo pedido
     * 
     * @params cartaModelo tipo da carta que vai ser removida
     */
    public boolean removerUmaCartaDoTipo(Carta cartaModelo) {
        Class<? extends Carta> tipo = cartaModelo.getClass();
        return removerPrimeiraOcorrencia(mao, tipo) || removerPrimeiraOcorrencia(descarte, tipo) || removerPrimeiraOcorrencia(pilhaCompra, tipo);
    }

    /**
     * Metodo que remove a primeira carta da lista (a primeira da lista)
     */
    private boolean removerPrimeiraOcorrencia(List<Carta> lista, Class<? extends Carta> tipo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getClass().equals(tipo)) {
                lista.remove(i);
                return true;
            }
        }
        return false;
    }

    public void adicionarConsumivel(ItemConsumivel item) {
        inventarioConsumiveis.add(item);
    }

    public boolean temConsumiveis() {
        return !inventarioConsumiveis.isEmpty();
    }

    /**
     * Metodo que mostra os itens consumiveis 
     */
    public void mostrarConsumiveis() {
        if (inventarioConsumiveis.isEmpty()) {
            System.out.println("Nenhum item consumivel disponivel.");
            return;
        }

        for (int i = 0; i < inventarioConsumiveis.size(); i++) {
            ItemConsumivel item = inventarioConsumiveis.get(i);
            System.out.println((i + 1) + ") " + item.getNome() + " - " + item.getDescricao() + " (usos restantes: " + item.getUsosRestantes() + ")");
        }
    }

    /**
     * metodo que usa um item consumivel
     */
    public boolean usarItemConsumivel(Scanner entrada) {
        if (inventarioConsumiveis.isEmpty()) {
            System.out.println("Voce nao possui itens consumiveis.");
            return false;
        }

        while (true) {
            System.out.println();
            System.out.println("Escolha um item consumivel:");

            for (int i = 0; i < inventarioConsumiveis.size(); i++) {
                ItemConsumivel item = inventarioConsumiveis.get(i);
                System.out.println((i + 1) + ") " + item.getNome() + " - " + item.getDescricao() + " (usos restantes: " + item.getUsosRestantes() + ")");
            }

            System.out.println((inventarioConsumiveis.size() + 1) + ") Cancelar");

            int opcao;
            try {
                opcao = Integer.parseInt(entrada.nextLine().trim());
            } catch (Exception e) {
                opcao = -1;
            }

            if (opcao == inventarioConsumiveis.size() + 1) {
                return false;
            }

            if (opcao >= 1 && opcao <= inventarioConsumiveis.size()) {
                ItemConsumivel item = inventarioConsumiveis.get(opcao - 1);
                item.usar(heroi, this);

                if (item.getUsosRestantes() <= 0) {
                    inventarioConsumiveis.remove(opcao - 1);
                }
                return true;
            }

            System.out.println("Opcao invalida.");
        }
    }
}
