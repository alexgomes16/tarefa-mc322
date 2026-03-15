import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final int CARTAS_POR_TURNO = 3;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Mensagem inicial do jogo
        System.out.println("Voce foi enviado para a Casa Leone para acabar com uma criatura sobrenatural que la habita, boa sorte");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("Ao entrar na casa voce se depara com uma criatura horrenda fantasmagorica, mate-a ou voce sera a proxima vitima dessa Casa amaldicoada");
        System.out.println();
        System.out.println("Aperte ENTER para iniciar a batalha...");
        entrada.nextLine();

        // Inicializa o heroi e o fantasma (primeiro inimigo)
        Heroi heroi = new Heroi(20, 1);
        Fantasma fantasma = new Fantasma(10, 2, 6);

        // Inicializa a pilha de compras, mão e pilha de descarte
        List<Carta> pilhaCompra = new ArrayList<>();
        List<Carta> mao = new ArrayList<>();
        List<Carta> pilhaDescarte = new ArrayList<>();

        // Adiciona na piha de compras as cartas de dano e de escudo
        for (int i = 0; i < 4; i++) {
            pilhaCompra.add(new CartaDano());
        }
        for (int i = 0; i < 4; i++) {
            pilhaCompra.add(new CartaEscudo());
        }

        // Embaralha a pilha de compras
        Collections.shuffle(pilhaCompra);

        // Iniciaiza a primeira batalha
        boolean venceuPrimeira = batalharComBaralho(entrada, heroi, fantasma, pilhaCompra, mao, pilhaDescarte);

        // Verifica se vencer a primeira batalha
        if (!venceuPrimeira) {
            entrada.close();
            return;
        }

        // Mensagem que voce venceu a primeira batalha e o inicio da outra
        System.out.println();
        System.out.println("Voce derrotou a terrivel criatura, porem ao entrar na sala seguinte dessa casa, voce da de cara com um terrivel zumbi que escorre sangue e grita em sua direcao, voce pressente sua morte, mas devera lutar.");
        System.out.println();
        System.out.println("Aperte ENTER para iniciar a proxima batalha...");
        entrada.nextLine();

        // Restaura oa status base do heroi
        heroi.restaurarBase();

        ZumbiSangue zumbi = new ZumbiSangue(14, 3, 7);

        // Inicializa a segunda batalha
        boolean venceuSegunda = batalharComBaralho(entrada, heroi, zumbi, pilhaCompra, mao, pilhaDescarte);

        // Verifica se vencer a primeira batalha
        if (venceuSegunda) {
            System.out.println();
            System.out.println("Parabens, voce livrou a Casa Leone do mal!");
        }

        entrada.close();
    }

    private static boolean batalharComBaralho(Scanner entrada, Heroi heroi, Inimigo inimigo, List<Carta> pilhaCompra, List<Carta> mao, List<Carta> pilhaDescarte) {
        // Aqui é a batalha em turnos em si
        int turno = 1;

        while (heroi.estaVivo() && inimigo.estaVivo()) {
            // Imprime o cabeçalho dos turnos
            System.out.println();
            System.out.println("========================================");
            System.out.println("               TURNO " + turno);
            System.out.println("========================================");
            System.out.println();

            int energia = 3;
            heroi.zerarEscudo();

            inimigo.anunciarIntencao();

            // Aqui é a ação de compras as cartas da pilha de compras
            comprarCartas(pilhaCompra, pilhaDescarte, mao, CARTAS_POR_TURNO);

            // Imprime os status das entidades e a intensão do inimigo
            System.out.println("Status:");
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida() + " | Escudo: " + inimigo.getEscudo());
            System.out.println("Intencao do " + inimigo.getNome() + ": " + inimigo.getIntencaoDescricao());
            System.out.println();

            boolean terminouTurno = false;

            while (energia > 0 && !terminouTurno && heroi.estaVivo() && inimigo.estaVivo()) {

                // Imprime o começo do cabeçalho da mão do jogador (as cartas e opções)
                System.out.println("----------------------------------------");
                System.out.println("Energia restante: " + energia);
                System.out.println("Mao atual:");

                // Imprime as cartas para o jogador e depois a opção de finalizar turno
                for (int i = 0; i < mao.size(); i++) {
                    Carta carta = mao.get(i);
                    System.out.println((i+1) + ") " + carta.getNome() + " - " + carta.getDescricao() + " (Custo: " + carta.getCusto() + ")");
                }
                System.out.println((mao.size()+1) + ") Finalizar turno");
                System.out.print("> ");

                String escolha = entrada.nextLine().trim();
                System.out.println("----------------------------------------");

                int opcao;
                try {
                    opcao = Integer.parseInt(escolha);
                } catch (NumberFormatException e) {
                    opcao = -1;
                }

                // Aqui é a parte da escolha da carta/ação do jogador
                if (opcao >= 1 && opcao <= mao.size()) {
                    Carta cartaEscolhida = mao.get(opcao - 1);
                    if (energia >= cartaEscolhida.getCusto()) {
                        // Verifica se tem energia o suficiente para usar essa carta, então usa ela e reduz o custo dela da energia
                        cartaEscolhida.usar(heroi, inimigo);
                        energia -= cartaEscolhida.getCusto();

                        // Então adiciona essa carta na pilha de descarte e tira da mão do jogador
                        pilhaDescarte.add(cartaEscolhida);
                        mao.remove(opcao - 1);

                    } else {
                        System.out.println("Energia insuficiente para usar esta carta.");
                    }
                } else if (opcao == mao.size() + 1) {
                    // Aqui decidiu encerrar o turno, então eu marco isso e imprimo a mensagem
                    terminouTurno = true;
                    System.out.println("Voce decidiu encerrar o turno.");
                } else {
                    System.out.println("Opcao invalida. Digite um numero valido.");
                }

                System.out.println("----------------------------------------");
                System.out.println("Pressione ENTER para continuar...");
                entrada.nextLine();

                // Verifica se o inimigo e o heroi está vivo, caso eles estejam mortos da um break
                if (!inimigo.estaVivo()) {
                    break;
                }
                if (!heroi.estaVivo()) {
                    break;
                }
            }

            if (!mao.isEmpty()) {
                pilhaDescarte.addAll(mao);
                mao.clear();
            }

            // Verifica se o inimigo morreu, se sim, imprime essa mensagem
            if (!inimigo.estaVivo()) {
                System.out.println();
                System.out.println("Voce derrotou " + inimigo.getNome() + "!");
                return true;
            }

            // FIm do turno, imprime essa informação e o inimigo faz sua ação
            System.out.println();
            System.out.println("Fim das atividades do jogador. " + inimigo.getNome() + " agora age:");
            System.out.println("----------------------------------------");
            inimigo.executarAcao(heroi);
            System.out.println("----------------------------------------");

            // Depois dessa ação imprime como ficou os status do heroi e do inimigo
            System.out.println();
            System.out.println("Depois da acao:");
            System.out.println(heroi.getNome() + " - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println(inimigo.getNome() + " - Vida: " + inimigo.getVida() + " | Escudo: " + inimigo.getEscudo());
            System.out.println();

            // Verifica se o heroi está vivo, caso ele esteja morto, imprime a mensagem de derrota
            if (!heroi.estaVivo()) {
                System.out.println();
                System.out.println("Game Over, voce foi derrotado e o " + inimigo.getNome() + " seguira assombrando a Casa Leone!");
                return false;
            }

            turno++;
        }
        // A batalha acaba e retorna que o heroi está vivo
        return heroi.estaVivo();
    }

    private static void comprarCartas(List<Carta> pilhaCompra, List<Carta> pilhaDescarte, List<Carta> mao, int n) {
        for (int i = 0; i < n; i++) {
            if (pilhaCompra.isEmpty()) {
                if (!pilhaDescarte.isEmpty()) {
                    // Caso a pilha de compras esteja vazia, vai adicionar as cartas da pilha de descarte ela, embaralhar e imprimir que houve esse embaralhamento de novo
                    pilhaCompra.addAll(pilhaDescarte);
                    pilhaDescarte.clear();
                    Collections.shuffle(pilhaCompra);
                    System.out.println("Pilha de compra vazia: embaralhando pilha de descarte para formar nova pilha de compra.");
                } else {
                    System.out.println("Nao ha mais cartas para comprar.");
                    break;
                }
            }
            Carta comprada = pilhaCompra.remove(0);
            mao.add(comprada);
        }
    }
}
