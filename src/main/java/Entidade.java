import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma entidade do jogo, seja o heroi ou o inimigo
 */
public abstract class Entidade {

    protected String nome;
    protected int vida;
    protected int escudo;
    protected List<Efeito> efeitos;

    public Entidade(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.efeitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getEscudo() {
        return escudo;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Metodo que aplica e acumula o efeito na entidade
     * 
     * @param novo Novo efeito
     */
    public void aplicarEfeito(Efeito novoEfeito) {
        for (Efeito efeitoExistente : efeitos) {
    
            if (efeitoExistente.getClass().equals(novoEfeito.getClass())) {
                efeitoExistente.acumulos += novoEfeito.acumulos;
                return;
            }
        }
    
        efeitos.add(novoEfeito);
        App.publisher.inscrever(novoEfeito);
    }

    /**
     * Metodo que remove o efeito de uma entidade
     * 
     * @param efeito efeito que vai ser removido
     */
    public void removerEfeito(Efeito efeito) {
        efeitos.remove(efeito);
    }

    /**
     * Metodo que limpa todos os efeitos ativos
     */
    public void limparEfeitos() {
        efeitos.clear();
    }

    /**
     * Metodo que lista todos os efeitos que estão sendo aplicados na entidade
     */
    public String listarEfeitos() {
        if (efeitos.isEmpty()) {
            return "Nenhum efeito em uso";
        }

        StringBuilder sb = new StringBuilder();
        for (Efeito efeito : efeitos) {
            sb.append(efeito.getString()).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * Metodo que a entidade recebe um dano
     * 
     * @param dano dano recebido pela entidade
     */
    public void receberDano(int dano) {

        System.out.println(nome + " recebe " + dano + " de dano.");
        // Verifica se o dano recebido é menor que o escudo, se sim retira esse dano do escudo e imprime a mensage
        if (escudo >= dano) {
            escudo -= dano;
            System.out.println("O escudo absorveu todo o dano! Escudo restante: " + escudo);
        } else {
            // Caso o contrário, eu vejo o quando de dano sobrou e quanto foi absorvido, e imprime as mensagem e retira a vida da entidade
            int absorvido = escudo;
            int restante = dano - escudo;

            if (escudo > 0) {
                System.out.println("O escudo absorveu " + absorvido + " de dano.");
            }

            escudo = 0;
            vida -= restante;

            System.out.println(nome + " perdeu " + restante + " de vida.");

            // Caso a vida tenha ficado negativa, eu coloco 0, pois não tem vida negativa
            if (vida < 0) {
                vida = 0;
            }
        }
    }

    /**
     * Metodo da entidade ganhar escudo
     * 
     * @param valor quantidade de escudo ganho pela entidade
     */
    public void ganharEscudo(int valor) {
        escudo += valor;
        System.out.println(nome + " ganha " + valor + " de escudo.");
    }

    /**
     * Metodo que reduz uma quantidade do escudo da entidade
     * 
     * @param valor quantidade que reduz 
     */
    public void reduzirEscudo(int valor) {
        escudo -= valor;
        if (escudo < 0) {
            escudo = 0;
        }
        System.out.println(nome + " perde " + valor + " de escudo. Escudo restante: " + escudo);
    }

    /**
     * Metodo que zera o escudo da entidade
     */
    public void zerarEscudo() {
        escudo = 0;
    }
}
