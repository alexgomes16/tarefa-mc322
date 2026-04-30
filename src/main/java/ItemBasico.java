/**
 * Classe que representa os itens básicas que possuem efeitos próprios, e são filhos da classe Item
 */
public class ItemBasico extends Item {

    public enum Tipo {
        COLAR_DA_SORTE,
        COLETE,
        CASACO_DE_LODO,
        CRANIO_DE_LODO,
        FACA_AFIADA,
        CICATRIZANTE,
        RADIO_PARANORMAL
    }

    private final Tipo tipo;

    /**
     * Metodo representa cada item em si 
     */
    public ItemBasico(Tipo tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case COLAR_DA_SORTE:
                nome = "Colar da Sorte";
                descricao = "Proximo tiro da batalha ganha +2 de dano";
                break;
            case COLETE:
                nome = "Colete";
                descricao = "Proxima batalha com +3 de escudo";
                break;
            case CASACO_DE_LODO:
                nome = "Casaco de Lodo";
                descricao = "Reduz em 1 o dano de sangramento da proxima batalha";
                break;
            case CRANIO_DE_LODO:
                nome = "Cranio de Lodo";
                descricao = "Proxima batalha com 4 de energia";
                break;
            case FACA_AFIADA:
                nome = "Faca Afiada";
                descricao = "Sangramento da proxima batalha causa +1";
                break;
            case CICATRIZANTE:
                nome = "Cicatrizante";
                descricao = "Recupera 4 de vida agora";
                break;
            case RADIO_PARANORMAL:
                nome = "Radio Paranormal";
                descricao = "Reduz em 1 o dano do proximo inimigo";
                break;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Metodo que aplica o bonus do item em questão
     * @param heroi
     */
    public void aplicar(Heroi heroi) {
        switch (tipo) {
            case COLAR_DA_SORTE:
                heroi.setBonusPrimeiroTiroProximaBatalha(2);
                System.out.println("O Colar da Sorte pulsa em sua pele.");
                break;
            case COLETE:
                heroi.setEscudoInicialProximaBatalha(3);
                System.out.println("O Colete protege seu corpo para a proxima batalha.");
                break;
            case CASACO_DE_LODO:
                heroi.setRedutorSangramentoProximaBatalha(1);
                System.out.println("O Casaco de Lodo absorve parte do veneno da escuridao.");
                break;
            case CRANIO_DE_LODO:
                heroi.setEnergiaProximaBatalha(4);
                System.out.println("O Cranio de Lodo distorce o tempo em sua volta.");
                break;
            case FACA_AFIADA:
                heroi.setBonusSangramentoProximaBatalha(1);
                System.out.println("A Faca Afiada promete feridas mais profundas.");
                break;
            case CICATRIZANTE:
                heroi.curar(4);
                break;
            case RADIO_PARANORMAL:
                heroi.setRedutorDanoProximaBatalha(1);
                System.out.println("O Radio Paranormal abafa ecos de violencia, distraindo criaturas.");
                break;
        }
    }
}
