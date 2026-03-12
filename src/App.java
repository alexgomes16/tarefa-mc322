import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Voce foi enviado para a Casa Leone para acabar com uma criatura sobrenatural que la habita, boa sorte");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("Ao entrar na casa voce se depara com uma criatura horrenda fantasmagorica, mate-a ou voce sera a proxima vitima dessa Casa amaldicoada");
        System.out.println();
        System.out.println("Aperte ENTER para iniciar a batalha...");
        sc.nextLine();

        // Aqui eu crio o Heroi e o Fantasma, com seus atributos
        Heroi heroi = new Heroi(20, 1);
        Inimigo fantasma = new Inimigo(10, 2, 6);

        // Aqui crio as cartas de dano e escudo
        CartaDano cartaDano = new CartaDano();
        CartaEscudo cartaEscudo = new CartaEscudo();

        int turno = 1;

        while (heroi.estaVivo() && fantasma.estaVivo()) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("               TURNO " + turno);
            System.out.println("========================================");

            // Aqui eu imprimo os status do heroi e do fantasma
            System.out.println();
            System.out.println("Status:");
            System.out.println("Investigador - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println("Fantasma     - Vida: " + fantasma.getVida() + " | Escudo: " + fantasma.getEscudo());

            int energia = 3;
            boolean terminouTurno = false;

            while (energia > 0 && !terminouTurno && heroi.estaVivo() && fantasma.estaVivo()) {

                //Aqui são as escolhas do jogador, de qual carta usar
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("Energia restante: " + energia);
                System.out.println("Escolha uma acao:");
                System.out.println("1) Usar Carta de Tiro (Dano: " + cartaDano.getDano() + " | Custo: " + cartaDano.getCusto() + ")");
                System.out.println("2) Usar Carta de Magia (Escudo: " + cartaEscudo.getEscudo() + " | Custo: " + cartaEscudo.getCusto() + ")");
                System.out.println("3) Finalizar turno");
                System.out.print("> ");

                String escolha = sc.nextLine().trim();

                System.out.println("----------------------------------------");

                switch (escolha) {

                    case "1":
                        // Caso escolha dano, eu verifico se tem energia o suficiente e se tiver eu uso a carta de dano e tiro energia
                        if (energia >= cartaDano.getCusto()) {

                            cartaDano.usar(fantasma);
                            energia -= cartaDano.getCusto();

                        } else {

                            System.out.println("Energia insuficiente para usar Carta de Tiro.");

                        }

                        break;

                    case "2":
                        // Caso escolha escudo, verifica se tem energia e caso tenha eu uso a carta de escudo e tiro a energia
                        if (energia >= cartaEscudo.getCusto()) {

                            cartaEscudo.usar(heroi);
                            energia -= cartaEscudo.getCusto();

                        } else {

                            System.out.println("Energia insuficiente para usar Carta de Magia.");

                        }

                        break;

                    case "3":
                        // Caso decida encerrar o turno, eu imrpimo a mensagem e dou um break
                        terminouTurno = true;
                        System.out.println("Voce decidiu encerrar o turno.");

                        break;

                    default:

                        System.out.println("Opcao invalida. Digite 1, 2 ou 3.");

                }

                System.out.println("----------------------------------------");
                System.out.println("Pressione ENTER para continuar...");
                sc.nextLine();

                if (!fantasma.estaVivo()) break;
                if (!heroi.estaVivo()) break;
            }

            // Se o fantasma não está vivo, imprimo a mensagem de vitoria
            if (!fantasma.estaVivo()) {

                System.out.println();
                System.out.println("Parabens, voce livrou a Casa Leone do mal!");
                break;

            }

            // Caso o inimigo esteja vivo ainda, ele ataca o heroi
            System.out.println();
            System.out.println("Fim das atividades do investigador.");
            System.out.println("O fantasma contra-ataca!");

            System.out.println("----------------------------------------");

            fantasma.atacar(heroi);

            System.out.println("----------------------------------------");

            System.out.println();
            System.out.println("Depois do ataque:");
            System.out.println("Investigador - Vida: " + heroi.getVida() + " | Escudo: " + heroi.getEscudo());
            System.out.println("Fantasma     - Vida: " + fantasma.getVida() + " | Escudo: " + fantasma.getEscudo());

            // Após imprimir todas as mensagens necessarias, eu verifico se o heroi está vivo, caso não imprimo a mensagem de derrota
            if (!heroi.estaVivo()) {

                System.out.println();
                System.out.println("Game Over, voce foi derrotado e o fantasma seguira assombrando a Casa Leone!");
                break;

            }

            turno++;
        }

        sc.close();
    }
}