// Classe que representa uma entidade do jogo, seja o heroi ou o inimigo

import java.util.ArrayList;
import java.util.List;

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

    public void aplicarEfeito(Efeito novo) {
        for (Efeito efeito : efeitos) {
            if (efeito.getNome().equals(novo.getNome())) {
                efeito.adicionarAcumulo(novo.getAcumulos());
                return;
            }
        }

        efeitos.add(novo);
        novo.publisher.inscrever(novo);
    }

    public void removerEfeito(Efeito efeito) {
        efeitos.remove(efeito);
    }

    public String listarEfeitos() {
        if (efeitos.isEmpty()) {
            return "Nenhum efeito em uso";
        }

        StringBuilder sb = new StringBuilder();
        for (Efeito efeito : efeitos) {
            sb.append(efeito.getString()).append(" ");
        }
        return sb.toString();
    }

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

    public void ganharEscudo(int valor) {
        escudo += valor;
        System.out.println(nome + " ganha " + valor + " de escudo.");
    }

    public void zerarEscudo() {
        escudo = 0;
    }
}
