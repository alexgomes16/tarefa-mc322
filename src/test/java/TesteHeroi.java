import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Aqui testa a classe heroi
 */
public class TesteHeroi {

    private Publisher publisher;

    private static class EfeitoTeste extends Efeito {
        public EfeitoTeste(Entidade dono, Publisher publisher, int acumulos) {
            super("EfeitoTeste", dono, publisher, acumulos);
        }

        @Override
        public void serNotificado() {
            // efeito de teste, sem acao automatica
        }
    }

    @BeforeEach
    void setup() {
        publisher = new Publisher();
        App.publisher = publisher;
    }

    /**
     * Testa quando o escudo segura todo o dano e garante que a vida não muda
     */
    @Test
    void receberDanoAbsorvidoPorEscudo() {
        Heroi heroi = new Heroi(20, 3);

        heroi.receberDano(2);

        assertEquals(20, heroi.getVida());
        assertEquals(1, heroi.getEscudo());
    }

    /**
     * Testa quando parte do dano passa para a vida, e verifica se o calculo ta certo
     */
    @Test
    void receberDanoPassaParaVidaQuandoEscudoNaoSuficiente() {
        Heroi heroi = new Heroi(20, 3);

        heroi.receberDano(5);

        assertEquals(18, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }

    /**
     * Verifica o dano sem o escudo, e ve se está reduzindo a vida direito
     */
    @Test
    void receberDanoSemEscudo() {
        Heroi heroi = new Heroi(20, 0);

        heroi.receberDano(4);

        assertEquals(16, heroi.getVida());
        assertEquals(0, heroi.getEscudo());
    }

    /**
     * Garante que a vida não passa do maximo da vida do heroi
     */
    @Test
    void curarNaoUltrapassaVidaMaxima() {
        Heroi heroi = new Heroi(20, 3);
        heroi.zerarEscudo();
        heroi.receberDano(5);

        heroi.curar(10);

        assertEquals(20, heroi.getVida());
    }

    /**
     * Garante que a vida não passa do maximo da vida do heroi, e sem o print
     */
    @Test
    void curarSemMensagemNaoUltrapassaVidaMaxima() {
        Heroi heroi = new Heroi(20, 3);
        heroi.zerarEscudo();
        heroi.receberDano(6);

        heroi.curarSemMensagem(10);

        assertEquals(20, heroi.getVida());
    }

    /**
     * Testa se o comportamento da energia está certo, no inicio do turno, quando gasta energia e quando ganha energia
     */
    @Test
    void energiaSobeEDesceCorretamente() {
        Heroi heroi = new Heroi(20, 3);

        heroi.iniciarTurno();

        assertEquals(3, heroi.getEnergia());
        assertTrue(heroi.gastarEnergia(2));
        assertEquals(1, heroi.getEnergia());

        heroi.ganharEnergia(2);

        assertEquals(3, heroi.getEnergia());
        assertFalse(heroi.gastarEnergia(5));
        assertEquals(3, heroi.getEnergia());
    }

    /**
     * Testa resetar os status do heroi
     */
    @Test
    void restaurarBaseRestauraVidaEEscudo() {
        Heroi heroi = new Heroi(20, 3);
        heroi.zerarEscudo();
        heroi.receberDano(7);

        heroi.restaurarBase();

        assertEquals(20, heroi.getVida());
        assertEquals(1, heroi.getEscudo());
    }

    /**
     * Testa o acumulo de efeitos
     */
    @Test
    void aplicarEfeitoMesmaClasseAcumula() {
        Heroi heroi = new Heroi(20, 3);

        EfeitoTeste efeito1 = new EfeitoTeste(heroi, publisher, 1);
        EfeitoTeste efeito2 = new EfeitoTeste(heroi, publisher, 2);

        heroi.aplicarEfeito(efeito1);
        heroi.aplicarEfeito(efeito2);

        assertEquals("EfeitoTeste (3)", heroi.listarEfeitos());
        assertTrue(publisher.temSubscribers());
    }

    /**
     * Garante que limpa todos os efeitos do heroi
     */
    @Test
    void limparEfeitosRemoveTodos() {
        Heroi heroi = new Heroi(20, 3);

        EfeitoTeste efeito = new EfeitoTeste(heroi, publisher, 1);
        heroi.aplicarEfeito(efeito);

        heroi.limparEfeitos();

        assertEquals("Nenhum efeito em uso", heroi.listarEfeitos());
    }
}
