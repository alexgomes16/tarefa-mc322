import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe Publisher
 */
public class TestePublisher {

    private static class ContadorSubscriber implements Subscriber {
        private int chamadas = 0;

        @Override
        public void serNotificado() {
            chamadas++;
        }
    }

    /**
     * Verifica se o observer funciona
     */
    @Test
    void inscreverENotificarChamaSubscriber() {
        Publisher publisher = new Publisher();
        ContadorSubscriber subscriber = new ContadorSubscriber();

        publisher.inscrever(subscriber);
        publisher.notificar();

        assertEquals(1, subscriber.chamadas);
    }

    /**
     * Garante que não notifica depois
     */
    @Test
    void desinscreverRemoveSubscriber() {
        Publisher publisher = new Publisher();
        ContadorSubscriber subscriber = new ContadorSubscriber();

        publisher.inscrever(subscriber);
        publisher.desinscrever(subscriber);
        publisher.notificar();

        assertEquals(0, subscriber.chamadas);
        assertFalse(publisher.temSubscribers());
    }
}
