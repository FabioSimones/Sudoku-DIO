package dev.fabiosimones.sudoku;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.model.SudokuPuzzle;
import dev.fabiosimones.sudoku.service.SudokuGenerator;
import dev.fabiosimones.sudoku.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();

        SudokuPuzzle puzzle = generator.generate(Difficulty.MEDIUM);

        System.out.println("Dificuldade: " + puzzle.getDifficulty().getDescription());

        System.out.println("\nTabuleiro inicial:");
        printBoard(puzzle.getInitialBoard());

        System.out.println("\nSolução:");
        printBoard(puzzle.getSolutionBoard());
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
