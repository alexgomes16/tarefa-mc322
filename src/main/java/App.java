import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    public static Publisher publisher = new Publisher();

    /**
     * Aqui é o fluxo principal do jogo, onde vai rodar todas as batalhas e ciclos do jogo
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Imprime o texto inicial do jogo
        System.out.println("Voce e um investigador paranormal e foi enviado para a Casa Leone para acabar com uma criatura sobrenatural que la habita, carregando sua fiel espingarda, boa sorte");
        System.out.println("Aperte ENTER para continuar...");
        entrada.nextLine();

        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("Voce se depara com a imponente Casa Leone, o local onde a sua familia foi massacrada e seus corpos agora se transformaram em terriveis criaturas.");
        System.out.println("Voce abre a porta e entra no Hall de Entrada");
        System.out.println();
        System.out.println("Aperte ENTER para continuar...");
        entrada.nextLine();

        // Então cria o heroi e as pilhas de cartas do jogador
        Heroi heroi = new Heroi(20, 3);

        List<Carta> pilhaCompra = criarBaralhoInicial();
        List<Carta> mao = new ArrayList<>();
        List<Carta> descarte = new ArrayList<>();

        // Então entra no sistema de mapas jo jogo (cria o mapa e explora ele)
        Mapa mapa = Mapa.criarMapa();
        explorarMapa(entrada, heroi, mapa.getRaiz(), pilhaCompra, mao, descarte);

        entrada.close();
    }

    private static void explorarMapa(Scanner entrada, Heroi heroi, NoMapa atual, List<Carta> pilhaCompra, List<Carta> mao, List<Carta> descarte) {

        boolean primeiroNo = true;
        boolean primeiraBatalha = true;
    
        while (atual != null && heroi.estaVivo()) {
    
            System.out.println();
            System.out.println("========================================");
            System.out.println(atual.getNome());
            System.out.println("========================================");
    
            // Imprime o texto da primeira batalha
            if (primeiroNo) {
                System.out.println();
                System.out.println("Voce se depara com uma criatura horrenda fantasmagorica, mate-a ou voce sera a proxima vitima dessa Casa amaldicoada");
                System.out.println();
                System.out.println("Aperte ENTER para iniciar a batalha...");
                entrada.nextLine();
                primeiroNo = false;
            }
    
            if (atual.isBatalha()) {
    
                List<NoMapa> proximos = atual.getFilhosNaoVisitados();
                boolean proximoEhEvento = proximos.size() == 1 && proximos.get(0).isEvento();
                boolean proximoEhFinal = proximos.size() == 1 && proximos.get(0).isFinalDoMapa();
                boolean ehHall = atual.getNome().equals("Hall de entrada");
    
                if (atual.isFinalDoMapa()) {
                    // Imprime o texto especial da ultima batalha
                    System.out.println();
                    System.out.println("Voce segue para: " + atual.getNome());
                    System.out.println();
                    System.out.println("Pressione ENTER...");
                    entrada.nextLine();
    
                    System.out.println("========================================");
                    System.out.println(atual.getNome());
                    System.out.println("========================================");
                    System.out.println();
    
                    heroi.curarSemMensagem(5);
    
                    System.out.println(atual.getDescricao());
                    System.out.println();
                    System.out.println("Voce retoma sua espingarda em maos e se prepara para a batalha!");
                    System.out.println();
                    System.out.println("Pressione ENTER...");
                    entrada.nextLine();
    
                } else if (!primeiraBatalha) {
                    // Imprime o texto de encontro com um inimigo, depois da primeira batalha (a primeira e a ultima batalha tem textos especiais)
                    System.out.println();
                    System.out.println("Voce abre a porta e se depara com um " + atual.getInimigo().getNome() + ".");
                    System.out.println("Voce retoma sua espingarda em maos e se prepara para a batalha!");
                }
    
                // Então após os textos iniciais, roda a batalha
                Batalha batalha = new Batalha(entrada, heroi, pilhaCompra, mao, descarte);
                boolean venceu = batalha.executar(atual.getInimigo());
    
                // Verifica se o jogador perdeu a batalha
                if (!venceu) {
                    return;
                }
    
                primeiraBatalha = false;
    
                proximos = atual.getFilhosNaoVisitados();
                proximoEhEvento = proximos.size() == 1 && proximos.get(0).isEvento();
                proximoEhFinal = proximos.size() == 1 && proximos.get(0).isFinalDoMapa();
                ehHall = atual.getNome().equals("Hall de entrada");
    
                // Imprime a mensagem que o heroi ingere o item de cura entre duas batalhas
                if (!atual.isFinalDoMapa() && !ehHall && !proximoEhEvento && !proximoEhFinal) {
                    System.out.println();
                    System.out.println("No meio do caminho voce pega um de seus frascos de lodo e ingere ele, recuperando parte de sua vitalidade (+5 de vida)");
                    heroi.curar(5);
                }
    
                // Mensagem caso venceu a ultima batalha
                if (atual.isFinalDoMapa()) {
                    System.out.println();
                    System.out.println("Voce, ferido, ve a criatura cair na sua frente, sua missao foi cumprida, voce livrou a casa do mal.");
                    return;
                }
    
            } else if (atual.isEvento()) {
                boolean continua = atual.executarAcao(entrada, heroi);
                if (!continua) {
                    return;
                }
            }
    
            // Então marca o local atual como visitado, ve os proximos lugares do mapa
            atual.marcarVisitado();
            List<NoMapa> proximos = atual.getFilhosNaoVisitados();
    
            // Verifica se tem um proximo lugar no mapa
            if (proximos.isEmpty()) {
                return;
            }
    
            // E então o jogador escolhe o proximo lugar para ir no mapa
            atual = escolherProximoNo(entrada, proximos);
        }
    }

    /**
     * Metodo que apresenta o proximo lugar para o jogador e ele escolhe para onde ir
     * 
     * @return lugar escolhido pelo jogador (ou o unico caminho que o heroi tem também)
     */
    private static NoMapa escolherProximoNo(Scanner entrada, List<NoMapa> opcoes) {
        if (opcoes.size() == 1) {
            // Caso só tenha um caminho para ir, então retorna esse caminho unico
            NoMapa unico = opcoes.get(0);
            System.out.println();
            System.out.println("Voce segue para: " + unico.getNome());
            return unico;
        }

        while (true) {
            System.out.println();
            System.out.println("Ao derrotar o inimigo voce enxerga duas portas por onde seguir, por onde voce quer ir:");

            // Então imprime as opções para o jogador escolher
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.println((i + 1) + ") " + opcoes.get(i).getNome());
            }

            // Pega a resposta do jogador
            System.out.print("> ");
            String input = entrada.nextLine().trim();

            int escolha;
            try {
                escolha = Integer.parseInt(input);
            } catch (Exception e) {
                escolha = -1;
            }

            // Retorna a escolha do jogador
            if (escolha >= 1 && escolha <= opcoes.size()) {
                return opcoes.get(escolha - 1);
            }

            System.out.println("Opcao invalida.");
        }
    }

    /**
     * Aqui que vai criar o baralho inicial do jogador e embaralha ela com todas as cartas
     */
    private static List<Carta> criarBaralhoInicial() {
        List<Carta> baralho = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 2; i++) {
            baralho.add(new CartaEletrocussao());
        }
        for (int i = 0; i < 2; i++) {
            baralho.add(new CartaAntidefesa());
        }
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaEnergia());
        }
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaAprisionamento());
        }
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaLentidao());
        }
        for (int i = 0; i < 3; i++) {
            baralho.add(new CartaFrascoLodo());
        }

        Collections.shuffle(baralho);
        return baralho;
    }
}
