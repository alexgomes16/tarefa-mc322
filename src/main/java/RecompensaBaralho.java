import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa o evento de modificação de baralho (quando, pós batalha, o jogador decide oolhar suas habilidades, ou seja modificar seu baralho)
 */
public class RecompensaBaralho extends Escolha {

    @Override
    public boolean iniciar(Jogo jogo, Scanner entrada) {
        while (true) {
            // Imprime as escolhas para o jogador
            System.out.println();
            System.out.println("Voce respira fundo e pensa em aprimorar suas habilidades.");
            System.out.println("Escolha uma acao:");
            System.out.println("1) Melhorar uma carta");
            System.out.println("2) Adicionar uma carta nova");
            System.out.println("3) Remover uma carta");
            System.out.println("4) Cancelar");

            // Pega a escolha do usuario
            int escolha = lerOpcao(entrada, 1, 4);

            if (escolha == 4) {
                return false;
            }

            // Lista todas as cartas do jogo
            List<Carta> cartas = jogo.listarCartasUnicas();
            if (cartas.isEmpty()) {
                System.out.println("Nao ha cartas disponiveis.");
                return false;
            }

            // Imprime todas essas cartas
            System.out.println();
            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = cartas.get(i);
                System.out.println((i + 1) + ") " + carta.getNome() + " - " + carta.getDescricao());
            }

            System.out.println((cartas.size() + 1) + ") Cancelar");
            int escolhaCarta = lerOpcao(entrada, 1, cartas.size() + 1);
            if (escolhaCarta == cartas.size() + 1) {
                return false;
            }

            // Pega a carta escolhida do usuario
            Carta cartaEscolhida = cartas.get(escolhaCarta - 1);

            // Se escolheu melhorar a carta, melhora ela
            if (escolha == 1) {
                jogo.melhorarCartasDoTipo(cartaEscolhida);
                System.out.println("A carta foi melhorada!");
                return true;
            }

            // Se escolheu adicionar mais uma carta daquele tipo no baralho, faz isso
            if (escolha == 2) {
                jogo.adicionarCartaAoBaralho(cartaEscolhida);
                System.out.println("Uma nova copia de " + cartaEscolhida.getNome() + " foi adicionada ao baralho.");
                return true;
            }

            // Se escolheu remover uma carta daquele tipo do baralho, faz isso
            if (escolha == 3) {
                boolean removida = jogo.removerUmaCartaDoTipo(cartaEscolhida);
                if (removida) {
                    System.out.println("Uma carta foi removida do baralho.");
                } else {
                    System.out.println("Nao foi possivel remover essa carta.");
                }
                return true;
            }
        }
    }
}
