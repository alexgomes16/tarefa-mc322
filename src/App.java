import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public static Publisher publisher = new Publisher();
    private static final int CARTAS_POR_TURNO = 3;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Imprime a mensagem de inicio do jogo 
        System.out.println("Voce foi enviado para a Casa Leone para acabar com uma criatura sobrenatural que la habita, boa sorte");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("Ao entrar na casa voce se depara com uma criatura horrenda fantasmagorica, mate-a ou voce sera a proxima vitima dessa Casa amaldicoada");
        System.out.println();
        System.out.println("Aperte ENTER para iniciar a batalha...");
        entrada.nextLine();

        // Cria o heroi e o primeiro inimigo que é o fantasma
        Heroi heroi = new Heroi(20, 1);
        Fantasma fantasma = new Fantasma(10, 2, 6);

        // Inicia o sistema de baralho também
        List<Carta> pilhaCompra = criarBaralhoInicial();
        List<Carta> mao = new ArrayList<>();
        List<Carta> descarte = new ArrayList<>();

        // Inicia a primeira batalha
        boolean venceuPrimeira = batalha(entrada, heroi, fantasma, pilhaCompra, mao, descarte);

        // Verifica se o jogador vencer a primeira batalha, caso não tenha vencido quebra o jogo aqui
        if (!venceuPrimeira) {
            entrada.close();
            return;
        }

        // Imprime a mensagem de vitória e introdução da segunda batalha
        System.out.println();
        System.out.println("Voce derrotou a terrivel criatura, mas algo pior surge...");
        System.out.println("Um Zumbi de Sangue aparece diante de voce!");
        System.out.println();
        System.out.println("Aperte ENTER para continuar...");
        entrada.nextLine();

        // Heroi restaura seus status
        heroi.restaurarBase();

        // Cria o segundo inimigo: o zumbi
        ZumbiSangue zumbi = new ZumbiSangue(14, 3, 7);

        // Inicia a segunda batalha
        boolean venceuSegunda = batalha(entrada, heroi, zumbi, pilhaCompra, mao, descarte);

        // Imprime a mensagem de vitória
        if (venceuSegunda) {
            System.out.println();
            System.out.println("Parabens, voce livrou a Casa Leone do mal!");
        }

        entrada.close();
    }

    private static boolean batalha(Scanner entrada, Heroi heroi, Inimigo inimigo, List<Carta> compra, List<Carta> mao, List<Carta> descarte) {

        int turno = 1;

        while (heroi.estaVivo() && inimigo.estaVivo()) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("               TURNO " + turno);
            System.out.println("========================================");

            heroi.iniciarTurno();
            inimigo.anunciarIntencao();

            // Aqui imprime os efeitos do fim do turno, e não mostra no primeiro turno, pois não tem como ter efeitos no primeiro turno
            if (turno > 1) {
                System.out.println();
                System.out.println("Aplicando efeitos...");

                if (!publisher.temSubscribers()) {
                    System.out.println("Nenhum efeito em uso.");
                } else {
                    publisher.notificar();
                }
            }

            // Compra as cartas 
            comprarCartas(compra, descarte, mao, CARTAS_POR_TURNO);

            // Imprime os status do heroi e do inimigo (e a intenção do inimigo)
            System.out.println();
            System.out.println("Status:");
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida() + " | Escudo: " + inimigo.getEscudo());
            System.out.println("Intencao do " + inimigo.getNome() + ": " + inimigo.getIntencaoDescricao());

            boolean fimTurno = false;

            while (!fimTurno && heroi.getEnergia() > 0 && heroi.estaVivo() && inimigo.estaVivo()) {

                // Imprime a energia do jogador
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("Energia: " + heroi.getEnergia());
                System.out.println("Mao:");

                // Imprime a mão do jogador e a opção de finalizar turno
                for (int i = 0; i < mao.size(); i++) {
                    Carta carta = mao.get(i);
                    System.out.println((i + 1) + ") " + carta.getNome() + " - " + carta.getDescricao() + " (Custo: " + carta.getCusto() + ")");
                }

                System.out.println((mao.size() + 1) + ") Finalizar turno");
                System.out.print("> ");

                // Então pega e guarda a escolha do jogador
                String input = entrada.nextLine();
                int escolha;

                try {
                    escolha = Integer.parseInt(input);
                } catch (Exception e) {
                    escolha = -1;
                }

                System.out.println("----------------------------------------");

                if (escolha >= 1 && escolha <= mao.size()) {

                    Carta carta = mao.get(escolha - 1);

                    if (heroi.gastarEnergia(carta.getCusto())) {
                        // Se for uma opção válida e tiver energia eu uso essa carta, coloco na pilha de descarta e removo ela da mão do jogador
                        carta.usar(heroi, inimigo);

                        descarte.add(carta);
                        mao.remove(escolha - 1);

                    } else {
                        // Caso não tenha energia, avisa o jogador
                        System.out.println("Energia insuficiente.");
                    }

                } else if (escolha == mao.size() + 1) {
                    // Caso tenha escolhido encerrar o turno
                    fimTurno = true;
                    System.out.println("Voce encerrou o turno.");

                } else {
                    System.out.println("Opcao invalida.");
                }

                System.out.println("----------------------------------------");
                System.out.println("Pressione ENTER...");
                entrada.nextLine();
            }
            // Verifica se a energia do jogador acabou e avisa isso caso sim
            if (heroi.getEnergia() == 0) {
                System.out.println("Sua energia acabou!");
            }

            descarte.addAll(mao);
            mao.clear();

            // Verifica se o inimigo está vivo, caso não imprime a mensagem de vitória
            if (!inimigo.estaVivo()) {
                System.out.println("Voce derrotou " + inimigo.getNome() + "!");
                return true;
            }

            System.out.println();
            System.out.println("O " + inimigo.getNome() + " age:");
            System.out.println("----------------------------------------");

            // Turno acaba, e o inimigo age
            inimigo.executarAcao(heroi);

            System.out.println("----------------------------------------");

            // Verifica se o heroi está vivo, se não estiver, imprime a mensagem de game over
            if (!heroi.estaVivo()) {
                System.out.println();
                System.out.println("Game Over, voce foi derrotado...");
                return false;
            }

            turno++;
        }

        return heroi.estaVivo();
    }

    private static List<Carta> criarBaralhoInicial() {
        // Aqui cria o baralho inicial, adicionando x quantidade de cartas de cada tipo e embaralha no final

        List<Carta> baralho = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaDano());
        }
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaEscudo());
        }
        for (int i = 0; i < 2; i++) {
            baralho.add(new CartaSangramento());
        }
        for (int i = 0; i < 2; i++) {
            baralho.add(new CartaBalaAmaldicoada());
        }

        Collections.shuffle(baralho);
        return baralho;
    }

    private static void comprarCartas(List<Carta> compra, List<Carta> descarte, List<Carta> mao, int qtd) {

        for (int i = 0; i < qtd; i++) {

            if (compra.isEmpty()) {
                // Caso a pilha de compras estiver vazia, vai avisar o jogador e embaralhar de novo
                if (descarte.isEmpty()) {
                    System.out.println("Sem cartas para comprar.");
                    return;
                }

                compra.addAll(descarte);
                descarte.clear();
                Collections.shuffle(compra);

                System.out.println("Baralho reembaralhado.");
            }

            mao.add(compra.remove(0));
        }
    }
}
