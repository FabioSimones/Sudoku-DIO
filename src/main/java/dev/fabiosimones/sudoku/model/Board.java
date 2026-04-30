package dev.fabiosimones.sudoku.model;

public class Board {
    public static final int SIZE = 9;

    private final Cell[][] cells;

    public Board(int[][] initialValues) {
        validateBoard(initialValues);

        this.cells = new Cell[SIZE][SIZE];

        initializeCells(initialValues);
    }

    private void initializeCells(int[][] initialValues) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = initialValues[row][col];
                boolean fixed = value != Cell.EMPTY;

                cells[row][col] = new Cell(value, fixed);
            }
        }
    }

    public int getValue(int row, int col) {
        validatePosition(row, col);
        return cells[row][col].getValue();
    }

    public boolean isFixed(int row, int col) {
        validatePosition(row, col);
        return cells[row][col].isFixed();
    }

    public boolean isEmpty(int row, int col) {
        validatePosition(row, col);
        return cells[row][col].isEmpty();
    }

    public void setValue(int row, int col, int value) {
        validatePosition(row, col);
        cells[row][col].setValue(value);
    }

    public void clearValue(int row, int col) {
        validatePosition(row, col);
        cells[row][col].clear();
    }

    public boolean isComplete() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col].isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[][] toMatrix() {
        int[][] matrix = new int[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                matrix[row][col] = cells[row][col].getValue();
            }
        }

        return matrix;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Linha e coluna devem estar entre 0 e 8.");
        }
    }

    private void validateBoard(int[][] board) {
        if (board == null || board.length != SIZE) {
            throw new IllegalArgumentException("O tabuleiro deve possuir 9 linhas.");
        }

        for (int row = 0; row < SIZE; row++) {
            if (board[row] == null || board[row].length != SIZE) {
                throw new IllegalArgumentException("Cada linha do tabuleiro deve possuir 9 colunas.");
            }

            for (int col = 0; col < SIZE; col++) {
                int value = board[row][col];

                if (value < 0 || value > 9) {
                    throw new IllegalArgumentException("Os valores do tabuleiro devem estar entre 0 e 9.");
                }
            }
        }
    }
}
