import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa todas as cartas do jogo
 */
public class TesteCarta {

    @BeforeEach
    void setup() {
        App.publisher = new Publisher();
    }

    /**
     * Testa a carta de eletrocussão (se da os 8 de dano)
     */
    @Test
    void cartaEletrocussaoCausaOitoDeDano() {
        Heroi heroi = new Heroi(20, 3);
        TesteInimigo inimigo = new TesteInimigo("Alvo", 20, 0, 4);

        new CartaEletrocussao().usar(heroi, inimigo);

        assertEquals(12, inimigo.getVida());
    }

    /**
     * Testa a carta de antidefesa (se retira o escudo do inimigo)
     */
    @Test
    void cartaAntidefesaReduzEscudoDoInimigo() {
        Heroi heroi = new Heroi(20, 3);
        TesteInimigo inimigo = new TesteInimigo("Alvo", 20, 5, 4);

        new CartaAntidefesa().usar(heroi, inimigo);

        assertEquals(3, inimigo.getEscudo());
    }

    /**
     * Testa a carta de energia (se aumenta a energia do heroi)
     */
    @Test
    void cartaEnergiaAumentaEnergiaDoHeroi() {
        Heroi heroi = new Heroi(20, 3);
        heroi.iniciarTurno();

        new CartaEnergia().usar(heroi, new TesteInimigo("Alvo", 20, 0, 4));

        assertEquals(5, heroi.getEnergia());
    }

    /**
     * Testa a carta de Frasco de Lodo (se cura corretamente)
     */
    @Test
    void cartaFrascoDeLodoCuraOMaximoPermitido() {
        Heroi heroi = new Heroi(20, 3);
        heroi.zerarEscudo();
        heroi.receberDano(5);

        new CartaFrascoLodo().usar(heroi, new TesteInimigo("Alvo", 20, 0, 4));

        assertEquals(20, heroi.getVida());
    }

    /**
     * Testa a carta de sangramento (se é aplicado)
     */
    @Test
    void cartaSangramentoAplicaEfeitoAoInimigo() {
        Heroi heroi = new Heroi(20, 3);
        TesteInimigo inimigo = new TesteInimigo("Alvo", 20, 0, 4);

        new CartaSangramento().usar(heroi, inimigo);

        assertTrue(inimigo.listarEfeitos().contains("Sangramento"));
    }

    /**
     * Testa a carta de bala amaldiçoada (se o efeito é consumido)
     */
    @Test
    void balaAmaldicoadaAumentaOTiroSeguinte() {
        Heroi heroi = new Heroi(20, 3);
        TesteInimigo inimigo = new TesteInimigo("Alvo", 20, 0, 4);

        new CartaBalaAmaldicoada().usar(heroi, inimigo);
        assertTrue(heroi.listarEfeitos().contains("Bala Amaldicoada"));

        new CartaDano().usar(heroi, inimigo);

        assertEquals(14, inimigo.getVida());
        assertEquals("Nenhum efeito em uso", heroi.listarEfeitos());
    }

    /**
     * Testa a carta de lentidão (se o debuff foi aplicado)
     */
    @Test
    void cartaLentidaoReduzProximoAtaqueDoInimigo() {
        Heroi heroi = new Heroi(20, 3);
        heroi.zerarEscudo();
        TesteInimigo inimigo = new TesteInimigo("Alvo", 20, 0, 4);

        new CartaLentidao().usar(heroi, inimigo);
        inimigo.atacarDireto(heroi);

        assertEquals(17, heroi.getVida());
    }
}
