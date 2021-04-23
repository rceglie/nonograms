package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.*;
import com.comp301.a09nonograms.view.AppLauncher;
import javafx.application.Application;

import java.util.Arrays;

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

    System.out.println(colClues.length);

    Model model = new ModelImpl(PuzzleLibrary.create());

    //model.toggleCellShaded(0,4);
    //model.toggleCellShaded(0,3);
    //model.toggleCellShaded(1,0);
    model.toggleCellShaded(1,3);
    model.toggleCellShaded(1,4);
    model.toggleCellShaded(2,2);
    model.toggleCellShaded(2,3);
    model.toggleCellShaded(2,4);
    //model.toggleCellShaded(3,0);
    model.toggleCellShaded(3,1);
    model.toggleCellShaded(3,2);
    model.toggleCellShaded(4,2);
    model.toggleCellShaded(4,4);

    System.out.println("Solved?: " + model.isSolved());


    for (int i = 0; i < model.getWidth(); i++){
      for (int j = 0; j < model.getHeight(); j++){
        if (model.isShaded(i,j)){
          System.out.print("X");
        } else if (model.isEliminated(i,j)){
          System.out.print("*");
        } else if (model.isSpace(i,j)){
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }

    //Application.launch(AppLauncher.class);
  }
}
