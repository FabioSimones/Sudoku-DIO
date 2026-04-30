package dev.fabiosimones.sudoku.model;

public enum Difficulty {
    EASY("Fácil", 35),
    MEDIUM("Médio", 45),
    HARD("Difícil", 55);

    private final String description;
    private final int emptyCells;

    Difficulty(String description, int emptyCells) {
        this.description = description;
        this.emptyCells = emptyCells;
    }

    public String getDescription() {
        return description;
    }

    public int getEmptyCells() {
        return emptyCells;
    }

    public static Difficulty fromOption(int option) {
        return switch (option) {
            case 1 -> EASY;
            case 2 -> MEDIUM;
            case 3 -> HARD;
            default -> throw new IllegalArgumentException("Opção de dificuldade inválida.");
        };
    }
}
