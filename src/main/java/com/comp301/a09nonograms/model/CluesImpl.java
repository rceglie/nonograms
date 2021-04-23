package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues{

    private int width;
    private int height;
    private int[][] rowClues;
    private int[][] colClues;

    public CluesImpl(int[][] rowClues, int[][] colClues){
        width = colClues.length;
        height = rowClues.length;
        this.rowClues = rowClues;
        this.colClues = colClues;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getRowClues(int index) {
        return rowClues[index];
    }

    public int[] getColClues(int index) {
        return colClues[index];
    }

    public int getRowCluesLength() {
        return colClues.length;
    }

    public int getColCluesLength() {
        return rowClues.length;
    }
}
