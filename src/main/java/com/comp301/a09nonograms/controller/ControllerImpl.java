package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.*;

import java.util.Random;

public class ControllerImpl implements Controller {

  private Model model;
  private String message;

  public ControllerImpl(Model model) {
    this.model = model;
    message = "";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String s) {
    message = s;
  }

  public Clues getClues() {
    return model.getClues();
  }

  public boolean isSolved() {
    if (model.isSolved()
        && !message.equals("All puzzles have been completed. Reset to continue.")
        && !message.contains("There is no")) {
      if (model.getSolvedPuzzles().size() == 1) {
        message = "You have solved 1 puzzle out of " + getPuzzleCount();
      } else {
        message =
            "You have solved "
                + model.getSolvedPuzzles().size()
                + " puzzles out of "
                + getPuzzleCount();
      }
    }
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

    if (model.getPuzzleIndex() < model.getPuzzleCount() - 1) {
      model.setPuzzleIndex(model.getPuzzleIndex() + 1);
      setMessage("");
    } else {
      setMessage("There is no next puzzle");
    }
  }

  public void prevPuzzle() {

    if (model.getPuzzleIndex() > 0) {
      model.setPuzzleIndex(model.getPuzzleIndex() - 1);
      setMessage("");
    } else {
      setMessage("There is no previous puzzle");
    }
  }

  public void randPuzzle() {
    // TODO
    if (getPuzzleCount() == model.getSolvedPuzzles().size()) {
      setMessage("All puzzles have been completed. Reset to continue.");
    } else if (getPuzzleCount() == 1 + model.getSolvedPuzzles().size()) {
      Random r = new Random();
      int rand = r.nextInt(getPuzzleCount());
      while (model.getSolvedPuzzles().contains(rand)) {
        rand = r.nextInt(getPuzzleCount());
      }
      model.setPuzzleIndex(rand);
      setMessage("");
    } else {
      Random r = new Random();
      int rand = r.nextInt(getPuzzleCount());
      while (model.getSolvedPuzzles().contains(rand) || rand == model.getPuzzleIndex()) {
        rand = r.nextInt(getPuzzleCount());
      }
      model.setPuzzleIndex(rand);
      setMessage("");
    }
  }

  public void clearBoard() {
    setMessage("Cleared Puzzle #" + (getPuzzleIndex() + 1));
    model.clear();
  }

  public void clearAll() {
    setMessage("Cleared All Puzzles");
    model.clearAll();
  }

  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
