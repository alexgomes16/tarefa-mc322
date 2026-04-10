/**
 * Aqui é a classe que representa o efeito de bala amaldiçoada (+ de dano no tiro) do jogo, no qual é filha da classe Efeito
 */
public class EfeitoBalaAmaldicoada extends Efeito {

    public EfeitoBalaAmaldicoada(Entidade dono, Publisher publisher, int valor) {
        super("Bala Amaldicoada", dono, publisher, valor);
    }

    /**
     * Metodo que consome os bonus da bala amaldiçoada
     * 
     * @return valor do bonus reduzido/consumido
     */
    public int consumirBonus() {
        int valor = acumulos;
        acumulos = 0;

        publisher.desinscrever(this);
        dono.removerEfeito(this);

        return valor;
    }

    @Override
    public void serNotificado() {}
}
