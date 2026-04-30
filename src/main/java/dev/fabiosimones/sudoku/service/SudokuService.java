package dev.fabiosimones.sudoku.service;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.model.Difficulty;
import dev.fabiosimones.sudoku.model.SudokuPuzzle;

public class SudokuService {
    private final SudokuGenerator generator;
    private final SudokuValidator validator;

    private Board board;
    private int[][] solutionBoard;
    private Difficulty currentDifficulty;
    private boolean gameStarted;

    public SudokuService() {
        this.generator = new SudokuGenerator();
        this.validator = new SudokuValidator();
        this.gameStarted = false;
    }

    public void startNewGame(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("A dificuldade não pode ser nula.");
        }

        SudokuPuzzle puzzle = generator.generate(difficulty);

        this.board = new Board(puzzle.getInitialBoard());
        this.solutionBoard = puzzle.getSolutionBoard();
        this.currentDifficulty = difficulty;
        this.gameStarted = true;
    }

    public void makeMove(int row, int col, int number) {
        ensureGameStarted();

        validator.validateMove(board, row, col, number);
        board.setValue(row, col, number);
    }

    public void removeNumber(int row, int col) {
        ensureGameStarted();

        if (board.isFixed(row, col)) {
            throw new IllegalStateException("Não é possível remover um número fixo do tabuleiro.");
        }

        board.clearValue(row, col);
    }

    public boolean isSolved() {
        ensureGameStarted();

        return validator.isSolved(board, solutionBoard);
    }

    public boolean isComplete() {
        ensureGameStarted();

        return board.isComplete();
    }

    public boolean isCompleteAndValid() {
        ensureGameStarted();

        return validator.isCompleteAndValid(board);
    }

    public int getValue(int row, int col) {
        ensureGameStarted();

        return board.getValue(row, col);
    }

    public boolean isFixed(int row, int col) {
        ensureGameStarted();

        return board.isFixed(row, col);
    }

    public boolean isEmpty(int row, int col) {
        ensureGameStarted();

        return board.isEmpty(row, col);
    }

    public int[][] getCurrentBoard() {
        ensureGameStarted();

        return board.toMatrix();
    }

    public int[][] getSolutionBoard() {
        ensureGameStarted();

        return copyBoard(solutionBoard);
    }

    public Difficulty getCurrentDifficulty() {
        ensureGameStarted();

        return currentDifficulty;
    }

    public boolean hasGameStarted() {
        return gameStarted;
    }

    public String getGameStatus() {
        ensureGameStarted();

        if (isSolved()) {
            return "Sudoku resolvido com sucesso!";
        }

        if (isComplete()) {
            return "O tabuleiro está completo, mas ainda possui erros.";
        }

        return "Jogo em andamento.";
    }

    private void ensureGameStarted() {
        if (!gameStarted || board == null || solutionBoard == null) {
            throw new IllegalStateException("Nenhuma partida foi iniciada.");
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
