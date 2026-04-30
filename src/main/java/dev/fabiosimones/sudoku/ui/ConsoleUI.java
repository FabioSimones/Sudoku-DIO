package dev.fabiosimones.sudoku.ui;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.service.SudokuService;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final SudokuService sudokuService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.sudokuService = new SudokuService();
    }

    public void start() {
        boolean running = true;

        showWelcomeMessage();

        while (running) {
            showMenu();

            int option = readInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> startNewGame();
                case 2 -> makeMove();
                case 3 -> removeNumber();
                case 4 -> showBoard();
                case 5 -> showGameStatus();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("\nObrigado por jogar Sudoku!");
        scanner.close();
    }

    private void showWelcomeMessage() {
        System.out.println("===================================");
        System.out.println("         SUDOKU EM JAVA");
        System.out.println("===================================");
        System.out.println("Complete o tabuleiro respeitando:");
        System.out.println("- Números de 1 a 9 por linha");
        System.out.println("- Números de 1 a 9 por coluna");
        System.out.println("- Números de 1 a 9 por bloco 3x3");
        System.out.println("===================================");
    }

    private void showMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Fazer jogada");
        System.out.println("3 - Remover número");
        System.out.println("4 - Exibir tabuleiro");
        System.out.println("5 - Verificar status");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------");
    }

    private void startNewGame() {
        try {
            Difficulty difficulty = chooseDifficulty();

            sudokuService.startNewGame(difficulty);

            System.out.println("\nNovo jogo iniciado!");
            System.out.println("Dificuldade: " + difficulty.getDescription());

            printBoard(sudokuService.getCurrentBoard());
        } catch (IllegalArgumentException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private Difficulty chooseDifficulty() {
        System.out.println("\nEscolha a dificuldade:");
        System.out.println("1 - Fácil");
        System.out.println("2 - Médio");
        System.out.println("3 - Difícil");

        int option = readInt("Opção: ");

        return Difficulty.fromOption(option);
    }

    private void makeMove() {
        if (!sudokuService.hasGameStarted()) {
            System.out.println("Nenhuma partida foi iniciada. Escolha a opção 1 para iniciar um novo jogo.");
            return;
        }

        try {
            printBoard(sudokuService.getCurrentBoard());

            int row = readCoordinate("Informe a linha (1 a 9): ");
            int col = readCoordinate("Informe a coluna (1 a 9): ");
            int number = readNumber("Informe o número (1 a 9): ");

            sudokuService.makeMove(row, col, number);

            System.out.println("\nJogada realizada com sucesso!");
            printBoard(sudokuService.getCurrentBoard());

            if (sudokuService.isSolved()) {
                System.out.println("Parabéns! Você resolveu o Sudoku!");
            }

        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private void removeNumber() {
        if (!sudokuService.hasGameStarted()) {
            System.out.println("Nenhuma partida foi iniciada. Escolha a opção 1 para iniciar um novo jogo.");
            return;
        }

        try {
            printBoard(sudokuService.getCurrentBoard());

            int row = readCoordinate("Informe a linha do número que deseja remover (1 a 9): ");
            int col = readCoordinate("Informe a coluna do número que deseja remover (1 a 9): ");

            sudokuService.removeNumber(row, col);

            System.out.println("\nNúmero removido com sucesso!");
            printBoard(sudokuService.getCurrentBoard());

        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private void showBoard() {
        if (!sudokuService.hasGameStarted()) {
            System.out.println("Nenhuma partida foi iniciada. Escolha a opção 1 para iniciar um novo jogo.");
            return;
        }

        System.out.println("\nDificuldade atual: " + sudokuService.getCurrentDifficulty().getDescription());
        printBoard(sudokuService.getCurrentBoard());
    }

    private void showGameStatus() {
        if (!sudokuService.hasGameStarted()) {
            System.out.println("Nenhuma partida foi iniciada. Escolha a opção 1 para iniciar um novo jogo.");
            return;
        }

        System.out.println("\nStatus: " + sudokuService.getGameStatus());
    }

    private int readCoordinate(String message) {
        int value = readInt(message);

        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("Linha e coluna devem estar entre 1 e 9.");
        }

        return value - 1;
    }

    private int readNumber(String message) {
        int number = readInt(message);

        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("O número deve estar entre 1 e 9.");
        }

        return number;
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);

            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }

    private void printBoard(int[][] board) {
        System.out.println();

        System.out.println("    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");

        for (int row = 0; row < Board.SIZE; row++) {
            System.out.print((row + 1) + " | ");

            for (int col = 0; col < Board.SIZE; col++) {
                int value = board[row][col];

                System.out.print(value == 0 ? ". " : value + " ");

                if ((col + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }

            System.out.println();

            if ((row + 1) % 3 == 0) {
                System.out.println("  +-------+-------+-------+");
            }
        }
    }
}
