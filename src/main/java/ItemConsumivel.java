/**
 * Classe que representa os itens básicas que são gastos e fazem algo, e são filhos da classe Item
 */
public class ItemConsumivel extends Item {

    public enum Tipo {
        FRASCO_DE_LODO,
        BANDAGEM
    }

    private final Tipo tipo;
    private int usosRestantes;

    /**
     * Metodo que representa cada item em si
     */
    public ItemConsumivel(Tipo tipo, int usosRestantes) {
        this.tipo = tipo;
        this.usosRestantes = usosRestantes;

        switch (tipo) {
            case FRASCO_DE_LODO:
                nome = "Frasco de Lodo";
                descricao = "Recupera 5 de vida";
                break;
            case BANDAGEM:
                nome = "Bandagem";
                descricao = "Recupera 3 de vida";
                break;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getUsosRestantes() {
        return usosRestantes;
    }

    /**
     * Metodo que usa os itens (se ainda possuir o item) e reduz um uso do item
     */
    public void usar(Heroi heroi, Jogo jogo) {
        if (usosRestantes <= 0) {
            System.out.println("Este item nao tem usos restantes.");
            return;
        }

        switch (tipo) {
            case FRASCO_DE_LODO:
                System.out.println("Voce bebe o Frasco de Lodo e recupera 5 de vida.");
                heroi.curar(5);
                break;
            case BANDAGEM:
                System.out.println("Voce usa uma Bandagem e recupera 3 de vida.");
                heroi.curar(3);
                break;
        }

        usosRestantes--;
    }
}
