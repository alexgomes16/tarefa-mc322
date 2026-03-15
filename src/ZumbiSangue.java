//Classe do Zumbi de Sangue, é o segundo inimigo do jogo, com status maiores que o Fantasma, e é filha da classe Inimigo
import java.util.Random;

public class ZumbiSangue extends Inimigo {
    private enum TipoIntencao { ATACAR, GANHAR_ESCUDO }
    private TipoIntencao intencaoEscolhida;
    private final Random rng = new Random();

    public ZumbiSangue(int vidaInicial, int escudoInicial, int ataque) {
        super("Zumbi de Sangue", vidaInicial, escudoInicial, ataque);
        this.intencaoEscolhida = TipoIntencao.ATACAR;
    }

    // O zumbi pode atacar ou ganhar escudo (se defender), então ele anuncia a intensão correspondente e no final do turno executa
    @Override
    public void anunciarIntencao() {
        boolean escolheAtacar = rng.nextBoolean();
        if (escolheAtacar) {
            intencaoEscolhida = TipoIntencao.ATACAR;
            this.intencaoDescricao = "Atacar (" + this.ataque + ")";
        } else {
            intencaoEscolhida = TipoIntencao.GANHAR_ESCUDO;
            int ganho = 3;
            this.intencaoDescricao = "Se defender: ganha escudo (" + ganho + ")";
        }
    }

    @Override
    public void executarAcao(Heroi heroi) {
        if (intencaoEscolhida == TipoIntencao.ATACAR) {
            atacar(heroi);
        } else {
            int ganho = 3;
            System.out.println(this.nome + " se defende e ganha " + ganho + " de escudo.");
            this.ganharEscudo(ganho);
        }
    }
}
