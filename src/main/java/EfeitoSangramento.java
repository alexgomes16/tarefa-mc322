/**
 * Aqui é a classe que representa o efeito de sangramento (veneno) do jogo, no qual é filha da classe Efeito
 */
public class EfeitoSangramento extends Efeito {

    public EfeitoSangramento(Entidade dono, Publisher publisher, int valor) {
        super("Sangramento", dono, publisher, valor);
    }

    /**
     * Metodo que da o dano do sangramento, imprime uma mensagem que fez isso e reduz esse acumulo de efeito
     */
    @Override
    public void serNotificado() {

        if (acumulos > 0) {
            int danoFinal = acumulos;

            if (dono instanceof Heroi) {
                Heroi heroi = (Heroi) dono;
                danoFinal = Math.max(0, acumulos - heroi.getRedutorSangramentoRecebido());
            }

            System.out.println(dono.getNome() + " sofre " + danoFinal + " de dano por sangramento!");
            dono.receberDano(danoFinal);

            acumulos--;

            if (acumulos <= 0) {
                publisher.desinscrever(this);
                dono.removerEfeito(this);
            }
        }
    }
}
