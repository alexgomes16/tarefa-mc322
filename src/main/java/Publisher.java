import java.util.ArrayList;
import java.util.List;

/**
 * Classe Publisher, que é importante para o Padrão Observer
 */
public class Publisher {

    private List<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<>();
    }

    public void inscrever(Subscriber s) {
        subscribers.add(s);
    }

    public void desinscrever(Subscriber s) {
        subscribers.remove(s);
    }

    public void notificar() {
        List<Subscriber> copia = new ArrayList<>(subscribers);

        for (Subscriber s : copia) {
            s.serNotificado();
        }
    }

    public boolean temSubscribers() {
        return !subscribers.isEmpty();
    }
}
