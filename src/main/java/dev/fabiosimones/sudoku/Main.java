package dev.fabiosimones.sudoku;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.model.SudokuPuzzle;
import dev.fabiosimones.sudoku.service.SudokuGenerator;
import dev.fabiosimones.sudoku.service.SudokuValidator;
import dev.fabiosimones.sudoku.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        SudokuValidator validator = new SudokuValidator();

        SudokuPuzzle puzzle = generator.generate(Difficulty.EASY);
        Board board = new Board(puzzle.getInitialBoard());

        printBoard(board.toMatrix());

        int testRow = 2;
        int testCol = 4;
        int testNumber = 5;

        try {
            validator.validateMove(board, testRow, testCol, testNumber);
            board.setValue(testRow, testCol, testNumber);
            System.out.println("Jogada realizada com sucesso!");
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }

        System.out.println("Sudoku resolvido? " + validator.isSolved(board, puzzle.getSolutionBoard()));
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
