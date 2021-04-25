package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.view.*;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class HeaderView implements FXComponent {

  private Controller controller;

  public HeaderView(Controller c) {
    controller = c;
  }

  public Parent render() {

    FlowPane pane = new FlowPane();

    // Center area with "NONOGRAMS" and "SOLVING PUZZLE #"

    VBox vbox1 = new VBox();
    pane.getChildren().add(vbox1);
    pane.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(new Label("")); // Horizontal Filler

    Label label1 = new Label("NONOGRAMS");
    HBox hbox5 = new HBox();
    hbox5.getChildren().add(label1);
    hbox5.setAlignment(Pos.CENTER);
    vbox1.getChildren().add(hbox5);

    vbox1.getChildren().add(new Label("")); // Horizontal Filler

    Label label2 =
        new Label(
            "Solving puzzle #"
                + (1 + controller.getPuzzleIndex())
                + " of "
                + controller.getPuzzleCount());
    HBox hbox6 = new HBox();
    hbox6.getChildren().add(label2);
    hbox6.setAlignment(Pos.CENTER);
    vbox1.getChildren().add(hbox6);

    vbox1.getChildren().add(new Label("")); // Horizontal Filler

    HBox hboxb = new HBox();
    vbox1.getChildren().add(hboxb);

    // Previous Puzzle button

    Button btn1 = new Button();
    btn1.setText("Previous Puzzle");
    hboxb.getChildren().add(btn1);

    btn1.setOnAction(
        (event) -> {
          controller.prevPuzzle();
          // System.out.println("Previous Puzzle Button Press");
          // System.out.println("Current Puzzle:" + controller.getPuzzleIndex());
          AppLauncher.refresh();
        });

    hboxb.getChildren().add(new Label("         "));

    // Random Puzzle button

    Button btn2 = new Button();
    btn2.setText("Random Unsolved Puzzle");
    hboxb.getChildren().add(btn2);

    btn2.setOnAction(
        (event) -> {
          controller.randPuzzle();
          // System.out.println("Random Puzzle Button Press");
          // System.out.println("Current Puzzle:" + controller.getPuzzleIndex());
          AppLauncher.refresh();
        });

    hboxb.getChildren().add(new Label("         "));

    // Next puzzle button

    Button btn3 = new Button();
    btn3.setText("Next Puzzle");
    hboxb.getChildren().add(btn3);

    btn3.setOnAction(
        (event) -> {
          controller.nextPuzzle();
          // System.out.println("Next Puzzle Button Press");
          // System.out.println("Current Puzzle:" + controller.getPuzzleIndex());
          AppLauncher.refresh();
        });

    vbox1.getChildren().add(new Label("")); // Horizontal Filler
    HBox hboxr = new HBox();
    vbox1.getChildren().add(hboxr);

    // Reset buttons

    // Reset This Puzzle

    Button btn4 = new Button();
    btn4.setText("Reset This Puzzle");
    hboxr.getChildren().add(btn4);

    btn4.setOnAction(
        (event) -> {
          controller.clearBoard();
          // System.out.println("Reset Puzzle button press");
          AppLauncher.refresh();
        });

    hboxr.getChildren().add(new Label("         "));

    // Reset all puzzles

    Button btn5 = new Button();
    btn5.setText("Reset All Puzzles");
    hboxr.getChildren().add(btn5);

    btn5.setOnAction(
        (event) -> {
          controller.clearAll();
          controller.setMessage("Cleared all puzzles");
          // System.out.println("Reset All button press");
          AppLauncher.refresh();
        });

    hboxr.setAlignment(Pos.CENTER);

    vbox1.getChildren().add(new Label("")); // Horizontal Filler
    vbox1.getChildren().add(new Label("")); // Horizontal Filler

    return pane;
  }
}
