package dev.fabiosimones.sudoku.model;

public class SudokuPuzzle {
    private final int[][] initialBoard;
    private final int[][] solutionBoard;
    private final Difficulty difficulty;

    public SudokuPuzzle(int[][] initialBoard, int[][] solutionBoard, Difficulty difficulty) {
        this.initialBoard = copyBoard(initialBoard);
        this.solutionBoard = copyBoard(solutionBoard);
        this.difficulty = difficulty;
    }

    public int[][] getInitialBoard() {
        return copyBoard(initialBoard);
    }

    public int[][] getSolutionBoard() {
        return copyBoard(solutionBoard);
    }

    public Difficulty getDifficulty() {
        return difficulty;
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
