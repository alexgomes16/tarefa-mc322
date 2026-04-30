import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe Mapa e NoMapa
 */
public class TesteMapa {

    /**
     * Verifica a raiz do mapa, a estrutura inicial
     */
    @Test
    void mapaComecaNoHallDeEntrada() {
        Mapa mapa = Mapa.criarMapa();

        assertNotNull(mapa.getRaiz());
        assertEquals("Hall de entrada", mapa.getRaiz().getNome());
        assertTrue(mapa.getRaiz().isBatalha());
        assertEquals(2, mapa.getRaiz().getFilhosNaoVisitados().size());
    }

    /**
     * Testa conexão entre nós
     */
    @Test
    void mapaTemEscadaComoProgresso() {
        Mapa mapa = Mapa.criarMapa();
        NoMapa raiz = mapa.getRaiz();

        List<NoMapa> filhos = raiz.getFilhosNaoVisitados();
        NoMapa cozinhaOuSala = filhos.get(0);

        assertEquals(1, cozinhaOuSala.getFilhosNaoVisitados().size());
        NoMapa escada = cozinhaOuSala.getFilhosNaoVisitados().get(0);

        assertEquals("Escada", escada.getNome());
        assertTrue(escada.isEvento());
    }

    /**
     * Testa os eventos do mapa, se funciona direito
     */
    @Test
    void executarAcaoDeEventoFunciona() {
        NoMapa evento = new NoMapa(
                "Evento de Teste",
                "",
                new Escolha() {
                    @Override
                    public boolean iniciar(Jogo jogo, Scanner entrada) {
                        jogo.getHeroi().ganharEnergia(1);
                        return true;
                    }
                },
                false
        );

        Heroi heroi = new Heroi(20, 3);
        Jogo jogo = new Jogo(heroi);

        boolean continua = evento.iniciar(
                jogo,
                new Scanner(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)))
        );

        assertTrue(continua);
        assertEquals(1, heroi.getEnergia());
        assertTrue(evento.isEvento());
    }
}
