import java.util.Scanner;

/**
 * CLasse que representa qualquer evento do jogo
 */
public interface Evento {
    boolean iniciar(Jogo jogo, Scanner entrada);
}
