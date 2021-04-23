package com.comp301.a09nonograms.model;

public class BoardImpl implements Board{

    private int[][] board;
    private int width;
    private int height;

    public BoardImpl(int width, int height){
        this.width = width;
        this.height = height;
        board = new int[width][height];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                board[i][j] = 1;
            }
        }
    }

    public boolean isShaded(int row, int col) {
        System.out.println("Row: " + row + ", Col: " + col);
        if (row >= height || row < 0 || col < 0 || col >= width){
            throw new RuntimeException();
        }
        return board[row][col] == 2;
    }

    public boolean isEliminated(int row, int col) {
        return board[row][col] == 0;
    }

    public boolean isSpace(int row, int col) {
        return board[row][col] == 1;
    }

    public void toggleCellShaded(int row, int col) {
        board[row][col] = 2;
    }

    public void toggleCellEliminated(int row, int col) {
        board[row][col] = 0;
    }

    public void clear() {
        for (int r = 0; r < height; r++){
            for (int c = 0; c < width; c++){
                board[r][c] = 1;
            }
        }
    }
}
