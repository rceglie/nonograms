package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.*;

public class ControllerImpl implements Controller {

  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  public Clues getClues() {
    return model.getClues();
  }

  public boolean isSolved() {
    return model.isSolved();
  }

  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  public void nextPuzzle() {
    // TODO
  }

  public void prevPuzzle() {
    // TODO
  }

  public void randPuzzle() {
    // TODO
  }

  public void clearBoard() {
    model.clear();
  }

  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
