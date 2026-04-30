import java.util.Scanner;

/**
 * Classe que representa as escolhas que o jogador tem que tomar no terminal
 */
public abstract class Escolha implements Evento {

    protected int lerOpcao(Scanner entrada, int minimo, int maximo) {
        while (true) {
            System.out.print("> ");
            String linha = entrada.nextLine().trim();

            try {
                int opcao = Integer.parseInt(linha);
                if (opcao >= minimo && opcao <= maximo) {
                    return opcao;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Opcao invalida.");
        }
    }
}
