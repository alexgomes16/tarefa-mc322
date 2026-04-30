/**
 * Aqui representa o mapa do jogo
 */
public class Mapa {

    private final NoMapa raiz;

    private Mapa(NoMapa raiz) {
        this.raiz = raiz;
    }

    public NoMapa getRaiz() {
        return raiz;
    }

    /**
     * Metodo que cria o mapa (cada comodo da mansão)
     */
    public static Mapa criarMapa() {
        // Então tem cada comodo, com o inimigo que tem no lugar
        NoMapa hall = batalha(
                "Hall de entrada",
                "",
                new Batalha(new Fantasma(9, 2, 4)),
                false
        );

        NoMapa cozinha = batalha(
                "Cozinha",
                "",
                new Batalha(new Fantasma(9, 2, 4)),
                false
        );

        NoMapa salaEstar = batalha(
                "Sala de estar",
                "",
                new Batalha(new ZumbiSangue()),
                false
        );

        // Aqui é a parte da escada, onde o heroi pode desistir de subir para o segundo andar e fugir da casa, ou continuar, e então aqui tem esses textos descritivos para maior imersão na história
        NoMapa escada = evento(
                "Escada",
                "",
                new Escolha() {
                    @Override
                    public boolean iniciar(Jogo jogo, java.util.Scanner entrada) {
                        System.out.println();
                        System.out.println("Voce derrotou a terrivel criatura, e na sua frente uma grande escada que leva para o segundo andar dessa terrivel casa, voce sente um arrepio, deseja continuar (sim ou nao)");
                        System.out.print("> ");

                        while (true) {
                            String resposta = entrada.nextLine().trim().toLowerCase();

                            if (resposta.equals("sim") || resposta.equals("s")) {
                                System.out.println();
                                System.out.println("Voce sobe as escadas com uma terrivel sensacao");
                                jogo.getHeroi().limparEfeitos();

                                System.out.println("========================================");
                                System.out.println("Escada");
                                System.out.println("========================================");
                                System.out.println("Entao voce encara mais uma decisao: sala de jantar ou quarto de hospedes.");
                                return true;
                            }

                            // E aqui caso a resposta for não (o jogo acaba)
                            if (resposta.equals("nao") || resposta.equals("n")) {
                                System.out.println();
                                System.out.println("O medo toma conta da sua alma, e tudo o que voce pensa e em fugir e sem pensar voce volta o caminho, deixando a casa mal-assombrada ainda");
                                return false;
                            }

                            System.out.println("Digite sim ou nao.");
                            System.out.print("> ");
                        }
                    }
                }
        );

        NoMapa salaJantar = batalha(
                "Sala de jantar",
                "",
                new Batalha(new Fantasma(9, 2, 4)),
                false
        );

        NoMapa biblioteca = batalha(
                "Biblioteca",
                "",
                new Batalha(new ZumbiSangue()),
                false
        );

        NoMapa quartoHospedes = batalha(
                "Quarto de hospedes",
                "",
                new Batalha(new ZumbiSangueBestial()),
                false
        );

        NoMapa suitePrincipal = batalha(
                "Suite principal",
                "Voce se recupera e toma folego, apos essa batalha, entretanto a porta a sua frente te faz tremer, voce presencia uma ultima batalha, a batalha na qual te trouxe ate aqui, na suite principal da familia Leone, onde a matriarca da familia foi consumida pelo sangue do paranormal, e se transformou em uma criatura gigantesca, um Tita de Sangue, com o corpo grande e terrivel, voce precisa lutar pela sua vida uma ultima vez",
                new Batalha(new TitaSangue(), false),
                true
        );

        hall.adicionarFilho(cozinha);
        hall.adicionarFilho(salaEstar);

        cozinha.adicionarFilho(escada);
        salaEstar.adicionarFilho(escada);

        escada.adicionarFilho(salaJantar);
        escada.adicionarFilho(quartoHospedes);

        salaJantar.adicionarFilho(biblioteca);
        biblioteca.adicionarFilho(suitePrincipal);
        quartoHospedes.adicionarFilho(suitePrincipal);

        return new Mapa(hall);
    }

    private static NoMapa batalha(String nome, String descricao, Evento evento, boolean finalDoMapa) {
        return new NoMapa(nome, descricao, evento, finalDoMapa);
    }

    private static NoMapa evento(String nome, String descricao, Evento evento) {
        return new NoMapa(nome, descricao, evento, false);
    }
}
