package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.*;
import com.comp301.a09nonograms.view.AppLauncher;
import javafx.application.Application;

import java.util.*;

public class Main {
  public static void main(String[] args) {

    int[][] rowClues =
            new int[][] {
                    new int[] {0, 2},
                    new int[] {1, 2},
                    new int[] {0, 3},
                    new int[] {0, 3},
                    new int[] {1, 1},
            };

    int[][] colClues =
            new int[][] {
                    new int[] {1, 1},
                    new int[] {0, 1},
                    new int[] {0, 3},
                    new int[] {0, 3},
                    new int[] {3, 1},
            };

    List<Clues> tester = new ArrayList<>();
    tester.add(new CluesImpl(rowClues, colClues));

    Model model2 = new ModelImpl(tester);
    Model model = new ModelImpl(PuzzleLibrary.create());

    fill(model);

    System.out.println("Solved?: " + model.isSolved());

    print(model);

    //Application.launch(AppLauncher.class);
  }

  public static void print(Model model){
    for (int r = 0; r < model.getHeight(); r++){
      for (int c = 0; c < model.getWidth(); c++){
        if (model.isShaded(r,c)){
          System.out.print("X  ");
        } else if (model.isEliminated(r,c)){
          System.out.print("*  ");
        } else if (model.isSpace(r,c)){
          System.out.print("-  ");
        }
      }
      System.out.print("\n");
    }
  }

  public static void fill(Model model){
    model.toggleCellShaded(1,1);
    model.toggleCellShaded(1,2);
    model.toggleCellShaded(1,3);
    model.toggleCellShaded(1,4);

    model.toggleCellShaded(2,1);
    model.toggleCellShaded(2,2);
    model.toggleCellShaded(2,3);
    model.toggleCellShaded(2,4);
    model.toggleCellShaded(2,5);
    model.toggleCellShaded(2,6);

    model.toggleCellShaded(3,1);
    model.toggleCellShaded(3,2);
    model.toggleCellShaded(3,5);
    model.toggleCellShaded(3,6);

    model.toggleCellShaded(4,1);
    model.toggleCellShaded(4,2);
    model.toggleCellShaded(4,5);
    model.toggleCellShaded(4,6);

    model.toggleCellShaded(5,1);
    model.toggleCellShaded(5,2);
    model.toggleCellShaded(5,3);
    model.toggleCellShaded(5,4);
    model.toggleCellShaded(5,5);
    model.toggleCellShaded(5,6);

    model.toggleCellShaded(6,1);
    model.toggleCellShaded(6,2);
    model.toggleCellShaded(6,3);
    model.toggleCellShaded(6,4);

    model.toggleCellShaded(7,1);
    model.toggleCellShaded(7,2);

    model.toggleCellShaded(8,1);
    model.toggleCellShaded(8,2);

    model.toggleCellShaded(9,1);
    model.toggleCellShaded(9,2);
  }

}
