package dev.fabiosimones.sudoku;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.model.SudokuPuzzle;
import dev.fabiosimones.sudoku.service.SudokuGenerator;
import dev.fabiosimones.sudoku.service.SudokuService;
import dev.fabiosimones.sudoku.service.SudokuValidator;
import dev.fabiosimones.sudoku.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        SudokuService sudokuService = new SudokuService();

        sudokuService.startNewGame(Difficulty.EASY);

        System.out.println("Dificuldade: " + sudokuService.getCurrentDifficulty().getDescription());

        System.out.println("\nTabuleiro inicial:");
        printBoard(sudokuService.getCurrentBoard());

        playFirstCorrectMove(sudokuService);

        System.out.println("\nTabuleiro após uma jogada:");
        printBoard(sudokuService.getCurrentBoard());

        System.out.println("\nStatus: " + sudokuService.getGameStatus());
    }

    private static void playFirstCorrectMove(SudokuService sudokuService) {
        int[][] currentBoard = sudokuService.getCurrentBoard();
        int[][] solutionBoard = sudokuService.getSolutionBoard();

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (currentBoard[row][col] == 0) {
                    int correctNumber = solutionBoard[row][col];

                    try {
                        sudokuService.makeMove(row, col, correctNumber);

                        System.out.println("\nJogada realizada com sucesso!");
                        System.out.println("Linha: " + (row + 1));
                        System.out.println("Coluna: " + (col + 1));
                        System.out.println("Número: " + correctNumber);
                    } catch (IllegalArgumentException | IllegalStateException exception) {
                        System.out.println("\nErro: " + exception.getMessage());
                    }

                    return;
                }
            }
        }
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (row % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }

            for (int col = 0; col < board[row].length; col++) {
                if (col % 3 == 0) {
                    System.out.print("| ");
                }

                int value = board[row][col];
                System.out.print(value == 0 ? ". " : value + " ");
            }

            System.out.println("|");
        }

        System.out.println("+-------+-------+-------+");
    }
}
