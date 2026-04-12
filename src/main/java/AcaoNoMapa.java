import java.util.Scanner;

/**
 * Ação executada em um lugar do mapa
 */
@FunctionalInterface
public interface AcaoNoMapa {
    boolean executar(Scanner entrada, Heroi heroi);
}
