# Sudoku em Java

Projeto desenvolvido em **Java 21** com o objetivo de construir um jogo de Sudoku executado via terminal, aplicando conceitos de **programação orientada a objetos**, **matrizes**, **validação de regras de negócio**, **geração aleatória de tabuleiros** e **organização em camadas**.

O projeto foi criado como parte de um desafio de bootcamp, com foco em desenvolver a lógica do jogo de forma limpa, organizada e evolutiva.

---

## Sobre o jogo

O Sudoku é um jogo de lógica baseado em uma grade 9x9, dividida em nove blocos 3x3.

O objetivo do jogador é preencher todas as células vazias com números de 1 a 9, respeitando as seguintes regras:

- Cada linha deve conter os números de 1 a 9 sem repetição.
- Cada coluna deve conter os números de 1 a 9 sem repetição.
- Cada bloco 3x3 deve conter os números de 1 a 9 sem repetição.
- Os números iniciais do tabuleiro são fixos e não podem ser alterados.

---

## Funcionalidades implementadas

- Iniciar uma nova partida.
- Escolher nível de dificuldade.
- Gerar tabuleiro aleatório.
- Exibir tabuleiro no terminal.
- Inserir números no tabuleiro.
- Remover números inseridos pelo usuário.
- Bloquear alteração de células fixas.
- Validar jogadas com base nas regras do Sudoku.
- Verificar se o Sudoku foi resolvido corretamente.
- Exibir status atual da partida.
- Interface interativa via console.

---

## Níveis de dificuldade

O jogo possui três níveis de dificuldade:

| Dificuldade | Células removidas | Descrição |
|------------|-------------------|-----------|
| Fácil | 35 | Mais números visíveis no início da partida |
| Médio | 45 | Nível intermediário de desafio |
| Difícil | 55 | Menos números visíveis, exigindo mais raciocínio |

Quanto mais células são removidas do tabuleiro inicial, maior é o nível de dificuldade para o jogador.

---

## Como funciona a geração do Sudoku

O projeto não utiliza um tabuleiro fixo.

A cada nova partida, o sistema gera uma solução válida de Sudoku utilizando um algoritmo de **backtracking**.

O processo funciona da seguinte forma:

1. Um tabuleiro vazio 9x9 é criado.
2. O algoritmo tenta preencher cada célula com números de 1 a 9.
3. A ordem dos números é embaralhada para gerar resultados diferentes.
4. Cada número é validado com base nas regras de linha, coluna e bloco 3x3.
5. Caso uma tentativa não funcione, o algoritmo retorna e tenta outro número.
6. Após gerar um tabuleiro completo e válido, algumas células são removidas conforme a dificuldade escolhida.

Dessa forma, cada partida apresenta um desafio diferente, evitando que o usuário memorize sempre o mesmo tabuleiro.

---

## Tecnologias utilizadas

- Java 21
- Maven
- IntelliJ IDEA
- Git
- GitHub

---

## Conceitos aplicados

Este projeto aplica diversos conceitos importantes da programação, como:

- Programação Orientada a Objetos
- Encapsulamento
- Separação de responsabilidades
- Manipulação de matrizes
- Validação de regras de negócio
- Tratamento de exceções
- Entrada e saída de dados via console
- Algoritmo de backtracking
- Organização em pacotes
- Versionamento com Git

---

## Estrutura do projeto

```text
sudoku-java
│
├── src
│   └── main
│       └── java
│           └── br
│               └── dev
│                   └── fabiosimones
│                       └── sudoku
│                           │
│                           ├── Main.java
│                           │
│                           ├── model
│                           │   ├── Board.java
│                           │   ├── Cell.java
│                           │   ├── Difficulty.java
│                           │   └── SudokuPuzzle.java
│                           │
│                           ├── service
│                           │   ├── SudokuGenerator.java
│                           │   ├── SudokuService.java
│                           │   └── SudokuValidator.java
│                           │
│                           └── ui
│                               └── ConsoleUI.java
│
├── pom.xml
└── README.md
```
## Responsabilidade das classes

### `Main.java`

Classe principal da aplicação.

Responsável por iniciar a interface do jogo no console.

---

### `Cell.java`

Representa uma célula individual do Sudoku.

Cada célula possui:

- valor numérico;
- indicação se é fixa;
- validação para permitir apenas valores entre 0 e 9.

O valor `0` representa uma célula vazia.

---

### `Board.java`

Representa o tabuleiro 9x9 do Sudoku.

Responsável por:

- armazenar as células;
- retornar valores;
- alterar valores;
- limpar células editáveis;
- verificar se o tabuleiro está completo;
- converter o tabuleiro para matriz.

---

### `Difficulty.java`

Enum responsável por representar os níveis de dificuldade do jogo.

Cada dificuldade define quantas células serão removidas do tabuleiro inicial.

