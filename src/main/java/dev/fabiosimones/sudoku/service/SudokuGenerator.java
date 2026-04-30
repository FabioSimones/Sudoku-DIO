package dev.fabiosimones.sudoku.service;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Cell;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.model.SudokuPuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private final Random random;

    public SudokuGenerator() {
        this.random = new Random();
    }

    public SudokuPuzzle generate(Difficulty difficulty) {
        int[][] solutionBoard = new int[Board.SIZE][Board.SIZE];

        fillBoard(solutionBoard);

        int[][] initialBoard = copyBoard(solutionBoard);

        removeNumbers(initialBoard, difficulty.getEmptyCells());

        return new SudokuPuzzle(initialBoard, solutionBoard, difficulty);
    }

    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {

                if (board[row][col] == Cell.EMPTY) {
                    List<Integer> numbers = generateShuffledNumbers();

                    for (int number : numbers) {
                        if (isValid(board, row, col, number)) {
                            board[row][col] = number;

                            if (fillBoard(board)) {
                                return true;
                            }

                            board[row][col] = Cell.EMPTY;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int number = 1; number <= Board.SIZE; number++) {
            numbers.add(number);
        }

        Collections.shuffle(numbers, random);

        return numbers;
    }

    private boolean isValid(int[][] board, int row, int col, int number) {
        return !existsInRow(board, row, number)
                && !existsInColumn(board, col, number)
                && !existsInBox(board, row, col, number);
    }

    private boolean existsInRow(int[][] board, int row, int number) {
        for (int col = 0; col < Board.SIZE; col++) {
            if (board[row][col] == number) {
                return true;
            }
        }

        return false;
    }

    private boolean existsInColumn(int[][] board, int col, int number) {
        for (int row = 0; row < Board.SIZE; row++) {
            if (board[row][col] == number) {
                return true;
            }
        }

        return false;
    }

    private boolean existsInBox(int[][] board, int row, int col, int number) {
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;

        for (int currentRow = boxStartRow; currentRow < boxStartRow + 3; currentRow++) {
            for (int currentCol = boxStartCol; currentCol < boxStartCol + 3; currentCol++) {
                if (board[currentRow][currentCol] == number) {
                    return true;
                }
            }
        }

        return false;
    }

    private void removeNumbers(int[][] board, int emptyCells) {
        int removedCells = 0;

        while (removedCells < emptyCells) {
            int row = random.nextInt(Board.SIZE);
            int col = random.nextInt(Board.SIZE);

            if (board[row][col] != Cell.EMPTY) {
                board[row][col] = Cell.EMPTY;
                removedCells++;
            }
        }
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[Board.SIZE][Board.SIZE];

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                copy[row][col] = board[row][col];
            }
        }

        return copy;
    }
}
