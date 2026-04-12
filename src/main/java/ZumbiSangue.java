import java.util.Random;

/**
 * Representa um dos inimigos do jogo, um zumbi de sangue, um inimigo com status melhores, e mais complexo
 */
public class ZumbiSangue extends Inimigo {

    protected enum TipoIntencao {
        ATACAR,
        DEFENDER,
        SANGRAR
    }

    protected final Random rng = new Random();
    protected final int escudoAoDefender;
    protected final int sangramentoAoSangrar;
    protected TipoIntencao intencaoEscolhida;

    public ZumbiSangue() {
        this("Zumbi de Sangue", 10, 3, 6, 3, 1);
    }

    public ZumbiSangue(int vidaInicial, int escudoInicial, int ataque) {
        this("Zumbi de Sangue", vidaInicial, escudoInicial, ataque, 3, 1);
    }

    protected ZumbiSangue(String nome, int vidaInicial, int escudoInicial, int ataque, int escudoAoDefender, int sangramentoAoSangrar) {
        super(nome, vidaInicial, escudoInicial, ataque);
        this.escudoAoDefender = escudoAoDefender;
        this.sangramentoAoSangrar = sangramentoAoSangrar;
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
            intencaoDescricao = "Ganhar escudo (" + escudoAoDefender + ")";
        } else {
            intencaoEscolhida = TipoIntencao.SANGRAR;
            intencaoDescricao = "Aplicar Sangramento (" + sangramentoAoSangrar + ")";
        }
    }

    /**
     * Metodo no qual o Zumbi executa a ação, conforme sua intensão
     * 
     * @param heroi Representa o heroi/jogador (investigador)
     */

    @Override
    public void executarAcao(Heroi heroi) {
        // E aqui executa a ação escolhida
        switch (intencaoEscolhida) {
            case ATACAR:
                atacar(heroi);
                break;

            case DEFENDER:
                System.out.println(nome + " se defende e ganha escudo!");
                ganharEscudo(escudoAoDefender);
                break;

            case SANGRAR:
                System.out.println("O zumbi rasga sua carne causando sangramento!");
                heroi.aplicarEfeito(new EfeitoSangramento(heroi, App.publisher, sangramentoAoSangrar));
                break;
        }
    }
}
