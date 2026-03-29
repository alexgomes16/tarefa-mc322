// Aqui é a classe que representa o efeito de sangramento (veneno) do jogo, no qual é filha da classe Efeito

public class EfeitoSangramento extends Efeito {

    public EfeitoSangramento(Entidade dono, Publisher publisher, int valor) {
        super("Sangramento", dono, publisher, valor);
    }

    @Override
    public void serNotificado() {

        if (acumulos > 0) {
            System.out.println(dono.getNome() + " sofre " + acumulos + " de dano por sangramento!");
            dono.receberDano(acumulos);

            acumulos--;

            if (acumulos <= 0) {
                publisher.desinscrever(this);
                dono.removerEfeito(this);
            }
        }
    }
}
