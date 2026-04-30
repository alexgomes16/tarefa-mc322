import java.util.Scanner;

/**
 * Classe que representa os eventos pós batalha, ou seja são as recompensas após a batalha
 */
public class EventoPosBatalha extends Escolha {

    @Override
    public boolean iniciar(Jogo jogo, Scanner entrada) {
        System.out.println();
        System.out.println("A energia da casa enfraquece por um instante...");
        System.out.println("Uma recompensa aparece diante de voce.");

        while (true) {
            // Imprime as opções para o jogador escolher
            System.out.println();
            System.out.println("Escolha uma recompensa:");
            System.out.println("1) Aprimorar suas habilidades");
            System.out.println("2) Pegar um item basico");
            System.out.println("3) Usar item consumivel");
            System.out.println("4) Ignorar recompensa");

            int escolha = lerOpcao(entrada, 1, 4);

            //Então pega a opção escolhida pelo jogador e realiza a ção escolhida (seja aprimorar as habilidades, pegar um item básico ou consumivel, ou fazer nada disso)
            if (escolha == 1) {
                boolean ok = new RecompensaBaralho().iniciar(jogo, entrada);
                if (ok) return true;
                continue;
            }

            if (escolha == 2) {
                boolean ok = new RecompensaItemBasico().iniciar(jogo, entrada);
                if (ok) return true;
                continue;
            }

            if (escolha == 3) {
                boolean ok = jogo.usarItemConsumivel(entrada);
                if (ok) return true;
                continue;
            }

            System.out.println("Voce deixa a recompensa para tras.");
            return true;
        }
    }
}
