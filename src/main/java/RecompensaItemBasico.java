import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa o evento de usar um item básico (que tem bonus diferentes para cada)
 */
public class RecompensaItemBasico extends Escolha {

    @Override
    public boolean iniciar(Jogo jogo, Scanner entrada) {
        List<ItemBasico.Tipo> possiveis = new ArrayList<>();
        Collections.addAll(possiveis,
                ItemBasico.Tipo.COLAR_DA_SORTE,
                ItemBasico.Tipo.COLETE,
                ItemBasico.Tipo.CASACO_DE_LODO,
                ItemBasico.Tipo.CRANIO_DE_LODO,
                ItemBasico.Tipo.FACA_AFIADA,
                ItemBasico.Tipo.CICATRIZANTE,
                ItemBasico.Tipo.RADIO_PARANORMAL
        );

        // Embaralha os possiveis itens que pode aparecer e pega os 3 primeiros (pois só aparece 3 opções de escolha para o jogador)
        Collections.shuffle(possiveis);
        possiveis = possiveis.subList(0, 3);

        while (true) {
            // Imprime a escolha para o jogador
            System.out.println();
            System.out.println("Tres itens aparecem entre a poeira da mansao:");
            for (int i = 0; i < possiveis.size(); i++) {
                ItemBasico item = new ItemBasico(possiveis.get(i));
                System.out.println((i + 1) + ") " + item.getNome() + " - " + item.getDescricao());
            }
            System.out.println("4) Cancelar");

            // Pega a opção do jogador
            int escolha = lerOpcao(entrada, 1, 4);
            if (escolha == 4) {
                return false;
            }

            // Então usa o bonus do item escolhido e imprime uma mensagem pro jogador de que ele pegou o item em questão
            ItemBasico itemEscolhido = new ItemBasico(possiveis.get(escolha - 1));
            itemEscolhido.aplicar(jogo.getHeroi());
            System.out.println("Voce obteve: " + itemEscolhido.getNome());
            return true;
        }
    }
}
