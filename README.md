# Tarefa01 - Alex Gomes Pessoa 294788

# 🧾 Historia

Nesse jogo você se torna um investigador paranormal, no qual foi designado para investigar a Mansão Leone, uma mansão no qual é habitada por diversas criaturas horrendas. 

Agora voce enfrentará duas criaturas horrendas, um fantasma que assombra a casa, e um terrivel Zumbi de Sangue que habita um dos quarto da mansão. Para isso você possui uma espinguarda, e uma habilidade, ou melhor, um ritual, você consegue conjurar uma proteção a sua frente lhe dando um escudo. Mas realizar essas ações consome sua energia, o que abrirá espaço para a terrível criatura te contra-atacar, tome cuidado. 

Para essa segunda tarefa, foi adicionado a pilha de compras e pilha de descarte, no qual decidirá as ações que voce poderá fazer contra as criaturas. Além disso, o Zimbi de Sangue oferece um perigo a mais, sendo mais esperto que o Fantasma ele pode querer se defender de seus ataques, assim adquirindo um escudo, o que dificultará voce a derrota-lo. Porém as criaturas apenas de crueis, são previsiveis, então voce poderá saber sua intensão, sabendo exatamente o que farão ao final dos turnos

# Jogabilidade

Em cada turno você começa com 3 de energia, e voce pode escolher entre duas cartas, essas cartas porém são embaralhas na pilha de compra e a cada turno voce comprará cartas, assim poderá usar as cartas de jogar, no fim do turno (seja porque voce encerrou seja porque ficou sem energia), essas cartas irão para a pilha de descarte. Se as cartas da pilha de compras acabar, o jogo irá pegar as cartas da pilha de descarte, embaralhar e montar uma nova pilha de compras. As opções de ação são: 

1. **Carta de Tiro**
   - Causa dano ao inimigo
   - Custo: 2 de energia

2. **Carta de Escudo**
   - Concede escudo ao jogador
   - Custo: 1 de energia

3. **Encerrar turno**

Quando você encerrar seu turno ou então ficar sem energia o inimigo irá fazer uma ação, podendo ser um ataque (dar dano), ou então uma defesa (adquirir escudo)

O jogo acaba quando voce derrotar os dois inimigos dessa casa, livrando-a do mal, ou então acabar morrendo na mãos dessas criaturas, boa sorte.

# Código:

Detalhes do código é que eu tive que mudar os textos do jogo pois cada acento e "ç" estava aparecendo de forma errada. Então adaptei os textos tirando os acentos, mas acho que isso não impede de entender os textos. 

Decidi colocar esses textos mais descritivos e com história para incrementar essa primeira tarefa (e me fazer dormir menos), e ser mais entendível para quem está jogando. Mas os textos são apenas um print básico se algo acontecer e/ou mostrando atributos dos objetos naquele momento (como vida, energia, escudo, etc).

Além disso foi adicionado uma refatoração do código, para evitar códigos repetidos, como pedido na tarefa. Além de eu ter adicionado o desafio extra, da intensão do inimigo aparecer para o jogador. 

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

E aproveite o jogo.
