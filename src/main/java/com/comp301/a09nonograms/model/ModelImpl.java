package com.comp301.a09nonograms.model;

import com.comp301.a09nonograms.view.*;

import com.comp301.a09nonograms.PuzzleLibrary;

import java.util.*;

public class ModelImpl implements Model {

  private Board board;
  private Board[] boards;
  private Clues clues;
  private List<Clues> puzzles;
  private int activePuzzle = 0;
  private List<ModelObserver> observers;
  private List<Integer> solvedPuzzles;

  public ModelImpl(List<Clues> clues) {
    puzzles = new ArrayList<>(clues);
    this.clues = puzzles.get(activePuzzle);
    observers = new ArrayList<>();
    solvedPuzzles = new ArrayList<>();
    boards = new Board[clues.size()];
    for (int i = 0; i < boards.length; i++) {
      boards[i] = new BoardImpl(puzzles.get(i).getWidth(), puzzles.get(i).getHeight());
      // System.out.println(i);
    }
    board = boards[activePuzzle];
  }

  public Clues getClues() {
    return clues;
  }

  public int getPuzzleCount() {
    return puzzles.size();
  }

  public int getPuzzleIndex() {
    return activePuzzle;
  }

  public void setPuzzleIndex(int index) {
    boards[activePuzzle] = board;
    activePuzzle = index;
    clues = puzzles.get(activePuzzle);
    board = boards[index];
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

    // System.out.println("row check:");

    for (int r = 0; r < clues.getHeight(); r++) {
      solved = (Arrays.equals(getClueR(r), clues.getRowClues(r))) & solved;

      // System.out.print(r + ": Actual: " + Arrays.toString(getClueR(r)));
      // System.out.print(", Answer: " + Arrays.toString(clues.getRowClues(r)));
      // System.out.print(", Result: " + solved + "\n");
    }

    // System.out.println("col check:");

    for (int c = 0; c < clues.getWidth(); c++) {
      solved = (Arrays.equals(getClueC(c), clues.getColClues(c))) & solved;

      // System.out.print(c + ": Actual: " + Arrays.toString(getClueC(c)));
      // System.out.print(", Answer: " + Arrays.toString(clues.getColClues(c)));
      // System.out.print(", Result: " + solved + "\n");
    }

    // System.out.println("FINAL RESULT: " + solved);

    if (solved && !solvedPuzzles.contains(activePuzzle)) {
      solvedPuzzles.add(activePuzzle);
    }

    return solved;
  }

  public int[] getClueR(int x) {
    int[] ret = new int[clues.getRowCluesLength()];
    Arrays.fill(ret, 0);
    boolean[] line = new boolean[clues.getWidth()];
    for (int c = 0; c < clues.getWidth(); c++) {
      line[c] = board.isShaded(x, c);
    }

    int groups = 0;
    boolean lastCell = false;
    for (boolean b : line) {
      if (b) {
        if (!lastCell) {
          groups++;
          if (groups > ret.length) {
            return null;
          }
        }
        ret[groups - 1]++;
      }
      lastCell = b;
      // System.out.println(i + ": " + line[i] + ", ret 0: " + ret[0] + ", ret 1: " + ret[1]);
    }

    int L = ret.length;
    int[] toReturn = new int[L];
    if (groups > L) {
      for (int i = 0; i < L; i++) {
        toReturn[L - i - 1] = ret[i];
      }
      return toReturn;
    } else {
      boolean allZero = true;
      for (int j : ret) {
        if (j != 0) {
          allZero = false;
          break;
        }
      }
      while (ret[L - 1] == 0 && !allZero) {
        for (int i = L - 1; i > 0; i--) {
          ret[i] = ret[i - 1];
          ret[i - 1] = 0;
        }
      }
      return ret;
    }
  }

  public int[] getClueC(int x) {
    int[] ret = new int[clues.getColCluesLength()];
    Arrays.fill(ret, 0);
    boolean[] line = new boolean[clues.getHeight()];
    for (int r = 0; r < clues.getHeight(); r++) {
      line[r] = board.isShaded(r, x);
    }

    int groups = 0;
    boolean lastCell = false;
    for (boolean b : line) {
      if (b) {
        if (!lastCell) {
          groups++;
          if (groups > ret.length) {
            return null;
          }
        }
        ret[groups - 1]++;
      }
      lastCell = b;
      // System.out.println(i + ": " + line[i] + ", ret 0: " + ret[0] + ", ret 1: " + ret[1]);
    }

    int L = ret.length;
    int[] toReturn = new int[L];
    if (groups > L) {
      for (int i = 0; i < L; i++) {
        toReturn[L - i - 1] = ret[i];
      }
      return toReturn;
    } else {
      boolean allZero = true;
      for (int j : ret) {
        if (j != 0) {
          allZero = false;
          break;
        }
      }
      while (ret[L - 1] == 0 && !allZero) {
        for (int i = L - 1; i > 0; i--) {
          ret[i] = ret[i - 1];
          ret[i - 1] = 0;
        }
      }
      return ret;
    }
  }

  public List<Integer> getSolvedPuzzles() {
    return solvedPuzzles;
  }

  private void note() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  // Board

  public boolean isShaded(int row, int col) {
    return board.isShaded(row, col);
  }

  public boolean isEliminated(int row, int col) {
    return board.isEliminated(row, col);
  }

  public boolean isSpace(int row, int col) {
    return board.isSpace(row, col);
  }

  public void toggleCellShaded(int row, int col) {
    board.toggleCellShaded(row, col);
    note();
  }

  public void toggleCellEliminated(int row, int col) {
    board.toggleCellEliminated(row, col);
    note();
  }

  public void clear() {
    solvedPuzzles.remove(new Integer(getPuzzleIndex()));
    board.clear();
    boards[getPuzzleIndex()] = board;
    note();
  }

  public void clearAll() {
    int current = activePuzzle;
    for (int i = 0; i < boards.length; i++) {
      setPuzzleIndex(i);
      clear();
    }
    setPuzzleIndex(current);
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
