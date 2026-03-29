# Tarefa01 - Alex Gomes Pessoa 294788

# 🧾 Historia

Nesse jogo você se torna um investigador paranormal, no qual foi designado para investigar a Mansão Leone, uma mansão no qual é habitada por diversas criaturas horrendas. 

Agora voce enfrentará duas criaturas horrendas, um fantasma que assombra a casa, e um terrivel Zumbi de Sangue que habita um dos quarto da mansão. Para isso você possui uma espinguarda, e tres habilidade, a primeira voce realiza um ritual, colocando em sua arma uma maldição que a deixa mais poderosa, a segunda voce realiza um ritual que confere a voce uma proteção contra seu inimigo e por ultimo mas não menos importante, voce consegue desferir um corte ritualistico em seu inimigo, que o deixará sangrando e sofrendo. Mas realizar essas ações consome sua energia, o que abrirá espaço para a terrível criatura te contra-atacar, tome cuidado. 

Para essa terceira tarefa, foi adicionado as cartas de efeito, as cartas de sangramento (que funciona como o veneno), e a bala amaldiçoada (que funciona como a força no exemplo dado no enunciado). Então o segundo inimigo Zumbi de Sangue, consegue também usar a carta de efeito de sangramento, além do ataque e da defesa que ele já fazia, o que deixa os turnos um pouco mais complexos, e a batalha mais cruel. 

# Jogabilidade

Em cada turno você começa com 3 de energia, e voce pode escolher entre quatro cartas, essas cartas porém são embaralhas na pilha de compra e a cada turno voce comprará cartas, assim poderá usar as cartas de jogar, no fim do turno (seja porque voce encerrou seja porque ficou sem energia), essas cartas irão para a pilha de descarte. Se as cartas da pilha de compras acabar, o jogo irá pegar as cartas da pilha de descarte, embaralhar e montar uma nova pilha de compras. As opções de ação são: 

1. **Carta de Tiro**
   - Causa dano ao inimigo
   - Custo: 2 de energia

2. **Carta de Escudo**
   - Concede escudo ao jogador
   - Custo: 1 de energia

3. **Carta de Sangramento**
   - Ao final do turno o inimigo sofre o dado de sangramento, que é acumulável. 
   - Custo: 1 de energia

4. **Carta de Bala Amaldiçoada**
   - Concede +1 no so seu tiro
   - Custo: 1 de energia

5. **Encerrar turno**

Quando você encerrar seu turno ou então ficar sem energia o inimigo irá fazer uma ação, podendo ser um ataque (dar dano), uma defesa (adquirir escudo), ou então te aplicar sangramento (1 de dano ao final do turno)

O jogo acaba quando voce derrotar os dois inimigos dessa casa, livrando-a do mal, ou então acabar morrendo na mãos dessas criaturas, boa sorte.

# Código:

Detalhes do código é que eu tive que mudar os textos do jogo pois cada acento e "ç" estava aparecendo de forma errada. Então adaptei os textos tirando os acentos, mas acho que isso não impede de entender os textos. 

Decidi colocar esses textos mais descritivos e com história para incrementar essa primeira tarefa (e me fazer dormir menos), e ser mais entendível para quem está jogando. Mas os textos são apenas um print básico se algo acontecer e/ou mostrando atributos dos objetos naquele momento (como vida, energia, escudo, etc).

Implementei o padrão Observer, atráves das classes Publisher e Subscriber e o sistema de efeitos como dito anteriormente. Tudo utilizando o encapsulamento, como na tarefa anterior

# Como rodar o projeto

- Primeiro clone o repositorio;

- Compile o projeto:
```
javac -d bin $(find src -name "*.java")
```

- Execute o projeto:
```
java -cp bin App
```

E por fim, aproveite o jogo e acabe com o mal dessa Mansão.
