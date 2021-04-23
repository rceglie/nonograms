package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.*;
import com.comp301.a09nonograms.view.AppLauncher;
import javafx.application.Application;

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

    Clues clues = new CluesImpl(rowClues, colClues);
    Board board = new BoardImpl(clues.getWidth(),clues.getHeight());
    Model model = new ModelImpl(clues, board);

    board.toggleCellShaded(0,4);
    board.toggleCellShaded(0,3);
    board.toggleCellShaded(1,0);
    board.toggleCellShaded(1,3);
    board.toggleCellShaded(1,4);
    board.toggleCellShaded(2,2);
    board.toggleCellShaded(2,3);
    board.toggleCellShaded(2,4);
    board.toggleCellShaded(3,0);
    board.toggleCellShaded(3,1);
    board.toggleCellShaded(3,2);
    board.toggleCellShaded(4,2);
    board.toggleCellShaded(4,4);

    System.out.println("Solved?: " + model.isSolved());


    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++){
        if (board.isShaded(i,j)){
          System.out.print("X");
        } else if (board.isEliminated(i,j)){
          System.out.print("*");
        } else if (board.isSpace(i,j)){
          System.out.print("-");
        }
      }
      System.out.print("\n");
    }

    //Application.launch(AppLauncher.class);
  }
}
