package dev.fabiosimones.sudoku.model;

public class Cell {
    public static final int EMPTY = 0;

    private int value;
    private final boolean fixed;

    public Cell(int value, boolean fixed) {
        validateValue(value);

        if (fixed && value == EMPTY) {
            throw new IllegalArgumentException("Uma célula fixa não pode ser vazia.");
        }

        this.value = value;
        this.fixed = fixed;
    }

    public int getValue() {
        return value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isEmpty() {
        return value == EMPTY;
    }

    public void setValue(int value) {
        if (fixed) {
            throw new IllegalStateException("Não é possível alterar uma célula fixa.");
        }

        validateValue(value);
        this.value = value;
    }

    public void clear() {
        if (fixed) {
            throw new IllegalStateException("Não é possível limpar uma célula fixa.");
        }

        this.value = EMPTY;
    }

    private void validateValue(int value) {
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("O valor da célula deve estar entre 0 e 9.");
        }
    }

    @Override
    public String toString() {
        return value == EMPTY ? "." : String.valueOf(value);
    }
}
