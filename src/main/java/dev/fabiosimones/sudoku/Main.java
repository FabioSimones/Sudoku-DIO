package dev.fabiosimones.sudoku;

import dev.fabiosimones.sudoku.model.Board;
import dev.fabiosimones.sudoku.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        Board board = new Board(initialBoard);

        System.out.println("Tabuleiro criado com sucesso!");
        System.out.println("Valor da posição [0][0]: " + board.getValue(0, 0));
        System.out.println("A posição [0][0] é fixa? " + board.isFixed(0, 0));
        System.out.println("A posição [0][2] está vazia? " + board.isEmpty(0, 2));
    }
}
