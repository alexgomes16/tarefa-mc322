# Tarefa01 - Alex Gomes Pessoa 294788

# 🧾 Historia

Nesse jogo você se torna um investigador paranormal, no qual foi designado para investigar a Mansão Leone, uma mansão no qual é habitada por diversas criaturas horrendas. 

Agora voce enfrentará duas criaturas horrendas, um fantasma que assombra a casa, e um terrivel Zumbi de Sangue que habita um dos quarto da mansão. Para isso você possui uma espinguarda, e também consegue realizar rituais poderosos, voce conhece 7 rituais: o primeiro voce realiza um ritual, colocando em sua arma uma maldição que a deixa mais poderosa, a segunda voce realiza um ritual que confere a voce uma proteção contra seu inimigo, o terceiro voce consegue desferir um corte ritualistico em seu inimigo, que o deixará sangrando e sofrendo, o quarto voce gera uma poderosa descarga eletrica contra o seu inimigo lhe ferindo ainda mais, o quinto voce deixa o inimigo mais indefeso, o sexto voce prende o seu inimigo em uma redoma eterea, não podendo te tocar, e em setimo voce deixa o inimigo mais lento no tempo. Mas realizar essas ações consome sua energia, o que abrirá espaço para a terrível criatura te contra-atacar, tome cuidado. Para isso, voce leva consigo um alimento energético, onde lhe restaura parte de sua energia para continuar a batalha.

Será que voce sobreviverá a essa Mansão Amaldiçoada?

# Jogabilidade

Em cada turno você começa com 3 de energia, e voce pode escolher entre quatro cartas, essas cartas porém são embaralhas na pilha de compra e a cada turno voce comprará cartas, assim poderá usar as cartas de jogar, no fim do turno (seja porque voce encerrou seja porque ficou sem energia), essas cartas irão para a pilha de descarte. Se as cartas da pilha de compras acabar, o jogo irá pegar as cartas da pilha de descarte, embaralhar e montar uma nova pilha de compras. As opções de ação são: 

1. **Carta de Tiro**
   - Causa 5 de dano ao inimigo
   - Custo: 2 de energia

2. **Carta de Escudo**
   - Concede 5 de escudo ao jogador
   - Custo: 1 de energia

3. **Carta de Sangramento**
   - Carta acumulavel, onde da +1 de dano no inimigo ao final do turno 
   - Custo: 1 de energia

4. **Carta de Bala Amaldiçoada**
   - Concede +1 no dano do seu tiro (esse efeito é acumulavel e acaba ao dar um tiro)
   - Custo: 1 de energia

5. **Carta de Eletrocussão**
   - Ritual que da 8 de dano no inimigo
   - Custo: 3 de energia

6. **Carta de Antidefesa**
   - Ritual que remove 2 do escudo do inimigo
   - Custo: 1 de energia

7. **Carta de Alimento energético**
   - Confere +2 de energia ao heroi
   - Custo: não gasta energia

8. **Carta de Aprisionamento**
   - O ritual tem 40% de chance de impedir que o inimigo faça a ação dele no turno dele
   - Custo: 2 de energia

9. **Carta de Lentidão**
   - Ritual que diminui o dano do inimigo em -1
   - Custo: 1 de energia

10. **Encerrar turno**

Quando você encerrar seu turno ou então ficar sem energia o inimigo irá fazer uma ação, podendo ser um ataque (dar dano), uma defesa (adquirir escudo), ou então te aplicar sangramento (1 de dano ao final do turno)

O jogo acaba quando voce derrotar os dois inimigos dessa casa, livrando-a do mal, ou então acabar morrendo na mãos dessas criaturas, boa sorte.

# Código:

Detalhes do código é que eu tive que mudar os textos do jogo pois cada acento e "ç" estava aparecendo de forma errada. Então adaptei os textos tirando os acentos, mas acho que isso não impede de entender os textos. 

Decidi colocar esses textos mais descritivos e com história para incrementar essa primeira tarefa (e me fazer dormir menos), e ser mais entendível para quem está jogando. Mas os textos são apenas um print básico se algo acontecer e/ou mostrando atributos dos objetos naquele momento (como vida, energia, escudo, etc).

Para essa quarta tarefa, eu refatorei o meu projeto para utilizar o Gradle, alterando a estrutura de pastas e instalando a ferramenta no projeto. Além disso adicionei 5 novas cartas (que serão explicadas a seguir), e além disso documentei o código utilizando o Javadoc, explicando as classes e principais metodos (e seus retornos e/ou parametros).

Além disso, arrumei melhor o padrão Observer do efeito de bala amaldiçoada que foi um ponto de erro na tarefa anterior. 

# Como rodar o projeto

- Primeiro clone o repositorio;

- Compile o projeto:
```
./gradlew build
```

- Execute o projeto:
```
./gradlew run 
```

E por fim, aproveite o jogo e acabe com o mal dessa Mansão.
