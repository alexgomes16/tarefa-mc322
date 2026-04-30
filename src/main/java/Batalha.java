import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa e controla o combate do jogo, tirando a responsabilidade do App fazer isso
 */
public class Batalha implements Evento {

    private static final int CARTAS_POR_TURNO = 3;

    private final Inimigo inimigo;
    private final boolean entregarRecompensa;
    private Scanner entradaLegado;
    private Heroi heroiLegado;
    private List<Carta> pilhaCompraLegado;
    private List<Carta> maoLegado;
    private List<Carta> descarteLegado;

    public Batalha(Inimigo inimigo) {
        this(inimigo, true);
    }

    public Batalha(Inimigo inimigo, boolean entregarRecompensa) {
        this.inimigo = inimigo;
        this.entregarRecompensa = entregarRecompensa;
    }

    /**
     * Construtor legado, para o App antigo ainda compilar.
     */
    public Batalha(Scanner entrada, Heroi heroi, List<Carta> pilhaCompra, List<Carta> mao, List<Carta> descarte) {
        this.inimigo = null;
        this.entregarRecompensa = false;
        this.entradaLegado = entrada;
        this.heroiLegado = heroi;
        this.pilhaCompraLegado = pilhaCompra;
        this.maoLegado = mao;
        this.descarteLegado = descarte;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    /**
     * Metodo para iniciar a batalha 
     */
    @Override
    public boolean iniciar(Jogo jogo, Scanner entrada) {
        if (inimigo == null) {
            throw new IllegalStateException("Esta batalha foi criada no modo legado e nao pode usar iniciar(Jogo, Scanner).");
        }
        return executarCombate(jogo, entrada, inimigo);
    }

    /**
     * Lopping da batalha do jogo (os turnos, maos, ações, decisões, etc)
     * 
     * @param inimigo representa o inimigo do heroi
     * @return true caso o jogador vença, e false caso o jogador perca
     */
    public boolean executar(Inimigo inimigo) {
        if (entradaLegado == null || heroiLegado == null || pilhaCompraLegado == null
                || maoLegado == null || descarteLegado == null) {
            throw new IllegalStateException("Esta batalha foi criada no modo novo e nao pode usar executar(Inimigo).");
        }

        Jogo jogo = new Jogo(heroiLegado, pilhaCompraLegado, maoLegado, descarteLegado);
        return executarCombate(jogo, entradaLegado, inimigo);
    }

    private boolean executarCombate(Jogo jogo, Scanner entrada, Inimigo inimigo) {
        Heroi heroi = jogo.getHeroi();

        App.publisher = new Publisher();
        heroi.limparEfeitos();
        inimigo.limparEfeitos();
        heroi.iniciarBatalha();

        int turno = 1;

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            // Imprime o cabeçalho de cada turno do jogo
            System.out.println();
            System.out.println("========================================");
            System.out.println("               TURNO " + turno);
            System.out.println("========================================");

            heroi.iniciarTurno();
            inimigo.anunciarIntencao();

            if (turno > 1) {
                // Depois do primeiro turno, pode ter algum efeito (tanto do inimigo quando do heroi)
                System.out.println();
                System.out.println("Aplicando efeitos...");

                // Verifica se tem efeitos ativos e avisa e aplica eles caso tenha 
                if (!App.publisher.temSubscribers()) {
                    System.out.println("Nenhum efeito em uso.");
                } else {
                    App.publisher.notificar();
                }

                // Verifica se o heroi ou o inimigo morreu após os efeitos serem executados e finaliza o combate
                if (!heroi.estaVivo() || !inimigo.estaVivo()) {
                    finalizarCombate(jogo, inimigo);
                    if (!heroi.estaVivo()) {
                        System.out.println();
                        System.out.println("Game Over, voce foi derrotado...");
                        return false;
                    } else {
                        System.out.println();
                        System.out.println("Voce derrotou " + inimigo.getNome() + "!");
                        if (entregarRecompensa) {
                            new EventoPosBatalha().iniciar(jogo, entrada);
                        }
                        return true;
                    }
                }
            }

            jogo.comprarCartas(CARTAS_POR_TURNO);

            // Imprime os status do heroi e do inimigo
            System.out.println();
            System.out.println("Status:");
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println("Efeitos do Investigador: " + heroi.listarEfeitos());
            System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida() + " | Escudo: " + inimigo.getEscudo());
            System.out.println("Efeitos do " + inimigo.getNome() + ": " + inimigo.listarEfeitos());
            System.out.println("Intencao do " + inimigo.getNome() + ": " + inimigo.getIntencaoDescricao());

            boolean fimTurno = false;

            while (!fimTurno && heroi.getEnergia() > 0 && heroi.estaVivo() && inimigo.estaVivo()) {
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("Energia: " + heroi.getEnergia());
                System.out.println("Mao:");

                // Imprime a mão atual do jogador, e a opção de encerrar o turno
                for (int i = 0; i < jogo.getMao().size(); i++) {
                    Carta c = jogo.getMao().get(i);
                    System.out.println((i + 1) + ") " + c.getNome() + " - " + c.getDescricao() + " (Custo: " + c.getCusto() + ")");
                }

                System.out.println((jogo.getMao().size() + 1) + ") Finalizar turno");
                System.out.print("> ");

                // Pega a escolha do jogador
                int escolha;
                try {
                    escolha = Integer.parseInt(entrada.nextLine().trim());
                } catch (Exception e) {
                    escolha = -1;
                }

                System.out.println("----------------------------------------");

                if (escolha >= 1 && escolha <= jogo.getMao().size()) {
                    Carta carta = jogo.getMao().get(escolha - 1);

                    // Com a escolha do jogador, caso tenha energia, vai usar a carta, e colocar ela na pilha de descarte
                    if (heroi.gastarEnergia(carta.getCusto())) {
                        carta.usar(heroi, inimigo);
                        jogo.getDescarte().add(carta);
                        jogo.getMao().remove(escolha - 1);
                    } else {
                        System.out.println("Energia insuficiente.");
                    }
                } else if (escolha == jogo.getMao().size() + 1) {
                    fimTurno = true;
                    System.out.println("Voce encerrou o turno.");
                } else {
                    System.out.println("Opcao invalida.");
                }

                System.out.println("----------------------------------------");
                System.out.println("Pressione ENTER...");
                if (entrada.hasNextLine()) {
                    entrada.nextLine();
                }
            }

            // Mensagem caso acabe a energia
            if (heroi.getEnergia() == 0) {
                System.out.println("Sua energia acabou!");
            }

            jogo.descartarMao();

            // Verifica se o inimigo morreu, se sim imprime uma mensagem de vitoria e returna true
            if (!inimigo.estaVivo()) {
                finalizarCombate(jogo, inimigo);
                System.out.println();
                System.out.println("Voce derrotou " + inimigo.getNome() + "!");
                if (entregarRecompensa) {
                    new EventoPosBatalha().iniciar(jogo, entrada);
                }
                return true;
            }

            // Mensagem de ação do inimigo, e caso ele não esteja aprisionado (uma das cartas do jogo), vai executar a ação
            System.out.println();
            System.out.println("O " + inimigo.getNome() + " age:");
            System.out.println("----------------------------------------");

            if (inimigo.atordoado) {
                System.out.println(inimigo.getNome() + " esta aprisionado e nao consegue agir!");
                inimigo.atordoado = false;
            } else {
                inimigo.executarAcao(heroi);
            }

            System.out.println("----------------------------------------");
            System.out.println("Pressione ENTER...");
            if (entrada.hasNextLine()) {
                entrada.nextLine();
            }

            // Verifica se o heroi morreu, e se sim, finaliza o combate, imprime a mensagem de derrota e retorna false
            if (!heroi.estaVivo()) {
                finalizarCombate(jogo, inimigo);
                System.out.println();
                System.out.println("Game Over, voce foi derrotado...");
                return false;
            }

            turno++;
        }

        finalizarCombate(jogo, inimigo);
        return heroi.estaVivo();
    }

    /**
     * Metodo onde vai finalizar o combate descartando a mão do jogador, e limpando os efeitos
     */
    private void finalizarCombate(Jogo jogo, Inimigo inimigo) {
        jogo.descartarMao();
        jogo.getHeroi().finalizarBatalha();
        inimigo.limparEfeitos();
    }
}
