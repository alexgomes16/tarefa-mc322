//Aqui é a classe entidade, que representa os personagens da batalha, seja o heroi ou os inimigos (vai ser a classe pai dessas outras classes)
import java.util.Objects;

public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;

    public Entidade(String nome, int vida, int escudo) {
        this.nome = Objects.requireNonNull(nome);
        this.vida = vida;
        this.escudo = escudo;
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

    // Metodo de reeceber dano, onde verifica primeiro se o dano é menor que o escudo, se for reduz isso do escudo e imprime uma mensagem disso
    public void receberDano(int quantidade) {
        System.out.println(this.nome + " recebe " + quantidade + " de dano (aplicado ao escudo antes da vida).");
        if (escudo >= quantidade) {
            escudo -= quantidade;
            System.out.println(this.nome + " teve todo o dano absorvido pelo escudo. Escudo restante: " + escudo);
        } else {
            // Caso o dano for superior ao escudo, eu vejo o quanto o escudo reduz desse dano, zero o escudo, retiro essa vida, imprimo uma mensagem
            int restante = quantidade - escudo;
            escudo = 0;
            vida -= restante;
            System.out.println(this.nome + " perde " + restante + " de vida.");
            if (vida < 0) {
                // Caso a vida fique negativa eu deixo zerado mesmo
                vida = 0;
            }
        }
    }

    // Metodo que da escudo a entidade, adicionando a quantidade correspondende ao escudo e imprimindo uma mensagem
    public void ganharEscudo(int quantidade) {
        this.escudo += quantidade;
        System.out.println(this.nome + " ganha " + quantidade + " de escudo. Escudo agora: " + this.escudo);
    }

    public void zerarEscudo() {
        this.escudo = 0;
    }
}
