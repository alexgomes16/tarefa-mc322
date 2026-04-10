/**
 * Classe do Efeito de Lentidão, que é filho da classe Efeito
 */
public class EfeitoLentidao extends Efeito {

    public EfeitoLentidao(Entidade dono, Publisher publisher, int valor) {
        super("Lentidao", dono, publisher, valor);
    }

    @Override
    public void serNotificado() {

        if (dono instanceof Inimigo) {
            // Vai ver se o ataque do inimigo está maior que zero e se sim vai reduzir em um esse dano e imprimir para o usuario que fez isso
            Inimigo inimigo = (Inimigo) dono;

            if (inimigo.getAtaque() > 0) {
                inimigo.ataque -= 1;
                System.out.println("Lentidao reduz o dano do inimigo!");
            }
        }

        acumulos--;

        if (acumulos <= 0) {
            publisher.desinscrever(this);
            dono.removerEfeito(this);
        }
    }
}
