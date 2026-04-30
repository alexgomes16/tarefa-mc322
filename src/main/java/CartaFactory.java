/**
 * Classe que cria cartas a partir de uma carta existente
 */
public class CartaFactory {

    public static Carta criarMesmaClasse(Carta carta) {
        if (carta instanceof CartaDano) {
            return new CartaDano();
        }
        if (carta instanceof CartaEscudo) {
            return new CartaEscudo();
        }
        if (carta instanceof CartaSangramento) {
            return new CartaSangramento();
        }
        if (carta instanceof CartaBalaAmaldicoada) {
            return new CartaBalaAmaldicoada();
        }
        if (carta instanceof CartaEletrocussao) {
            return new CartaEletrocussao();
            }
        if (carta instanceof CartaAntidefesa) {
            return new CartaAntidefesa();
            }
        if (carta instanceof CartaEnergia) {
            return new CartaEnergia();
        }
        if (carta instanceof CartaAprisionamento) {
            return new CartaAprisionamento();
        }
        if (carta instanceof CartaLentidao) {
            return new CartaLentidao();
        }
        if (carta instanceof CartaFrascoLodo) {
            return new CartaFrascoLodo();
        }
        throw new IllegalArgumentException("Carta nao suportada pela factory.");
    }
}
