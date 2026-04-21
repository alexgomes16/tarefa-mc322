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
                (Scanner entrada, Heroi heroi) -> {
                    heroi.ganharEnergia(1);
                    return true;
                }
        );

        Heroi heroi = new Heroi(20, 3);
        heroi.iniciarTurno();

        boolean continua = evento.executarAcao(
                new Scanner(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8))),
                heroi
        );

        assertTrue(continua);
        assertEquals(4, heroi.getEnergia());
        assertTrue(evento.isEvento());
    }
}
