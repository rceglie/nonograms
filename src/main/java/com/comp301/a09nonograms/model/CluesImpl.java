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
        return new int[0];
    }

    public int[] getColClues(int index) {
        return new int[0];
    }

    public int getRowCluesLength() {
        return rowClues[0].length;
    }

    public int getColCluesLength() {
        return colClues[0].length;
    }
}