---

### `SudokuPuzzle.java`

Representa um Sudoku gerado pelo sistema.

Armazena:

- tabuleiro inicial;
- solução completa;
- dificuldade escolhida.

---

### `SudokuGenerator.java`

Responsável por gerar tabuleiros aleatórios de Sudoku.

Utiliza backtracking para criar uma solução válida e, depois, remove células de acordo com o nível de dificuldade.

---

### `SudokuValidator.java`

Responsável por validar as regras do Sudoku.

Verifica:

- se a posição informada é válida;
- se o número está entre 1 e 9;
- se a célula é fixa;
- se o número já existe na linha;
- se o número já existe na coluna;
- se o número já existe no bloco 3x3;
- se o tabuleiro foi resolvido corretamente.

---

### `SudokuService.java`

Camada responsável por controlar a partida.

Essa classe integra:

- geração do tabuleiro;
- tabuleiro atual;
- solução do jogo;
- validação de jogadas;
- remoção de números;
- verificação de vitória;
- status da partida.

---

### `ConsoleUI.java`

Responsável pela interação com o usuário via terminal.

Exibe menus, recebe entradas do jogador e chama os métodos da camada de serviço.

---

## Como executar o projeto

### Pré-requisitos

Antes de executar, é necessário ter instalado:

- Java 21;
- IntelliJ IDEA ou outra IDE Java;
- Maven.

---

## Executando pelo IntelliJ IDEA
1.  Clone o repositório:
````bash
git clone https://github.com/SEU-USUARIO/sudoku-java.git
````
2.  Abra o projeto no IntelliJ IDEA.
3.  Aguarde o Maven carregar as dependências.
4.  Acesse a classe:
````bash
src/main/java/br/dev/fabiosimones/sudoku/Main.java
````
5.  Execute o método `main`.

---

## Exemplo de uso
- Ao iniciar a aplicação, o usuário verá o menu:

````bash
===================================
         SUDOKU EM JAVA
===================================
Complete o tabuleiro respeitando:
- Números de 1 a 9 por linha
- Números de 1 a 9 por coluna
- Números de 1 a 9 por bloco 3x3
===================================

------------- MENU -------------
1 - Novo jogo
2 - Fazer jogada
3 - Remover número
4 - Exibir tabuleiro
5 - Verificar status
0 - Sair
--------------------------------
Escolha uma opção:
````

- Após iniciar uma nova partida, o tabuleiro será exibido no console:

````bash
    1 2 3   4 5 6   7 8 9
  +-------+-------+-------+
1 | 5 . . | 8 . 1 | . . 7 |
2 | . 2 8 | . . . | 6 4 . |
3 | . . . | 3 6 . | . . 5 |
  +-------+-------+-------+
4 | 7 . . | . . 3 | . 9 . |
5 | . 9 . | . 2 . | . 5 . |
6 | . 6 . | 5 . . | . . 3 |
  +-------+-------+-------+
7 | 6 . . | . 4 8 | . . . |
8 | . 8 5 | . . . | 9 7 . |
9 | 9 . . | 7 . 5 | . . 4 |
  +-------+-------+-------+
````
O ponto `.` representa uma célula vazia.

O jogador deve informar:

- linha;
- coluna;
- número desejado.

---

## Exemplo de validação

- Se o jogador tentar alterar uma célula fixa, o sistema retorna:
```text
Erro: Não é possível alterar uma célula fixa do tabuleiro.
```

- Se tentar repetir um número na linha:
````text
Erro: Jogada inválida: o número já existe nesta linha.
````

- Se tentar repetir um número na coluna:
````text
Erro: Jogada inválida: o número já existe nesta coluna.
````

- Se tentar repetir um número no bloco 3x3:
````text
Erro: Jogada inválida: o número já existe neste bloco 3x3.
````

---

## Fluxo básico da aplicação
````text
Usuário inicia o jogo
        ↓
Escolhe a dificuldade
        ↓
Sistema gera um Sudoku aleatório
        ↓
Sistema remove células conforme a dificuldade
        ↓
Usuário realiza jogadas
        ↓
Sistema valida cada jogada
        ↓
Usuário completa o tabuleiro
        ↓
Sistema verifica se a solução está correta
````

## Possíveis melhorias futuras

Algumas melhorias que podem ser implementadas futuramente:

- Adicionar cronômetro.
- Adicionar contador de erros.
- Criar sistema de dicas.
- Criar opção para revelar a solução.
- Criar pontuação por dificuldade.
- Criar ranking local.
- Adicionar testes unitários com JUnit.
- Garantir que cada Sudoku gerado tenha solução única.
- Criar interface gráfica com JavaFX.
- Criar API com Spring Boot.
- Criar frontend com Angular ou React.
- Persistir histórico de partidas em banco de dados.
