import java.util.Random;

/**
 * Representa o inimigo final do jogo, O Titã de Sangue, inimigo com o maior status do jogo e com uma habilidade especial
 */
public class TitaSangue extends Inimigo {

    private enum TipoIntencao {
        ATACAR,
        DEFENDER,
        SANGRAR,
        EXPLODIR
    }

    private final Random rng = new Random();
    private final int escudoAoDefender = 5;
    private final int sangramentoAoSangrar = 3;
    private TipoIntencao intencaoEscolhida;
    private boolean explosaoUsada = false;

    public TitaSangue() {
        super("Tita de Sangue", 14, 4, 8);
    }

    /**
     * Metodo que faz o Titã anunciar sua inteção ao final do turno, podendo ser atacar, ganhar escudo, aplicar sangramento ou então explodir em sangue, onde da o maior dano
     */
    @Override
    public void anunciarIntencao() {
        // Aqui decide no inicio se o Titã vai usar sua maior habilidade (separado pois a chance de ser essa ação tem que ser menor, pois é bem forte)
        if (!explosaoUsada && rng.nextInt(8) == 0) {
            intencaoEscolhida = TipoIntencao.EXPLODIR;
            intencaoDescricao = "Explodir em sangue (11)";
            return;
        }

        // Aqui vai escolher aleatoriamente qual ação o zumbi irá fazer
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
        switch (intencaoEscolhida) {
            case ATACAR:
                atacar(heroi);
                break;

            case DEFENDER:
                System.out.println(nome + " se defende e ganha escudo!");
                ganharEscudo(escudoAoDefender);
                break;

            case SANGRAR:
                System.out.println("O Tita de Sangue rasga o ar e causa sangramento!");
                heroi.aplicarEfeito(new EfeitoSangramento(heroi, App.publisher, sangramentoAoSangrar));
                break;

            case EXPLODIR:
                System.out.println("O Tita de Sangue explode em sangue e causa 11 de dano!");
                heroi.receberDano(11);
                explosaoUsada = true;
                break;
        }
    }
}
