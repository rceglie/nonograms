package com.comp301.a09nonograms;

import com.comp301.a09nonograms.controller.*;
import com.comp301.a09nonograms.model.*;
import com.comp301.a09nonograms.view.*;
import javafx.application.Application;

import java.util.*;

public class Main {

  private static Controller controller;

  public static void main(String[] args) {

    Model model = new ModelImpl(PuzzleLibrary.create());

    // fill(model);

    // System.out.println("Solved?: " + model.isSolved());

    // print(model);

    controller = new ControllerImpl(model);

    Application.launch(AppLauncher.class);
  }

  public static Controller getController() {
    return controller;
  }

  public static void print(Model model) {
    for (int r = 0; r < model.getHeight(); r++) {
      for (int c = 0; c < model.getWidth(); c++) {
        if (model.isShaded(r, c)) {
          System.out.print("X  ");
        } else if (model.isEliminated(r, c)) {
          System.out.print("*  ");
        } else if (model.isSpace(r, c)) {
          System.out.print("-  ");
        }
      }
      System.out.print("\n");
    }
  }

  public static void fill(Model model) {

    model.toggleCellShaded(0, 0);
    model.toggleCellShaded(0, 2);
    model.toggleCellShaded(0, 4);

    model.toggleCellShaded(1, 0);
    model.toggleCellShaded(1, 2);
    model.toggleCellShaded(1, 3);
    model.toggleCellShaded(1, 4);

    model.toggleCellShaded(2, 3);
    model.toggleCellShaded(2, 4);

    model.toggleCellShaded(3, 0);
    model.toggleCellShaded(3, 1);

    model.toggleCellShaded(4, 0);
    model.toggleCellShaded(4, 1);
  }
}
