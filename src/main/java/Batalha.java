import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa e controla o combate do jogo, tirando a responsabilidade do App fazer isso
 */
public class Batalha {

    private static final int CARTAS_POR_TURNO = 3;

    private final Scanner entrada;
    private final Heroi heroi;
    private final List<Carta> pilhaCompra;
    private final List<Carta> mao;
    private final List<Carta> descarte;

    public Batalha(Scanner entrada, Heroi heroi, List<Carta> pilhaCompra, List<Carta> mao, List<Carta> descarte) {
        this.entrada = entrada;
        this.heroi = heroi;
        this.pilhaCompra = pilhaCompra;
        this.mao = mao;
        this.descarte = descarte;
    }

    /**
     * Lopping da batalha do jogo (os turnos, maos, ações, decisões, etc)
     * 
     * @param inimigo representa o inimigo do heroi
     * @return true caso o jogador vença, e false caso o jogador perca
     */
    public boolean executar(Inimigo inimigo) {
        App.publisher = new Publisher();
        heroi.limparEfeitos();
        inimigo.limparEfeitos();

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

                // Verifica se o heroi ou o inimigo morreu após os efeitos serem executados
                if (!heroi.estaVivo() || !inimigo.estaVivo()) {
                    finalizarCombate(inimigo);
                    if (!heroi.estaVivo()) {
                        System.out.println();
                        System.out.println("Game Over, voce foi derrotado...");
                    } else {
                        System.out.println();
                        System.out.println("Voce derrotou " + inimigo.getNome() + "!");
                    }
                    return heroi.estaVivo();
                }
            }

            comprarCartas(CARTAS_POR_TURNO);

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
                for (int i = 0; i < mao.size(); i++) {
                    Carta carta = mao.get(i);
                    System.out.println((i + 1) + ") " + carta.getNome() + " - " + carta.getDescricao() + " (Custo: " + carta.getCusto() + ")");
                }

                System.out.println((mao.size() + 1) + ") Finalizar turno");
                System.out.print("> ");

                // Pega a escolha do jogador
                String input = entrada.nextLine().trim();
                int escolha;

                try {
                    escolha = Integer.parseInt(input);
                } catch (Exception e) {
                    escolha = -1;
                }

                System.out.println("----------------------------------------");

                if (escolha >= 1 && escolha <= mao.size()) {
                    Carta carta = mao.get(escolha - 1);

                    // Com a escolha do jogador, caso tenha energia, vai usar a carta, e colocar ela na pilha de descarte
                    if (heroi.gastarEnergia(carta.getCusto())) {
                        carta.usar(heroi, inimigo);
                        descarte.add(carta);
                        mao.remove(escolha - 1);
                    } else {
                        System.out.println("Energia insuficiente.");
                    }
                } else if (escolha == mao.size() + 1) {
                    fimTurno = true;
                    System.out.println("Voce encerrou o turno.");
                } else {
                    System.out.println("Opcao invalida.");
                }

                System.out.println("----------------------------------------");
                System.out.println("Pressione ENTER...");
                entrada.nextLine();
            }

            // Mensagem caso acabe a energia
            if (heroi.getEnergia() == 0) {
                System.out.println("Sua energia acabou!");
            }

            passarMaoParaDescarte();

            // Verifica se o inimigo morreu, se sim imprime uma mensagem de vitoria e returna true
            if (!inimigo.estaVivo()) {
                finalizarCombate(inimigo);
                System.out.println();
                System.out.println("Voce derrotou " + inimigo.getNome() + "!");
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
            entrada.nextLine();

            // Verifica se o heroi morreu, e se sim, finaliza o combate, imprime a mensagem de derrota e retorna false
            if (!heroi.estaVivo()) {
                finalizarCombate(inimigo);
                System.out.println();
                System.out.println("Game Over, voce foi derrotado...");
                return false;
            }

            turno++;
        }

        finalizarCombate(inimigo);
        return heroi.estaVivo();
    }

    /**
     * Metodo do jogador comprar cartas
     * 
     * @param quantidade de cartas
     */
    private void comprarCartas(int quantidade) {
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
     * Caso a mão ainda esteja com cartas, vai colocar essas cartas na pilha de descarte
     */
    private void passarMaoParaDescarte() {
        if (!mao.isEmpty()) {
            descarte.addAll(mao);
            mao.clear();
        }
    }

    /**
     * Metodo do final da batalha, onde limpa os efeitos do heroi e do inimigo e coloca as cartas da mão do jogador para a pilha de descarte
     * 
     * @param inimigo representa o inimigo
     */
    private void finalizarCombate(Inimigo inimigo) {
        passarMaoParaDescarte();
        heroi.limparEfeitos();
        inimigo.limparEfeitos();
    }
}
