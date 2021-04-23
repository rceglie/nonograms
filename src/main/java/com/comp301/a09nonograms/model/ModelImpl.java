package com.comp301.a09nonograms.model;
import com.comp301.a09nonograms.PuzzleLibrary;

import java.util.*;

public class ModelImpl implements Model{

    private Board board;
    private Clues clues;
    private List<Clues> puzzles;
    private int activePuzzle = 0;
    private List<ModelObserver> observers;

    public ModelImpl(List<Clues> clues){
        puzzles = new ArrayList<>(clues);
        this.clues = puzzles.get(activePuzzle);
        board = new BoardImpl(this.clues.getWidth(), this.clues.getHeight());
        observers = new ArrayList<>();
    }

    public int getPuzzleCount() {
        return puzzles.size();
    }

    public int getPuzzleIndex() {
        return activePuzzle;
    }

    public void setPuzzleIndex(int index) {
        activePuzzle = index;
        this.clues = puzzles.get(activePuzzle);
        board = new BoardImpl(this.clues.getWidth(), this.clues.getHeight());
        note();
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    public boolean isSolved() {

        boolean solved = true;

        System.out.println("horz check:");

        for (int i = 0; i < clues.getWidth(); i++) {
            solved = (Arrays.equals(getClueW(i), clues.getColClues(i))) & solved;

            System.out.print(i + ": Actual: " + Arrays.toString(getClueW(i)));
            System.out.print(", Answer: " + Arrays.toString(clues.getColClues(i)));
            System.out.print(", Result: " + solved + "\n");
        }

        System.out.println("vert check:");

        //solved = true;

        for (int i = 0; i < clues.getHeight(); i++) {
            solved = (Arrays.equals(getClueH(i), clues.getRowClues(i))) & solved;

            System.out.print(i + ": Actual: " + Arrays.toString(getClueH(i)));
            System.out.print(", Answer: " + Arrays.toString(clues.getRowClues(i)));
            System.out.print(", Result: " + solved + "\n");
        }

        return solved;
    }

    public int[] getClueW(int x){
        int[] ret = new int[] {0,0};
        boolean[] line = new boolean[clues.getHeight()];
        for(int i = 0; i < clues.getHeight(); i++){
            line[i] = board.isShaded(x,i);
        }

        int groups = 0;
        boolean lastCell = false;
        for(int i = 0; i < line.length; i++){
            if (line[i]){
                if(groups == 0){
                    ret[0]++;
                } else if (groups == 1){
                    ret[1]++;
                }
            }
            if ((!line[i] && lastCell) || line[i] && i +1 == line.length){
                groups++;
            }
            lastCell = line[i];
            //System.out.println(i + ": " + line[i] + ", ret 0: " + ret[0] + ", ret 1: " + ret[1]);
        }

        if (groups == 1) {
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        //System.out.println("Groups:" + groups);
        return ret;
    }

    public int[] getClueH(int x){
        int[] ret = new int[] {0,0};
        boolean[] line = new boolean[clues.getWidth()];
        for(int i = 0; i < clues.getWidth(); i++){
            line[i] = board.isShaded(i,x);
        }

        int groups = 0;
        boolean lastCell = false;
        for(int i = 0; i < line.length; i++){
            if (line[i]){
                if(groups == 0){
                    ret[0]++;
                } else if (groups == 1){
                    ret[1]++;
                }
            }
            if ((!line[i] && lastCell) || line[i] && i +1 == line.length){
                groups++;
            }
            lastCell = line[i];
            //System.out.println(i + ": " + line[i] + ", ret 0: " + ret[0] + ", ret 1: " + ret[1]);
        }

        if (groups == 1) {
            int temp = ret[0];
            ret[0] = ret[1];
            ret[1] = temp;
        }
        //System.out.println("Groups:" + groups);
        return ret;
    }

    private void note(){
        for (ModelObserver o: observers){
            o.update(this);
        }
    }


    // Board

    public boolean isShaded(int row, int col) {
        if (row >= getWidth() || row < 0 || col < 0 || col >= getHeight()){
            throw new RuntimeException();
        }
        return board.isShaded(row, col);
    }

    public boolean isEliminated(int row, int col) {
        return board.isEliminated(row, col);
    }

    public boolean isSpace(int row, int col) {
        return board.isSpace(row,col);
    }

    public void toggleCellShaded(int row, int col) {
        board.toggleCellShaded(row,col);
        note();
    }

    public void toggleCellEliminated(int row, int col) {
        board.toggleCellEliminated(row,col);
        note();
    }

    public void clear() {
        board.clear();
        note();
    }

    // Clues

    public int getWidth() {
        return clues.getWidth();
    }

    public int getHeight() {
        return clues.getHeight();
    }

    public int[] getRowClues(int index) {
        return clues.getRowClues(index);
    }

    public int[] getColClues(int index) {
        return clues.getColClues(index);
    }

    public int getRowCluesLength() {
        return clues.getRowCluesLength();
    }

    public int getColCluesLength() {
        return clues.getColCluesLength();
    }
}
