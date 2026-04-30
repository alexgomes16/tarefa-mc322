import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe Batalha
 */
public class TesteBatalha {

    @BeforeEach
    void setup() {
        App.publisher = new Publisher();
    }

    /**
     * Simula uma batalha em que o heroi vence, e verifica se retorna que o heroi vence mesmo
     */
    @Test
    void batalhaPodeTerminarComVitoria() {
        Heroi heroi = new Heroi(20, 3);

        List<Carta> compra = new ArrayList<>();
        compra.add(new CartaDano());

        List<Carta> mao = new ArrayList<>();
        List<Carta> descarte = new ArrayList<>();

        Scanner entrada = new Scanner("1\n\n");
        Batalha batalha = new Batalha(entrada, heroi, compra, mao, descarte);

        boolean venceu = batalha.executar(new TesteInimigo("FantasmaTeste", 5, 0, 1));

        assertTrue(venceu);
        assertTrue(heroi.estaVivo());
    }

    /**
     * Simula uma batalha em que o heroi perde, e verifica se retorna isso mesmo
     */
    @Test
    void batalhaPodeTerminarComDerrota() {
        Heroi heroi = new Heroi(10, 0);

        List<Carta> compra = new ArrayList<>();
        List<Carta> mao = new ArrayList<>();
        List<Carta> descarte = new ArrayList<>();

        Scanner entrada = new Scanner("1\n\n\n\n\n");
        Batalha batalha = new Batalha(entrada, heroi, compra, mao, descarte);

        boolean venceu = batalha.executar(new TesteInimigo("Inimigo Forte", 20, 0, 50));

        assertFalse(venceu);
        assertEquals(0, heroi.getVida());
    }
}
