// Aqui é a classe que representa o efeito de bala amaldiçoada (+ de dano no tiro) do jogo, no qual é filha da classe Efeito

public class EfeitoBalaAmaldicoada extends Efeito {

    public EfeitoBalaAmaldicoada(Entidade dono, Publisher publisher, int valor) {
        super("Bala Amaldicoada", dono, publisher, valor);
    }

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
