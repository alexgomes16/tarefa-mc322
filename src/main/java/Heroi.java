// Está é a classe de Heroi que herda a classe Entidade

public class Heroi extends Entidade {
    private int energia;

    public Heroi(int vida, int escudo) {
        super("Investigador", vida, escudo);
    }

    public void iniciarTurno() {
        energia = 3;
        zerarEscudo();
    }

    public int getEnergia() {
        return energia;
    }

    public boolean gastarEnergia(int custo) {
        if (energia >= custo) {
            energia -= custo;
            return true;
        }
        return false;
    }

    public int consumirBonusDano() {
        for (Efeito efeito : efeitos) {
            if (efeito instanceof EfeitoBalaAmaldicoada) {
                return ((EfeitoBalaAmaldicoada) efeito).consumirBonus();
            }
        }
        return 0;
    }

    public void restaurarBase() {
        this.vida = 20;
        this.escudo = 1;
    }
}
