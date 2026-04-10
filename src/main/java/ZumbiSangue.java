import java.util.Random;

/**
 * Representa o segundo inimigo do jogo, um zumbi de sangue, um inimigo com status melhores, e mais complexo
 */
public class ZumbiSangue extends Inimigo {

    private enum TipoIntencao { ATACAR, DEFENDER, SANGRAR }
    private TipoIntencao intencaoEscolhida;

    private final Random rng = new Random();

    public ZumbiSangue(int vidaInicial, int escudoInicial, int ataque) {
        super("Zumbi de Sangue", vidaInicial, escudoInicial, ataque);
    }

    /**
     * Metodo que faz o Zumbi anunciar sua inteção ao final do turno, podendo ser atacar, ganhar escudo ou aplicar sangramento
     */
    @Override
    public void anunciarIntencao() {
        // Aqui vai escolher aleatoriamente qual ação o zumbi irá fazer, podendo atacar, usar escudo ou aplicar sangramento
        int escolha = rng.nextInt(3);

        if (escolha == 0) {
            intencaoEscolhida = TipoIntencao.ATACAR;
            intencaoDescricao = "Atacar (" + ataque + ")";
        } else if (escolha == 1) {
            intencaoEscolhida = TipoIntencao.DEFENDER;
            intencaoDescricao = "Ganhar escudo (3)";
        } else {
            intencaoEscolhida = TipoIntencao.SANGRAR;
            intencaoDescricao = "Aplicar Sangramento (1)";
        }
    }

    /**
     * Metodo no qual o Zumbi executa a ação, conforme sua intensão
     * 
     * @param heroi Representa o heroi/jogador (investigador)
     */
    @Override
    public void executarAcao(Heroi heroi) {
        // E aqui executa a ação escolhida, 
        switch (intencaoEscolhida) {
            case ATACAR:
                atacar(heroi);
                break;
            case DEFENDER:
                System.out.println(nome + " se defende e ganha escudo!");
                ganharEscudo(3);
                break;
            case SANGRAR:
                System.out.println("O zumbi rasga sua carne causando sangramento!");
                EfeitoSangramento efeito = new EfeitoSangramento(heroi, App.publisher, 1);
                App.publisher.inscrever(efeito);
                break;
        }
    }
}
