package dev.fabiosimones.sudoku.service;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Cell;

public class SudokuValidator {
    public void validateMove(Board board, int row, int col, int number) {
        validateBoard(board);
        validatePosition(row, col);
        validateNumber(number);
        validateCellIsNotFixed(board, row, col);
        validateSudokuRules(board, row, col, number);
    }

    public boolean isValidMove(Board board, int row, int col, int number) {
        try {
            validateMove(board, row, col, number);
            return true;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            return false;
        }
    }

    public boolean isSolved(Board board, int[][] solutionBoard) {
        validateBoard(board);
        validateSolutionBoard(solutionBoard);

        if (!board.isComplete()) {
            return false;
        }

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.getValue(row, col) != solutionBoard[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isCompleteAndValid(Board board) {
        validateBoard(board);

        if (!board.isComplete()) {
            return false;
        }

        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                int number = board.getValue(row, col);

                if (number == Cell.EMPTY) {
                    return false;
                }

                if (hasConflict(board, row, col, number)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void validateSudokuRules(Board board, int row, int col, int number) {
        if (existsInRow(board, row, col, number)) {
            throw new IllegalArgumentException("Jogada inválida: o número já existe nesta linha.");
        }

        if (existsInColumn(board, row, col, number)) {
            throw new IllegalArgumentException("Jogada inválida: o número já existe nesta coluna.");
        }

        if (existsInBox(board, row, col, number)) {
            throw new IllegalArgumentException("Jogada inválida: o número já existe neste bloco 3x3.");
        }
    }

    private boolean hasConflict(Board board, int row, int col, int number) {
        return existsInRow(board, row, col, number)
                || existsInColumn(board, row, col, number)
                || existsInBox(board, row, col, number);
    }

    private boolean existsInRow(Board board, int row, int ignoredCol, int number) {
        for (int col = 0; col < Board.SIZE; col++) {
            if (col != ignoredCol && board.getValue(row, col) == number) {
                return true;
            }
        }

        return false;
    }

    private boolean existsInColumn(Board board, int ignoredRow, int col, int number) {
        for (int row = 0; row < Board.SIZE; row++) {
            if (row != ignoredRow && board.getValue(row, col) == number) {
                return true;
            }
        }

        return false;
    }

    private boolean existsInBox(Board board, int row, int col, int number) {
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;

        for (int currentRow = boxStartRow; currentRow < boxStartRow + 3; currentRow++) {
            for (int currentCol = boxStartCol; currentCol < boxStartCol + 3; currentCol++) {

                boolean isSamePosition = currentRow == row && currentCol == col;

                if (!isSamePosition && board.getValue(currentRow, currentCol) == number) {
                    return true;
                }
            }
        }

        return false;
    }

    private void validateBoard(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("O tabuleiro não pode ser nulo.");
        }
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= Board.SIZE || col < 0 || col >= Board.SIZE) {
            throw new IllegalArgumentException("Linha e coluna devem estar entre 0 e 8.");
        }
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("O número informado deve estar entre 1 e 9.");
        }
    }

    private void validateCellIsNotFixed(Board board, int row, int col) {
        if (board.isFixed(row, col)) {
            throw new IllegalStateException("Não é possível alterar uma célula fixa do tabuleiro.");
        }
    }

    private void validateSolutionBoard(int[][] solutionBoard) {
        if (solutionBoard == null || solutionBoard.length != Board.SIZE) {
            throw new IllegalArgumentException("O tabuleiro de solução deve possuir 9 linhas.");
        }

        for (int row = 0; row < Board.SIZE; row++) {
            if (solutionBoard[row] == null || solutionBoard[row].length != Board.SIZE) {
                throw new IllegalArgumentException("Cada linha da solução deve possuir 9 colunas.");
            }

            for (int col = 0; col < Board.SIZE; col++) {
                int value = solutionBoard[row][col];

                if (value < 1 || value > 9) {
                    throw new IllegalArgumentException("A solução deve conter apenas valores entre 1 e 9.");
                }
            }
        }
    }
}
